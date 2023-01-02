<template>

    <div v-if="!isModify" style="margin-top:20px">
        <el-row :gutter="20">
            <el-col :span="8" v-for="designer in designerList" :key="designer.id" style="cursor: pointer;">
                <el-card :body-style="{ padding: '0px' }">
                    <img :src="designer.picture" class="image" style="width:100%">
                    <div style="padding: 14px;text-align: center">
                        <div style="font-size: 30px;">{{ designer.title }}</div>
                        <el-button text @click="toModify(designer)">修改</el-button>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
    <div v-else style="margin-top:20px">
        <el-button text @click="isModify=!isModify">返回</el-button>
        <p style="color:orange">注意：修改姓名后原来案例对应的作者也会被改为当前设计师</p>
        <el-row>
            <el-col :xs="4" :sm="2"><span>姓名</span></el-col>
            <el-col :xs="20" :sm="22"><el-input v-model="currentDesigner.data.title" class="w-50 m-2"
                    placeholder="输入姓名" /></el-col>
        </el-row>
        <p>封面图:</p>
        <el-upload class="avatar-uploader" action="/admin/picture/push" name="picture" :show-file-list="false"
            :on-success="handleAvatarSuccess">
            <img v-if="currentDesigner.data.picture" :src="currentDesigner.data.picture" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
                <Plus />
            </el-icon>
        </el-upload>
        <p>介绍:</p>
        <div style="border: 1px solid #ccc;margin-top:20px">
            <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
                :mode="mode" />
            <Editor style="overflow-y: hidden;height: 500px;" v-model="currentDesigner.data.content"
                :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
        </div>
        <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
            <el-col :xs="8" :sm="2">
                <el-button type="primary" @click="modify" size="large" style="width:100%">修改</el-button>
            </el-col>
        </el-row>
    </div>

</template>

<script>
import { onBeforeUnmount, reactive, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import '@wangeditor/editor/dist/css/style.css'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import axios from 'axios'
export default {
    name: 'DesignerManage',
    components: { Editor, Toolbar, Plus },
    setup() {
        const designerList = ref([])
        const isModify = ref(false)
        const currentDesigner = reactive({ data: { id: '', title: '', picture: '' } })
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

        const handleAvatarSuccess = (response) => {
            currentDesigner.data.picture = response.data.url
        }
        const update = () => {
            axios.get('/designerIntroduce').then(res => {
                designerList.value = res.data.data
            })
        }
        const toModify=(designer)=>{
            isModify.value = !isModify.value
            currentDesigner.data=designer
        }
        const modify = () => {
            ElMessageBox.confirm(
                '确定修改内容吗？',
                '提示!',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }
            ).then(() => {
                axios.post('/admin/designerIntroduce/update', currentDesigner.data).then(res => {
                    if (res.data.code == 200)
                        ElMessage.success("更新成功!")
                    else ElMessage.error(res.data.msg)
                    update()
                    isModify.value = false
                    currentDesigner.data = { id: '', title: '', picture: '' }
                })
            }).catch(() => { })
        }

        update()
        return {
            designerList, modify, editorRef,
            mode: 'default', // 或 'simple'
            toolbarConfig,
            editorConfig,
            handleCreated,
            handleAvatarSuccess,
            isModify,
            currentDesigner,
            toModify
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