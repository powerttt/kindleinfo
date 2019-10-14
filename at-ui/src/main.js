import Vue from 'vue'
import App from './App.vue'
import 'at-ui-style'
import AtUI from 'at-ui'
import Axios from 'axios'
import  axiosApi  from './api/axios.js'
Vue.use(AtUI)
Vue.use(axiosApi)
new Vue({
  el: '#app',
  ...App,
})
