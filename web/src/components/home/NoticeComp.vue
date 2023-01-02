<template>
    <el-row :justify="'center'" style="min-height:calc(100% - 200px)">
        <el-col :lg="14">
            <div class="title-div">
                <span class="firstTitle">通知公告/</span><span class="secondTitle">最新的通知，确保交流的畅通</span>
            </div>
            <el-divider content-position="right">
            </el-divider>
            <div v-for="item in noticeList" :key="item.id" @click="handleCurrentChange(item.id)"
                style="cursor: pointer; height:40px;border-bottom:#E8E8E8 solid 1px;margin-bottom:20px">
                <div style="width:40%;float:left;font-size:18px;margin-left:50px">{{item.title}}</div>
                <div style="width:30%;float:left;">
                    <el-tag :type="statusTag(item.status)">{{statusFilter(item.status)}}</el-tag>
                </div>
                <div style="width:20%;float:left;font-size:18px;color:grey;">{{item.date}}</div>
            </div>

            <div style="margin-top: 20px;text-align:center;margin-bottom: 20px;">
                <el-button v-show="pageNumber>2&&currentPage!=1" @click="reload(1)">第一页</el-button>
                <el-button v-for="index in pageNumber" :key="index" @click="reload(index)" plain>{{index}}</el-button>
                <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="reload(pageNumber)">最后一页</el-button>
            </div>
        </el-col>
    </el-row>
</template>

<script>
import { inject, ref } from '@vue/runtime-core';
import { useRouter } from 'vue-router';
import axios from 'axios';
export default {
    name: 'NoticeComp',
    setup() {
        let router = useRouter()
        const setTip = inject('setTip')
        setTip(router.currentRoute.value.path)
        let noticeList = ref([''])
        let currentPage = ref(0)
        let pageNumber = ref(0)
        let reload = (num) => {
            axios.get("/noticeList/page").then(res => {
                if (res.data.code == 200)
                    pageNumber.value = res.data.data
                else console.log(res.data.msg)
            })
            axios.get("/noticeList?page=" + num).then(res => {
                if (res.data.code == 200) {
                    noticeList.value = res.data.data
                    currentPage.value = num
                }
                else console.log(res.data.msg)
            })
        }
        reload(1) //刷新页面内容

        //以方法来实现过滤器的作用，也就是根据对应状态返回中文的状态文字
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
        let handleCurrentChange = (id) => {
            const routeData = router.resolve({
                path: 'noticeshow',query:{id}
            })
            window.open(routeData.href, '_blank')
        }
        return {
            noticeList, handleCurrentChange, statusFilter, statusTag, pageNumber,reload
        }
    }
}
</script>

<style scoped>
.title-div {
    margin-top: 30px;
}

.firstTitle {
    font-size: 30px;
}

.secondTitle {
    font-size: 15px;
    color: grey;
}
</style>