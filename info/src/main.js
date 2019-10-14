import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'

import Axios from 'axios'
import  axiosApi  from './api/axios.js'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'

Vue.use(axiosApi)
Vue.use(ElementUI)
Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
