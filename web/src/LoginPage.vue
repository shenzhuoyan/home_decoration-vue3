<template>
    <!--整个窗口-->
    <div class="main" @keyup.enter="login" >
        <el-row :justify="'center'" :align="'middle'" style="height:100%">
            <el-col :sm="12" :lg="8" :xl="6">
                <!--中间的白框-->
                <div id="center-box">
                    <el-row :align="'middle'" :justify="'center'">
                        <el-col :lg="20">
                            <el-row :justify="'center'" :align="'middle'" style="margin-bottom:20px;margin-top:20px">
                                <el-col :lg="18">
                                    <img src="feixiong.png" style="width:100%" />
                                </el-col>
                            </el-row>
                            <el-row style="margin-bottom:20px">
                                <el-col :lg="6">
                                    用户名
                                </el-col>
                                <el-col :lg="18">
                                    <el-input v-model="userName" placeholder="输入用户名" />
                                </el-col>
                            </el-row>
                            <el-row style="margin-bottom:30px">
                                <el-col :lg="6">
                                    密码
                                </el-col>
                                <el-col :lg="18">
                                    <el-input v-model="password" type="password" placeholder="输入密码" show-password />
                                </el-col>
                            </el-row>
                            <el-row :justify="'center'">
                                <el-col :lg="8">
                                    <el-button round type="primary" @click="login" size="large"
                                        style="width:100%">登录</el-button>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
        </el-row>
    </div>

</template>

<script>
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ref } from '@vue/reactivity'
import { ElNotification, ElMessageBox } from 'element-plus'
export default {
    name: 'LoginPage',
    setup() {
        const router = useRouter()
        //读取本地数据，判断是否已登录，是则直接跳转到管理页
        if (localStorage.getItem("isLogin"))
            router.push('/manage').catch(err => { err })

        const userName = ref('')
        const password = ref('')
        const login = () => {
            if (userName.value == '' || password.value == '') {
                ElMessageBox.alert('用户名和密码必须填写', '输入为空', {
                    confirmButtonText: '取消'
                })
            }
            else {
                axios.get("/adminLogin?name=" + userName.value + "&password=" + password.value).then(res => {
                    if (res.data.code==200) {
                        //登录成功在本地写入已登录，并保存token
                        localStorage.setItem("isLogin", true)
                        localStorage.setItem("token", res.data.data)
                        //axios.defaults.headers.common['token'] = res.data
                        router.push({
                            path: '/manage',
                        }).catch(err => { err })
                    }
                    else
                        ElNotification({
                            type: 'error',
                            title: '登录失败',
                            message: res.data.msg
                        });
                }).catch(() => {
                    ElNotification({
                        type: 'error',
                        title: '登录失败',
                        message: '网络异常'
                    });
                })
            }
        }

        return {
            userName,
            password,
            login
        }
    }
}
</script>

<style scoped>
.main {
    height: 100vh;
    width: 100%;
    background: url('~@/assets/login.jpg');
}

#center-box {
    background-color: white;
    padding: 20px 20px 20px 20px;
    border-radius: 20px;
}
</style>