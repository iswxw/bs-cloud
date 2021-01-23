import Vue from "vue";
import axios from "axios";

axios.defaults.baseURL = "http://api.wxw.com/api"; // 设置axios的基础请求路径
axios.defaults.timeout = 20000; // 设置axios的请求时间

// get请求
axios.loadData = async function(url) {
  const resp = await axios.get(url);
  return resp.data;
};

// post请求 不用
Vue.prototype.$http = axios; // 将axios添加到 Vue的原型，这样一切vue实例都可以使用该对象
