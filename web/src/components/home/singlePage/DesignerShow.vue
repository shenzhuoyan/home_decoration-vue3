<template>
    <div id="main">
        <div id="my-title">
            <h1>{{ designer.val.title }}</h1>
            <p>{{ designer.val.date }}</p>
        </div>
        <el-row :justify="'center'" style="margin-bottom:20px">
            <el-col :span="10"><img :src="designer.val.picture" style="width:100%"/></el-col>
        </el-row>
        <el-card class="box-card" style="margin-bottom:20px">
            <div v-html="designer.val.content"></div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'
import { reactive } from '@vue/reactivity';
export default {
    name: 'DesignerShow',
    setup() {
        const GetRequest = (value) => {
            var url = decodeURI(window.location.search); //?id="123456"&name="www";
            var object = {};
            if (url.indexOf("?") != -1)//url中存在问号，也就说有参数。  
            {
                var str = url.substr(1);  //得到?后面的字符串
                var strs = str.split("&");  //将得到的参数分隔成数组[id="123456",name="www"];
                for (var i = 0; i < strs.length; i++) {
                    object[strs[i].split("=")[0]] = strs[i].split("=")[1];//得到{id:'123456',name:'www'}
                }
            }
            return object[value];
        }

        // axios.get("/designer/get?id=" + sessionStorage.getItem("designerId")).then(res => {
        //     designer.value = res.data
        // })
        const designer = reactive({
            val: { id: -1, title: '标题', date: '', introduction: '', content: '' }
        });
        axios.get("/designerIntroduce/get?id=" + GetRequest('id')).then(res => {
            designer.val = res.data.data
        })
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
        return {
            designer, statusFilter, statusTag
        }
    }
}
</script>

<style scoped>
#main {
    margin-left: 15%;
    margin-right: 15%;
    min-height: calc(100vh - 250px);
}

#my-title {
    text-align: center;
    margin-bottom: 100px;
}

#designer {
    font-size: 20px;
    cursor: pointer;
}
</style>