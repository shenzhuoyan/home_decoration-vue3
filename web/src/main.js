import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import router from './router'
import axios from 'axios'

axios.interceptors.request.use((config) => {
    if (['/admin'].indexOf(config.url) === -1) {
      const token = localStorage.getItem('token')
      if (token) {
        //config.headers.Authorization = token
        config.headers.token=token
      }
    }
    return config
  })
  
  
  const app = createApp(App)
  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
      app.component(key, component)
    }
  
  app.use(ElementPlus)
  app.use(router)
  
  app.mount('#app')
