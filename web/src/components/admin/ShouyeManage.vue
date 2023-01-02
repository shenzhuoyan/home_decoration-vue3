<template>
  <el-tabs type="border-card">
    <el-tab-pane label="轮播图">
      <span
      >请确保图片尺寸一致，以免影响显示效果</span
    >
    <el-divider />
      <el-upload class="avatar-uploader" v-model:file-list="bannerList" action="/admin/picture/push" name="picture"
        list-type="picture-card" :on-preview="handlePictureCardPreview"
        :before-remove="handleRemove" :on-success="pushOK" style="width:100%">
        <el-icon>
          <Plus />
        </el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible" fullscreen="true">
        <img w-full :src="dialogImageUrl" alt="Preview Image" style="width:100%"/>
      </el-dialog>
    </el-tab-pane>
    <el-tab-pane label="电话">
      <el-input v-model="hotLine" placeholder="请输入" />
      <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
        <el-col :xs="8" :sm="2">
          <el-button type="primary" @click="okHotLine" size="large" style="width:100%">修改</el-button>
        </el-col>
        <el-col :xs="8" :sm="2">
          <el-button type="default" @click="quitHotLine" size="large" style="width:100%">取消</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="备案号">
      <el-input v-model="beiAnNumber" placeholder="请输入" />
      <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
        <el-col :xs="8" :sm="2">
          <el-button type="primary" @click="okBeiAnNumber" size="large" style="width:100%">修改</el-button>
        </el-col>
        <el-col :xs="8" :sm="2">
          <el-button type="default" @click="quitBeiAnNumber" size="large" style="width:100%">取消</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="地址">
      <el-input v-model="address" placeholder="请输入" />
      <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
        <el-col :xs="8" :sm="2">
          <el-button type="primary" @click="okAddress" size="large" style="width:100%">修改</el-button>
        </el-col>
        <el-col :xs="8" :sm="2">
          <el-button type="default" @click="quitAddress" size="large" style="width:100%">取消</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="简介">
      <div style="border: 1px solid #ccc">
        <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
          :mode="mode" />
        <Editor style="overflow-y: hidden;height:calc(100vh - 215px)" v-model="introduce" :defaultConfig="editorConfig"
          :mode="mode" @onCreated="handleCreated" />
      </div>
      <el-row :justify="'center'" :gutter="10" style="margin-top:20px">
        <el-col :xs="8" :sm="2">
          <el-button type="primary" @click="okIntroduce" size="large" style="width:100%">修改</el-button>
        </el-col>
        <el-col :xs="8" :sm="2">
          <el-button type="default" @click="quitIntroduce" size="large" style="width:100%">取消</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { onBeforeUnmount, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import '@wangeditor/editor/dist/css/style.css'
import axios from 'axios'
export default {
  name: 'ShouyeManage',
  components: { Editor, Toolbar },
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



    /* 
    信息初始化
    */
    // 轮播图
    const bannerList=ref([])
    const preBannerList=ref([])
    //电话
    const hotLine = ref('')
    const preHotLine = ref('')
    //备案号
    const beiAnNumber = ref('')
    const preBeiAnNumber = ref('')
    //地址
    const address = ref('')
    const preAddress = ref('')
    // 简介
    const introduce = ref('')
    const preIntroduce = ref('')

    // 获取轮播图
    const getBanner=()=>{
      axios.get("/banner").then(res=>{
        preBannerList.value=bannerList.value=res.data.data
      })
    }
    //获取电话
    const getHotLine = () => {
      axios.get("/hotLine").then(res => {
        preHotLine.value = hotLine.value = res.data.data
      })
    }
    //获取备案号
    const getBeiAnNumber = () => {
      axios.get("/beiAnNumber").then(res => {
        preBeiAnNumber.value = beiAnNumber.value = res.data.data
      })
    }
    //获取地址
    const getAddress = () => {
      axios.get("/address").then(res => {
        preAddress.value = address.value = res.data.data
      })
    }
    //获取简介
    const getIntroduce = () => {
      axios.get("/describe").then(res => {
        preIntroduce.value = introduce.value = res.data.data
      })
    }

    //电话的取消
    const quitHotLine = () => {
      hotLine.value = preHotLine.value
    }
    //备案号的取消
    const quitBeiAnNumber = () => {
      beiAnNumber.value = preBeiAnNumber.value
    }
    //地址修改的取消按钮
    const quitAddress = () => {
      address.value = preAddress.value
    }
    //简介的取消按钮
    const quitIntroduce = () => {
      introduce.value = preIntroduce.value
    }


    //抽取出提交和发起请求的方法
    const mySubmit = (url, params) => {
      ElMessageBox.confirm(
        '确定修改吗？',
        '警告!',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        //确认
        axios.post(url, params).then(
          res => {
            if (res.data.code == 200) {
              ElMessage({ type: 'success', message: '已修改！' })
              setTimeout(()=>{update()},1500)
            }
            else {
              ElMessage({ type: 'info', message: res.data.msg })
            }
          }
        )
      }
      )
    }

    //电话的确定
    const okHotLine = () => {
      if (preHotLine.value != hotLine.value)
        mySubmit('/admin/hotLine/add', { hotLine: hotLine.value })
    }
    //备案号的确定
    const okBeiAnNumber = () => {
      if (preBeiAnNumber.value != beiAnNumber.value)
        mySubmit('/admin/beiAnNumber/add', { beiAnNumber: beiAnNumber.value })
    }
    //地址的确定按钮
    const okAddress = () => {
      if (preAddress.value != address.value)
        mySubmit('/admin/address/add', { address: address.value })
    }
    //简介的确定按钮
    const okIntroduce = () => {
      if (preIntroduce.value != introduce.value)
        mySubmit('/admin/describe/add', { describe: introduce.value })
    }

    //初始化,更新数据
    const update = () => {
      getIntroduce()
      getAddress()
      getBeiAnNumber()
      getHotLine()
      getBanner()
    }
    update()

    const dialogImageUrl = ref('')
    const dialogVisible = ref(false)

    //图片删除前触发
    const handleRemove = (uploadFile) => {
      ElMessageBox.confirm(
        '确定删除图片吗？',
        '警告!',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        //确认
        axios.get('/admin/banner/del?id='+uploadFile.name).then(
          res => {
            if (res.data.code == 200) {
              ElMessage({ type: 'success', message: '已删除！' })
              update()
            }
            else {
              ElMessage({ type: 'info', message: res.data.msg })
              return false
            }
          }
        )
      }
      ).catch(()=>{return false})
      return false
    }

    //图片上传成功触发
    const pushOK=(response)=>{
      axios.post('/admin/banner/add?picture='+response.data.url)
      ElMessage({type:'success',message:'上传成功'})
      update()
    }
    //图片预览
    const handlePictureCardPreview = (uploadFile) => {
      console.log(uploadFile.url)
      dialogImageUrl.value = uploadFile.url
      dialogVisible.value = true
    }
    return {
      editorRef,
      introduce,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      quitIntroduce,
      okIntroduce,
      address,
      quitAddress,
      okAddress,
      beiAnNumber,
      quitBeiAnNumber,
      okBeiAnNumber,
      hotLine,
      okHotLine,
      quitHotLine,
      handleRemove,
      handlePictureCardPreview,
      dialogImageUrl,
      dialogVisible,
      bannerList,
      pushOK
    };
  }
}
</script>

<style>

</style>