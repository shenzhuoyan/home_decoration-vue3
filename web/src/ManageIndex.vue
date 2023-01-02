<template>
  <el-row>
    <el-col :xs="8" :sm="5" :lg="3">
      <el-menu class="el-menu-vertical-demo" :default-active="activeIndex" @open="handleOpen" @close="handleClose"
        style="height:100vh" background-color="#545c64" text-color="#fff" router>
        <el-menu-item>
          <img src="../public/feixiong_white.png" style="width:120px" />
        </el-menu-item>
        <el-menu-item index="/manage">
          <span>
            <h2>欢迎!</h2>
          </span>
        </el-menu-item>
        <el-menu-item index="/manage/shouye">
          首页管理
        </el-menu-item>
        <el-menu-item index="/manage/anli">
          案例管理
        </el-menu-item>
        <!-- <el-menu-item index="4">
          产品管理
        </el-menu-item> -->
        <el-menu-item index="/manage/notice">
          通知管理
        </el-menu-item>
        <el-menu-item index="/manage/designer">
          首页设计师
        </el-menu-item>
        <el-menu-item >
          <el-button @click="exit" type="info">退出</el-button>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :xs="16" :sm="19" :lg="21">
      <router-view></router-view>
    </el-col>
  </el-row>

</template>

<script>
import { useRouter } from 'vue-router'
import {
  Document,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { ref } from '@vue/reactivity'
export default {
  name: 'ManageIndex',
  setup() {
    const router = useRouter()
    //读取本地数据，判断是否已登录，否则跳转到登录页
    if (!localStorage.getItem("isLogin"))
      router.push('/admin').catch(err => { err })
    const handleOpen = (key, keyPath) => {
      console.log(key, keyPath)
    }
    const handleClose = (key, keyPath) => {
      console.log(key, keyPath)
    }

    const exit = () => {
      localStorage.removeItem("isLogin")
      localStorage.removeItem("token")
      router.push('/').catch(err => { err })
    }
    let activeIndex = ref('/manage')
    activeIndex.value = router.currentRoute.value.path
    return {
      Document, Location, Setting, handleOpen, handleClose, activeIndex, exit
    }
  }
}
</script>

<style>

</style>