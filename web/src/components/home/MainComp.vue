<template>
    <el-row>
        <el-col :span="24">
            <BannerComp :pictures="bannerPictures"></BannerComp>
        </el-col>
    </el-row>

    <!--首页只要展示6个，但是要复用案例组件，所以用isHome来判断是否-->
    <AnLi :isHome="true">
        <router-link to="/anli"><span style="color:grey">更多案例</span></router-link>
    </AnLi>
    <!-- <GoodMall :isHome="true">
        <router-link to="/goodmall"><span style="color:grey">更多产品</span></router-link>
    </GoodMall> -->
    <NoticeInMain>
        <router-link to="/notice"><span style="color:grey">更多通知</span></router-link>
    </NoticeInMain>

    <DesignerComp></DesignerComp>

</template>

<script>
import axios from 'axios'
import AnLi from './AnLi.vue';
import NoticeInMain from './inMain/NoticeInMain.vue';
import DesignerComp from './DesignerComp.vue';
import { ref } from '@vue/reactivity';
import BannerComp from './inMain/BannerComp.vue';
// import GoodMall from './GoodMall.vue';

export default {
    name: "MainComp",
    setup() {
        let bannerPictures = ref(['']);
        axios.get("/banner").then(res => {
            if (res.data.code == 200)
                bannerPictures.value = res.data.data;
        });
        return {
            bannerPictures, BannerComp
        };
    },
    components: {
    AnLi,
    NoticeInMain,
    DesignerComp,
    BannerComp,
    // GoodMall
}
}
</script>

<style scoped>
.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>