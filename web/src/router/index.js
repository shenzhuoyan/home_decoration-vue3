import {
    createRouter,
    createWebHistory,
} from 'vue-router'

const routes = [
    // 路由的默认路径
    {
        path: '/',
        component: () => import('../HomePage'),
        children: [
            {
                path: '',
                component: () => import('../components/home/MainComp')
            },
            {
                path: 'anli',
                component: () => import('../components/home/AnLi')
            },
            {
                path: 'introduce',
                component: () => import('../components/home/AboutUs')
            },
            {
                path: 'connectus',
                component: () => import('../components/home/AboutUs')
            },
            {
                path: 'designer',
                component: () => import('../components/home/DesignerComp')
            },
            {
                path: 'notice',
                component: () => import('../components/home/NoticeComp')
            },
            {
                path:'noticeshow',
                component:()=>import('../components/home/singlePage/NoticeShow')
            },
            {
                path:'anlishow',
                component:()=>import('../components/home/singlePage/AnLiShow')
            },
            {
                path:'designershow',
                component:()=>import('../components/home/singlePage/DesignerShow')
            }
        ]
    },
    {
        path: '/manage',
        component: () => import('../ManageIndex'),
        meta:{requireAuth:true},
        children:[
            {
                path:'',
                component:()=>import('../components/admin/ShouyeManage'),
                meta:{requireAuth:true},
            },
            {
                path:'shouye',
                component:()=>import('../components/admin/ShouyeManage'),
                meta:{requireAuth:true},
            },
            {
                path:'anli',
                component:()=>import('../components/admin/AnLiManage'),
                meta:{requireAuth:true},
            },
            {
                path:'notice',
                component:()=>import('../components/admin/NoticeManage'),
                meta:{requireAuth:true},
            },
            {
                path:'designer',
                component:()=>import('../components/admin/DesignerManage'),
                meta:{requireAuth:true},
            }
        ]
    },
    {
        path:'/404',
        name:'404',
        component: ()=>import('../NoFound')
    },
    {
        path:'/admin',
        name:'login',
        component: ()=>import('../LoginPage')
    },
    {
        path:'/:pathMatch(.*)',
        redirect:'/404'
    },
]
// 创建路由对象
const router = createRouter({
    routes,
    history: createWebHistory(),//设置地址去掉#
})
//登录拦截
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
        //如果需要就执行下面的代码
        var isLogin = localStorage.getItem("isLogin")
        console.log(isLogin)
        // 通过sessionStorage 获取当前的isLogin的值 将我们保存的isLogin的值赋给num,num是顺便取的名称，可以换
        //我们在登录界面，我们使用请求，请求成功后，我们就使用sessionStorage为‘isLogin’保存一个值  1，如果请求失败，就不保存‘isLogin’的值
        //如果请求成功，num的值就是1，请求失败就是null，所以下面进行判断
        if (isLogin) {
            //如果登录了，就跳转到'/index'路径
            //next({path:'/admin'});
            next()
        } else {
            next({
                path: '/admin', //返回登录界面
                // query: {redirect: to.fullPath}  
            })
        }
    } else {
        //如果不需要登录权限就直接跳转到该路由
        next()
    }
})

export default router