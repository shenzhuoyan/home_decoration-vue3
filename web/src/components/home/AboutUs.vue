<template>
    <!--这里高度固定，这样上面的导航栏就会一直在屏幕顶部。并且撑开容器，不让footer往上跑-->
    <div id="main" v-html="text" style="min-height:calc(100% - 200px)">
    </div>
</template>

<script>
import axios from "axios"
import { inject, onMounted, ref } from '@vue/runtime-core'
import { useRouter } from 'vue-router';
export default {
    name: 'AboutUs',
    setup() {
        let router = useRouter()
        let text = ref('')
        onMounted(() => {
            axios.get('/describe').then(
                res => {
                    if (res.data.code == 200) text.value = res.data.data
                }
            )
        })
        const setTip = inject('setTip')//HomePage中定义的setTip方法，这里调用他，实现路由切换后同时切换导航栏的选中项
        //为什么不在HomePage中设置呢？因为只有路由切换时才需要设置导航栏选中项，但HomePage他自身是不变的。
        //即导航栏在HomePage中，但是其他页面也在HomePage里，切换路由的时候HomePage内部变了但本身没变，所以HomePage不会触发切换导航栏。
        setTip(router.currentRoute.value.path)
        return {
            text
        }
    }
}
</script>

<style scoped>
#main {
    margin-left: 15%;
    margin-right: 15%;
    margin-top: 200px;
    margin-bottom: 200px;
    min-height: calc(100vh - 531px);
}
</style>