<template>
    <div id="main">
        <div id="my-title">
            <h1>{{ anLi.val.title }}</h1>
            <p>发布日期：{{ anLi.val.date }}</p>
            <p>设计师：<el-button text @click="handleCurrentChange(anLi.val.designer.id)">{{ anLi.val.designer.title }}</el-button></p>
        </div>
        <el-card class="box-card">
            <div v-html="anLi.val.content"></div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'
import { reactive } from '@vue/reactivity';
import { useRouter } from 'vue-router';
export default {
    name: 'AnLiShow',
    setup() {
        let router = useRouter()
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

        // axios.get("/anLi/get?id=" + sessionStorage.getItem("anLiId")).then(res => {
        //     anLi.value = res.data
        // })
        const anLi = reactive({
            val: { id: -1, title: '标题', date: '', content: '' ,designer:{id:'',title:''}}
        });
        axios.get("/anLi/get?id=" + GetRequest('id')).then(res => {
            anLi.val = res.data.data
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
        const handleCurrentChange = (id) => {
            const routeData = router.resolve({
                path: '/designershow',query:{id}
            })
            window.open(routeData.href, '_blank')
        }
        return {
            anLi, statusFilter, statusTag,handleCurrentChange
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