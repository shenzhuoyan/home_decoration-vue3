<template>
    <el-tabs type="border-card">
        <el-tab-pane label="列表" style="background-color:#f5f6f8">
            <ul v-infinite-scroll="load" style="overflow: auto;height: calc(100vh - 72px);" v-if="isMounted"
                infinite-scroll-distance='1' class="infinite-list">
                <li class="infinite-list-item">
                    <el-row style="height: 100%;align-items: center;display: flex;">
                        <el-col :sm="8" :md="8" :xl="4">
                            <span style="margin-left:20px;margin-right:50px;">id</span>
                            <span style="margin-right:50px;">日期</span>
                            <span style="margin-right:20px;">状态</span>
                        </el-col>
                        <el-col :sm="4" :md="6" :xl="16">
                            <span>标题</span>
                        </el-col>
                        <el-col :sm="12" :md="10" :xl="4">
                            <span style="margin-left:40px">操作</span>
                        </el-col>
                    </el-row>
                </li>
                <li class="infinite-list-item" v-for="notice in noticeList" :key="notice.id"
                    @click="openDrawer(notice.id)">
                    <el-row style="height: 100%;align-items: center;display: flex;">
                        <el-col :sm="8" :md="8" :xl="4">
                            <span style="margin-left:20px;margin-right:20px;">{{ notice.id }}</span>
                            <span style="margin-right:20px ;">{{ notice.date }}</span>
                            <span style="margin-right:20px ;">
                                <el-tag :type="statusTag(notice.status)">
                                    {{ statusFilter(notice.status) }}
                                </el-tag>
                            </span>
                        </el-col>
                        <el-col :sm="4" :md="6" :xl="16">
                            {{ notice.title }}
                        </el-col>
                        <el-col :sm="12" :md="10" :xl="4" @click.stop>
                            <el-button text @click="top(notice.id)" :disabled="!(notice.status < 3)"
                                type="primary">置顶</el-button>
                            <el-button text @click="important(notice.id)" :disabled="!(notice.status < 2)"
                                type="success">设为重要</el-button>
                            <el-button text @click="resume(notice.id)" :disabled="!(notice.status > 1)"
                                type="info">设为普通</el-button>
                            <el-button type="danger" @click="del(notice.id)">删除</el-button>
                        </el-col>
                    </el-row>
                </li>
            </ul>
            <el-drawer v-model="drawer" :with-header="false" :size="'60%'">
                <h1>{{ currNotice.data.title }}</h1>
                <p>{{ currNotice.data.date }}</p>
                <p><el-tag :type="statusTag(currNotice.data.status)">{{ statusFilter(currNotice.data.status) }}</el-tag>
                </p>
                <div v-html="currNotice.data.content"></div>
            </el-drawer>
        </el-tab-pane>
        <el-tab-pane label="添加">
            <div style="height:calc(100vh - 72px);overflow-y:auto;width: 100%;overflow-x:hidden;">
                <el-row>
                    <el-col :xs="4" :sm="2"><span>标题</span></el-col>
                    <el-col :xs="20" :sm="22"><el-input v-model="newNotice.data.title" class="w-50 m-2"
                            placeholder="输入标题" /></el-col>
                </el-row>
                <p>内容：</p>
                <div style="border: 1px solid #ccc;margin-top:20px">
                    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
                        :mode="mode" />
                    <Editor style="overflow-y: hidden;height: 500px;" v-model="newNotice.data.content"
                        :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
                </div>
                <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
                    <el-col :xs="8" :sm="2">
                        <el-button type="primary" @click="add" size="large" style="width:100%">添加</el-button>
                    </el-col>
                </el-row>
            </div>
        </el-tab-pane>
    </el-tabs>

</template>

<script>
import axios from 'axios'
import { onBeforeUnmount, reactive, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import '@wangeditor/editor/dist/css/style.css'
import { onMounted } from '@vue/runtime-core'
// import { Plus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
    name: 'NoticeManage',
    components: { Editor, Toolbar },
    setup() {
        // 编辑器实例，必须用 shallowRef
        const editorRef = shallowRef()
        const toolbarConfig = { excludeKeys: ['insertTable', 'emotion', 'group-video', 'fullScreen'] }
        const editorConfig = {
            placeholder: '请输入内容...',
            MENU_CONF: {
                uploadImage: {
                    server: '/admin/picture/push',
                    fieldName: 'picture'//后端接收的时候用的变量名
                },
            }
        }
        // 组件销毁时，也及时销毁编辑器
        onBeforeUnmount(() => {
            const editor = editorRef.value
            if (editor == null) return
            editor.destroy()
        })
        const handleCreated = (editor) => {
            editorRef.value = editor // 记录 editor 实例，重要！
        }


        const noticeList = ref([])
        //总页数
        let totalPage = 0
        //当前页数
        let currentPage = 1

        //当前通知
        const currNotice = reactive({ data: {} })
        //控制抽屉的显示
        const drawer = ref(false)
        //创建新的通知
        let newNotice = reactive({ data: {title:'',content:''} })

        //获取下一页
        const getNoticeList = () => {
            axios.get('/admin/noticeList/all?page=' + currentPage).then(res => {
                noticeList.value.push(...res.data.data)
                currentPage++
            })
        }
        const load = () => {
            if (currentPage == 1) {
                getNoticeList()
            }
            else if (currentPage <= totalPage) {
                getNoticeList()
            }
        }

        //以方法来实现过滤器的作用，也就是根据对应状态返回中文的状态文字
        let statusFilter = (value) => {
            switch (value) {
                case 0: return '已删除'
                case 1: return '通知'
                case 2: return '重要'
                case 3: return '置顶'
                default: return '未知'
            }
        }

        //这里是控制标记类型
        let statusTag = (value) => {

            switch (value) {
                case 0: return 'danger'
                case 1: return 'success'
                case 2: return 'info'
                case 3: return 'warning'
                default: return 'danger'
            }
        }

        //打开抽屉
        const openDrawer = (id) => {
            axios.get('/noticeDetail/get?id=' + id).then(res => {
                currNotice.data = res.data.data
                drawer.value = true
            })
        }

        //置顶
        const top = (id) => {
            ElMessageBox.confirm(
                '确定置顶吗？',
                '提示!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                axios.get('/admin/noticeList/top?id=' + id).then(res => {
                    if (res.data.code == 200)
                        ElMessage.success("置顶成功!")
                    else ElMessage.error(res.data.msg)
                    update()
                })
            }).catch(()=>{})
        }

        //设置为重要
        const important = (id) => {
            ElMessageBox.confirm(
                '确定设为重要吗？',
                '提示!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                axios.get('/admin/noticeList/important?id=' + id).then(res => {
                    if (res.data.code == 200)
                        ElMessage.success("设置重要成功!")
                    else ElMessage.error(res.data.msg)
                    update()
                })
            }).catch(()=>{})
        }

        //把置顶的和重要的降为普通的
        const resume = (id) => {
            ElMessageBox.confirm(
                '确定设为普通通知吗？',
                '提示!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                axios.get('/admin/noticeList/resume?id=' + id).then(res => {
                    if (res.data.code == 200)
                        ElMessage.success("设置普通通知成功!")
                    else ElMessage.error(res.data.msg)
                    update()
                })
            }).catch(()=>{})
        }

        //删除
        const del = (id) => {
            ElMessageBox.confirm(
                '确定删除吗？',
                '提示!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                axios.get('/admin/noticeList/del?id=' + id).then(res => {
                    if (res.data.code == 200)
                        ElMessage.success("删除成功!")
                    else ElMessage.error(res.data.msg)
                    update()
                })
            }).catch(()=>{})
        }
        
        //更新数据
        const update = () => {
            // axios.get("/admin/noticeList/all?page=1").then(res => {
            //     noticeList.value = res.data.data
            // })
            noticeList.value = []
            currentPage = 1
            load()
        }
        //初始化信息，获得总页数
        axios.get('/admin/noticeList/page').then(res => {
            totalPage = res.data.data
        })
       

        //添加案例
        const add = () => {
            if (newNotice.data.content != '' && newNotice.data.title != '') {
                ElMessageBox.confirm(
                    '确定发布通知吗？',
                    '提示!',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    axios.post('/admin/noticeList/add', newNotice.data).then(res => {
                        if (res.data.code == 200) {
                            ElMessage.success('添加成功！')
                            update()
                            newNotice.data = {}
                        }
                        else ElMessage.error(res.data.msg)
                    })
                })
            }
            else ElMessage.error('必须全部填写！')
        }

        //挂载完毕才能开启滚动刷新
        const isMounted = ref(false);
        onMounted(() => {
            isMounted.value = true;
        });
        return {
            editorRef,
            mode: 'default', // 或 'simple'
            toolbarConfig,
            editorConfig,
            handleCreated,
            noticeList,
            newNotice,
            del,
            top,
            important,
            resume,
            add,
            load,
            isMounted,
            statusFilter,
            statusTag,
            currNotice,
            openDrawer,
            drawer
        }
    }
}
</script>

<style scoped>
.infinite-list {
    height: 300px;
    padding: 0;
    margin: 0;
    list-style: none;
}

.infinite-list .infinite-list-item {
    /* display: flex; */
    /* align-items: center; */
    /* justify-content: center; */
    /* padding-left:20px; */
    height: 50px;
    /* background: var(--el-color-primary-light-9); */
    background-color: #fcfcfc;
    margin: 10px;
    /* color: var(--el-color-primary); */
}

.infinite-list .infinite-list-item+.list-item {
    margin-top: 10px;
}
</style>