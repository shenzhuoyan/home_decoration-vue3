<template>
    <el-row :justify="'center'">
        <el-col :lg="14">
            <div class="title-div">
                <span class="firstTitle">消息公告/</span><span class="secondTitle">最新的消息，确保交流的畅通</span>
            </div>
            <el-divider content-position="right">
                <slot></slot>
            </el-divider>
            <el-row>
                <el-col :lg="12">
                    <h3>最新消息</h3>
                    <el-table :data="newest" style="width: 100%" @row-click="handleCurrentChange">
                        <el-table-column prop="title" label="标题" show-overflow-tooltip />
                        <el-table-column prop="date" label="日期" width="180" />
                    </el-table>
                </el-col>
                <el-col :lg="12">
                    <h3>重要公告</h3>
                    <el-table :data="important" style="width: 100%" @row-click="handleCurrentChange">
                        <el-table-column prop="title" label="标题" show-overflow-tooltip />
                        <el-table-column prop="date" label="日期" width="180" />
                    </el-table>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>

<script>
import { ref } from '@vue/reactivity'
import { onMounted } from '@vue/runtime-core'
import { useRouter } from 'vue-router';
import axios from 'axios'
export default {
    name: 'NoticeInMain',
    setup() {
        let newest = ref([]);
        let important = ref([])
        let router = useRouter()
        onMounted(() => {
            axios.get("/noticeList/newest").then(res => {
                if (res.data.code == 200)
                    newest.value = res.data.data
            })
            axios.get("/noticeList/important").then(res => {
                if (res.data.code == 200)
                    important.value = res.data.data
            })
        })
        let handleCurrentChange = (row) => {
            const routeData = router.resolve({
                path: 'noticeshow', query: { id: row.id }
            })
            window.open(routeData.href, '_blank')
        }
        return {
            newest, important, handleCurrentChange
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