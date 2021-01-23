/*
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:32
 */
import Vue from 'vue'
import axios from 'axios'
import config from './config'

axios.defaults.withCredentials=true;//让ajax携带cookie
axios.defaults.baseURL = config.api; // 设置axios的基础请求路径
axios.defaults.timeout = 20000; // 设置axios的请求时间

axios.interceptors.request.use(function (config) {
    let BS_TOKEN = window.localStorage.getItem("BS_TOKEN")
     if(BS_TOKEN){
        config.headers.BS_TOKEN=BS_TOKEN;
     }
  return config;
})

// get请求
axios.loadData = async function (url) {
  const resp = await axios.get(url);
  return resp.data;
}

// post请求 不用
Vue.prototype.$http = axios;// 将axios添加到 Vue的原型，这样一切vue实例都可以使用该对象

