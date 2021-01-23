/*
 * @Descripttion:
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-04-13 16:29:18
 */
import Vue from "vue";
import Router from "vue-router";
import Home from "../pages/Home";
import Error from "../components/Error";
import Goods from "../pages/Goods"
import About from "../pages/About"
import Detail from "../pages/Detail.vue"
import ShoppingCart from "../pages/ShoppingCart.vue"
import ConfirmOrder from "../pages/confirmOrder.vue"
import Order from "../pages/Order.vue"
import Alipay from "../pages/Alipay.vue"

Vue.use(Router);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/error",
    name: "Error",
    component: Error
  },
  {
    path: "/goods",
    name: "Goods",
    component: Goods
  },
  {
    path: "/about",
    name: "About",
    component: About
  },
  {
    path: "/goods/details",
    name: "Detail",
    component: Detail
  },
  {
    path: "/shoppingCart",
    name: "shoppingCart",
    component: ShoppingCart
  },
  {
    path: "/confirmOrder",
    name: "confirmOrder",
    component: ConfirmOrder
  },
  {
    path: "/order",
    name: "Order",
    component: Order
  }, {
    path: "/alipay",
    name: "Alipay",
    component: Alipay
  },

];

const router = new Router({
  // mode: 'history',
  routes
});

/* 由于Vue-router在3.1之后把$router.push()方法改为了Promise。所以假如没有回调函数，错误信息就会交给全局的路由错误处理。
vue-router先报了一个Uncaught(in promise)的错误(因为push没加回调) ，然后再点击路由的时候才会触发NavigationDuplicated的错误(路由出现的错误，全局错误处理打印了出来)。*/
// 禁止全局路由错误处理打印
const originalPush = Router.prototype.push;
Router.prototype.push = function push (location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
};

export default router;
