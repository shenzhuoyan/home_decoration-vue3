<template>
    <el-row :justify="'center'" style="min-height:calc(100% - 200px)">
        <el-col :lg="14">
            <div class="title-div">
                <span class="firstTitle">专属设计大咖/</span><span class="secondTitle">选对设计师才能让家居大放异彩</span>
            </div>
            <el-divider content-position="right">
            </el-divider>
            <el-row :gutter="20">
                <el-col :span="8" v-for="(item, index) in designers" :key="index" style="cursor: pointer;">
                    <el-card :body-style="{ padding: '0px' }" @click="handleCurrentChange(item.id)">
                        <img :src="item.picture" class="image">
                        <div style="padding: 14px;text-align: center">
                            <div style="font-size: 30px;" @click="open(item.id)">{{item.title}}</div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>

<script>
import { inject, onMounted, ref } from '@vue/runtime-core'
import axios from 'axios'
import { useRouter } from 'vue-router';
export default {
    name: 'DesignerComp',
    setup() {
        let designers = ref([])
        let router = useRouter()
        onMounted(() => {
            axios.get("/designerIntroduce").then(res => {
                if (res.data.code == 200)
                    designers.value = res.data.data
            })
        })
        const handleCurrentChange = (id) => {
            const routeData = router.resolve({
                path: '/designershow',query:{id}
            })
            window.open(routeData.href, '_blank')
        }
        const setTip = inject('setTip')
        setTip(router.currentRoute.value.path)
        return {
            //以下为后端内容
            designers, open,handleCurrentChange
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

.image {
    width: 100%;
    display: block;
}
</style>