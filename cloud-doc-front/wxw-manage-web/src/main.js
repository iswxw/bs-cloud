/*
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:32
 */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import config from './config'
import MyComponent from './components/MyComponent'
import './http';
import 'vuetify/dist/vuetify.min.css'
import qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/material.css'
// 引入部分工具
import { Button, Tabs,TabPane,TableColumn,Table } from 'element-ui';
Vue.use(Button);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.use(TableColumn);
Vue.use(Table);
// 使用Vuetify
Vue.use(Vuetify, { theme: config.theme})
Vue.use(MyComponent)
Vue.prototype.$qs = qs;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
