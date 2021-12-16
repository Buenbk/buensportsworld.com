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




import $ from 'jquery';
window.jQuery = $;
window.$ = $;

Vue.config.productionTip = false
Vue.use(VueAxios, axios)


new Vue({
  el: '#app',
  router,
  store,
  /*components: { App },
  template: '<App/>'*/
  components: {},
  template: '',

})

