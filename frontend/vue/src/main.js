import Vue from 'vue';
import App from './App.vue';
import router from './router/router.js';
import store from './store/index.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import './assets/styles/global.css';
import '@fortawesome/fontawesome-free/css/all.css';
import 'bootstrap';

Vue.config.productionTip = false;
axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;


new Vue({
  router,
  store, // store를 Vue 인스턴스에 등록
  render: h => h(App),
}).$mount('#app');
