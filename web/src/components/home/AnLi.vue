<template>
  <el-row :justify="'center'" :style="heightSetting">
    <el-col :lg="14">
      <div class="title-div">
        <span class="firstTitle">家装案例/</span><span class="secondTitle">精挑细选500例优秀案例</span>
      </div>
      <el-divider content-position="right">
        <slot></slot>
      </el-divider>
      <el-row :gutter="20">
        <el-col :lg="8" v-for="(item, index) in anLi" :key="index" class="anliP" @click="look(item.id)">
          <img :src="item.picture" @click="handleCurrentChange(item.id)" />
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios'
import { inject, ref } from '@vue/runtime-core'
import { useRouter } from 'vue-router';
export default {
  name: 'AnLi',
  props: ['isHome'],
  setup(props) {
    let anLi = ref()
    let router = useRouter()

    //如果没有嵌套在主页里，那么就需要设置最小高度以撑开页面
    const heightSetting = ref({ minHeight: 'calc(100% - 200px)' })
    if (props.isHome) {
      heightSetting.value = {}
      axios.get('/anLi/six').then(res => {
        if (res.data.code == 200)
          anLi.value = res.data.data
      })
    } else {
      axios.get('/anLi/all').then(res => {
        if (res.data.code == 200)
          anLi.value = res.data.data
      })
    }
    const setTip = inject('setTip')
    setTip(router.currentRoute.value.path)
    let look = (id) => {
      sessionStorage.setItem("anLiId", id)
      const routeData = this.$router.resolve({
        name: 'anlidetail'
      })
      window.open(routeData.href, '_blank')
    }

    const handleCurrentChange = (id) => {
      const routeData = router.resolve({
        path: 'anlishow', query: { id }
      })
      window.open(routeData.href, '_blank')
    }
    return {
      look, heightSetting,
      //以下为后端内容
      anLi,
      handleCurrentChange
    }
  }

}
</script>

<style scoped>
.anliP {
  cursor: pointer;
  margin-bottom: 20px;
}

img {
  width: 100%;
  height: 100%;
}

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