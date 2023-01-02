<template>
    <el-tabs type="border-card">
        <el-tab-pane label="列表">
            <div class="demo-collapse" style="height:calc(100vh - 72px);overflow-y:auto;">
                <el-collapse>
                    <el-collapse-item :title="anLi.title" :name="anLi.id" v-for="anLi in anLiList" :key="anLi.id">
                        <el-row><el-col><img :src="anLi.picture" /></el-col></el-row>
                        <el-row>
                            <el-col>id：{{ anLi.id }}</el-col>
                            <el-col>日期：{{ anLi.date }}</el-col>
                            <el-col>作者：{{ anLi.designer.title }}</el-col>
                        </el-row>
                        <el-row>
                            <el-col>引言：{{ anLi.introduction }}</el-col>
                        </el-row>
                        内容：
                        <div v-html="anLi.content"></div>
                        <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
                            <el-col :xs="8" :sm="2">
                                <el-button type="danger" @click="del(anLi.id)" size="large"
                                    style="width:100%">删除</el-button>
                            </el-col>
                        </el-row>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </el-tab-pane>
        <el-tab-pane label="添加">
            <div style="height:calc(100vh - 72px);overflow-y:auto;width: 100%;overflow-x:hidden;">
                <el-row>
                    <el-col :xs="4" :sm="2"><span>标题</span></el-col>
                    <el-col :xs="20" :sm="22"><el-input v-model="newAnLi.data.title" class="w-50 m-2"
                            placeholder="输入标题" /></el-col>
                </el-row>
                <el-row style="margin-top:20px">
                    <el-col :xs="4" :sm="2"><span>引言</span></el-col>
                    <el-col :xs="20" :sm="22"><el-input v-model="newAnLi.data.introduction" class="w-50 m-2"
                            placeholder="输入引言" type="textarea" :rows="3" /></el-col>
                </el-row>
                <el-row style="margin-top:20px">
                    <el-col :xs="4" :sm="2"><span>设计师</span></el-col>
                    <el-col :xs="20" :sm="22">
                        <el-select v-model="newAnLi.data.designer.id" class="m-2" placeholder="Select" size="large">
                            <el-option v-for="item in designerList" :key="item.id" :label="item.title"
                                :value="item.id" />
                        </el-select>
                    </el-col>
                </el-row>
                <p>封面图</p>
                <el-upload class="avatar-uploader" action="/admin/picture/push" name="picture" :show-file-list="false"
                    :on-success="handleAvatarSuccess">
                    <img v-if="newAnLi.data.picture" :src="newAnLi.data.picture" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
                内容：
                <div style="border: 1px solid #ccc;margin-top:20px">
                    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
                        :mode="mode" />
                    <Editor style="overflow-y: hidden;height: 500px;" v-model="newAnLi.data.content"
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
import { Plus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
    name: 'AnLiManage',
    components: { Editor, Toolbar, Plus },
    setup() {
        // 编辑器实例，必须用 shallowRef
        const editorRef = shallowRef()
        // 模拟 ajax 异步获取内容
        // onMounted(() => {
        //   setTimeout(() => {
        //     valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
        //   }, 1500)
        // })
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
        const handleAvatarSuccess = (response) => {
            newAnLi.data.picture = response.data.url
        }


        const anLiList = ref([])
        const designerList = ref([])

        //创建新的案例
        let newAnLi = reactive({ data: { id: '', title: '', introduction: '', picture: '', date: '', content: '', designer: { id: '' } } })

        //更新数据
        const update = () => {
            axios.get("/admin/anLi/all").then(res => {
                anLiList.value = res.data.data
            })
            axios.get('/designerIntroduce').then(res => {
                designerList.value = res.data.data
            })
        }
        const del = (id) => {
            ElMessageBox.confirm(
                '确定删除该案例吗？',
                '警告!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                //确认
                axios.get('/admin/anLi/del?id=' + id).then(res => {
                    if (res.data.code == 200) {
                        ElMessage({ type: 'success', message: '已删除！' })
                        update()
                    }
                    else {
                        ElMessage({ type: 'info', message: res.data.msg })
                    }
                })
            })
        }

        //添加案例
        const add = () => {
            if (newAnLi.data.content != '' && newAnLi.data.title != '' && newAnLi.data.picture != '' && newAnLi.data.introduction != '') {
                ElMessageBox.confirm(
                    '确定添加案例吗？',
                    '提示!',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    axios.post('/admin/anLi/add', newAnLi.data).then(res => {
                        if (res.data.code == 200) {
                            ElMessage.success('添加成功！')
                            update()
                            newAnLi.data = { id: '', title: '', introduction: '', picture: '', date: '', content: '', designer: { id: '' } }
                        }
                        else ElMessage.error(res.data.msg)
                    })
                })
            }
            else ElMessage.error('必须全部填写！')
        }

        update()
        return {
            editorRef,
            mode: 'default', // 或 'simple'
            toolbarConfig,
            editorConfig,
            handleCreated,
            anLiList,
            designerList,
            newAnLi,
            handleAvatarSuccess,
            del,
            add,
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>