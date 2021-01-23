/*
 * @Descripttion:
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-04-13 16:29:18
 */
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
//import axios from "axios";
import "element-ui/lib/theme-chalk/index.css";
import Toast from "./tools/toast.js";
import "./tools/http";
import VueLazyload from "vue-lazyload";
import VueCookie from "vue-cookie";
import "./assets/iconfont/iconfont.css";

// 全局请求拦截
// axios.interceptors.request.use(
//   config => {
//     return config;
//   },
//   error => {
//     // 跳转error页面
//     router.push({ path: "/error" });
//     return Promise.reject(error);
//   }
// );

// 全局响应拦截器
// axios.interceptors.response.use(
//   res => {
//     if (res.data.code === "401") {
//       // 401表示没有登录
//       // 提示没有登录
//       Vue.prototype.notifyError(res.data.msg);
//       // 修改vuex的showLogin状态,显示登录组件
//       store.dispatch("setShowLogin", true);
//     }
//     if (res.data.code === "500") {
//       // 500表示服务器异常
//       // 跳转error页面
//       router.push({ path: "/error" });
//     }
//     return res;
//   },
//   error => {
//     // 跳转error页面
//     router.push({ path: "/error" });
//     return Promise.reject(error);
//   }
// );

// 全局拦截器,在进入需要用户权限的页面前校验是否已经登录
router.beforeResolve((to, from, next) => {
  const loginUser = store.state.user.user;
  // 判断路由是否设置相应校验用户权限
  if (to.meta.requireAuth) {
    if (!loginUser) {
      // 没有登录，显示登录组件
      store.dispatch("setShowLogin", true);
      if (from.name == null) {
        //此时，是在页面没有加载，直接在地址栏输入链接，进入需要登录验证的页面
        next("/");
        return;
      }
      // 终止导航
      next(false);
      return;
    }
  }
  next();
});

// 相对时间过滤器,把时间戳转换成时间
// 格式: 2020-02-25 21:43:23
Vue.filter("dateFormat", dataStr => {
  var time = new Date(dataStr);
  function timeAdd0(str) {
    if (str < 10) {
      str = "0" + str;
    }
    return str;
  }
  var y = time.getFullYear();
  var m = time.getMonth() + 1;
  var d = time.getDate();
  var h = time.getHours();
  var mm = time.getMinutes();
  var s = time.getSeconds();
  return (
    y +
    "-" +
    timeAdd0(m) +
    "-" +
    timeAdd0(d) +
    " " +
    timeAdd0(h) +
    ":" +
    timeAdd0(mm) +
    ":" +
    timeAdd0(s)
  );
});

import axios from "axios";
axios.defaults.withCredentials=true;//让ajax携带cookie
Vue.prototype.$axios = axios;

// 全局组件
import MyLogin from "./components/MyLogin.vue";
Vue.component(MyLogin.name, MyLogin);
import MyRegister from "./components/MyRegister.vue";
Vue.component(MyRegister.name, MyRegister);  // 分类
import MyList from "./components/MyList.vue"
Vue.component(MyList.name,MyList);
import MyList2 from "./components/MyList2.vue"; // 首页特殊商品展示
Vue.component(MyList2.name,MyList2); 
import EsList from "./components/EsList.vue"; // ES结果集商品展示
Vue.component(EsList.name,EsList); 


Vue.config.productionTip = false;
Vue.use(VueLazyload, {
  loading: "/imgs/loading-svg/loading-bars.svg"
});

Vue.use(VueCookie);
// ElementUI
Vue.use(ElementUI);
// 全局函数及变量
Vue.use(Toast);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
