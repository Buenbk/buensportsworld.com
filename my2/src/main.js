// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from "vue-axios";
import store from "./components/store/store";
import "./styles/common.css";
import "./styles/main.css"
import "./styles/comment.css"
import "./styles/Button.css"
import "./styles/myOther.css"
import "./styles/Hamster.css"
import "./styles/card.css"
import "./styles/loader.css"


/*Vue.prototype.$axios=axios
import ElementPlus from 'element-plus';//1.引入组件
import 'element-plus/dist/index.css'

import { ElLoading } from 'element-plus';
Vue.use(ElementPlus)
*/


import $ from 'jquery';
window.jQuery = $;
window.$ = $;

Vue.config.productionTip = false
Vue.use(VueAxios, axios)

//Vue.use(ElementUI)


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  /*components: { App },
  template: '<App/>'*/
  components: {},
  template: '',

})


/*// 定义一个请求次数的变量，用来记录当前页面总共请求的次数
let loadingRequestCount = 0;
// 初始化loading
let loadingInstance;


const showLoading = (target) => {
  if (loadingRequestCount === 0) {
    // element的服务方式 target 我这边取的是表格class
    // 类似整个表格loading和在表格配置v-loading一样的效果，这么做是全局实现了，不用每个页面单独去v-loading
    loadingInstance = ElLoading.service({ target });
  }
  loadingRequestCount++
}

// 编写一个隐藏loading的函数，并且记录请求次数 --
const hideLoading = () => {
  if (loadingRequestCount <= 0) return
  loadingRequestCount--
  if (loadingRequestCount === 0) {
    loadingInstance.close();
  }
}

export {
  showLoading,
  hideLoading
}

//请求数据拦截器
axios.interceptors.request.use(request => {
  showLoading();
  return request
}, err => {
  return Promise.reject(err);
});


//接收响应拦截器
axios.interceptors.response.use(response => {
  hideLoading();
  return response
}, err => {
  /!*endLoading();
  if (err && err.response) {
    switch (err.response.status) {
      case 400: err.message = '请求错误(400)'; break;
      case 401: this.$router.push('/login'); break;
      case 403: err.message = '拒绝访问(403)'; break;
      case 404: err.message = '请求出错(404)'; break;
      case 408: err.message = '请求超时(408)'; break;
      case 500: err.message = '服务器错误(500)'; break;
      case 501: err.message = '服务未实现(501)'; break;
      case 502: err.message = '网络错误(502)'; break;
      case 503: err.message = '服务不可用(503)'; break;
      case 504: err.message = '网络超时(504)'; break;
      case 505: err.message = 'HTTP版本不受支持(505)'; break;
      default: err.message = `连接出错(${err.response.status})!`;
    }
  } else {
    err.message = '连接服务器失败!'
  }
  message.error(err.message);
  return Promise.reject(err);*!/
});*/
