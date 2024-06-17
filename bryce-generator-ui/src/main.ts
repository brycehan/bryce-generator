import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import VxeTable from "vxe-table"

import 'element-plus/dist/index.css'
import 'vxe-table/lib/style.min.css'
import VxeUI from 'vxe-pc-ui'
import 'vxe-pc-ui/lib/style.css'
import '@/assets/styles/index.scss'

const app = createApp(App)

app.use(router)

// element-plus
app.use(ElementPlus, {
  locale: zhCn
})
// 注册所有element-plus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(VxeUI)
app.use(VxeTable)

app.mount('#app')
