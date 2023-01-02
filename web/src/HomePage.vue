<template>
  <el-container style="height:100vh;padding:0;">
    <el-header style="padding:0">
      <el-menu class="el-menu-demo" mode="horizontal" :router="true" :default-active="activeIndex">
        <div class="flex-grow" />
        <el-menu-item index=""><img src="/feixiong.png" style="width:124px"></el-menu-item>
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/introduce">公司简介</el-menu-item>
        <!-- <el-menu-item index="/goodmall">我们的产品</el-menu-item> -->
        <el-menu-item index="/anli">家装案例</el-menu-item>
        <el-menu-item index="/designer">设计师</el-menu-item>
        <el-menu-item index="/notice">通知</el-menu-item>
        <el-menu-item index="/connectus">联系我们</el-menu-item>
        <el-menu-item>
          <el-icon :size="25">
            <PhoneFilled />
          </el-icon><span style="font-size: 25px" v-cloak>{{
          hotPhone }}</span>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main style="padding:0;">
      <router-view></router-view> <!--这里的每个模块都高度固定，这样上面的导航栏就会一直在屏幕顶部。并且撑开容器，不让footer往上跑-->
      <FooterComp></FooterComp>
    </el-main>
  </el-container>
</template>

<script>
import { onMounted, provide, ref } from '@vue/runtime-core';
import axios from 'axios';
import FooterComp from './components/home/FooterComp.vue';
import { useRouter } from 'vue-router';

export default {
  name: "HomePage",
  setup() {
    let router = useRouter()
    let activeIndex=ref('/')
    activeIndex.value=router.currentRoute.value.path
    let hotPhone = ref();
    onMounted(() => {
      axios.get("/hotLine").then(res => {
        if (res.data.code == 200)
          hotPhone.value = res.data.data;
      });
    });
    let setTip=(path)=>{
      activeIndex.value=path
    }
    provide("setTip",setTip)
    return {
      hotPhone,activeIndex
    };
  },
  components: { FooterComp }
}
</script>

<style scoped>
.flex-grow {
  flex-grow: 0.5;
}
</style>