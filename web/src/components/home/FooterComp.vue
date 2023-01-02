<template>
    <el-row class="footer" :justify="'center'">
        <el-col :lg="5" :sm="8">
            <div>
            <h2>关于我们</h2>
            <p><a href="/connectus">联系我们</a></p>
            <p><a href="/introduce">公司简介</a></p>
            </div>
        </el-col>
        <el-col :lg="5" :sm="8">
            <h2>在线商城</h2>
            <p><a href="/">淘宝旗舰店</a></p>
            <p><a href="/">京东旗舰店</a></p>
        </el-col>
        <el-col :lg="5" :sm="8">
            <h2>联系我们</h2>
            <p class="info">咨询热线：{{ hotPhone }}</p>
            <p class="info">备案号：{{ beiAnHao }}</p>
            <p class="info">总部地址：{{ address }}</p>
        </el-col>
    </el-row>
</template>

<script>
import { onMounted, ref } from '@vue/runtime-core'
import axios from 'axios'
export default {
    name: 'FooterComp',
    setup() {
        let hotPhone = ref()
        let beiAnHao = ref()
        let address = ref()
        onMounted(() => {
            axios.get('/hotLine').then(res => {
                if (res.data.code == 200)
                    hotPhone.value = res.data.data
            })
            axios.get('/beiAnNumber').then(res => {
                if (res.data.code == 200)
                    beiAnHao.value = res.data.data
            })
            axios.get('/address').then(res => {
                if (res.data.code == 200)
                    address.value = res.data.data
            })
        })
        return {
            hotPhone,beiAnHao,address
        }
    }
}
</script>

<style scoped>
.footer{
    background-color: #00463d;
    height:250px;/*这里必须是250px,小了底下有白边 */
}
div{
    width:100%;
    text-align: center;
}
.footer
a {
    color: grey;
}

p {
  color: grey;
  cursor: pointer;
}
.info{
    color:white;
}
h2{
    color:white;
}
</style>