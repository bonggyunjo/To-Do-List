// main.js
import Vue from 'vue';
import App from './App.vue';
import router from './router/router.js';
import store from './store/index.js'; // store를 올바르게 import
import 'bootstrap/dist/css/bootstrap.min.css';

Vue.config.productionTip = false;

new Vue({
  router,
  store, // store를 Vue 인스턴스에 등록
  render: h => h(App),
}).$mount('#app');
