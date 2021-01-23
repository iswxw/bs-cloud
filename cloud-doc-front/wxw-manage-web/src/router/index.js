/*
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:33
 */
import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

function route(path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import('../pages' + file)
  };
}

export default new Router({
  routes: [
    route('/', '/Login', 'Login'), // /login路径，路由到登录组件
    {
      path: '/success', // 根路径，路由到 Layout组件
      component: () => import('../pages/Layout'),
      redirect: '/index/dashboard',

      children: [
        // 其它所有组件都是 Layout的子组件
        //相当于route("path","component",Name,[可以有子路由])
        route('/index/dashboard', '/Dashboard', 'Dashboard'),
        route('/item/category', '/item/Category', 'Category'),
        route('/item/brand', '/item/Brand', 'Brand'),
        route('/item/list', '/item/Goods', 'Goods'),
        route(
          '/item/specification',
          '/item/specification/Specification',
          'Specification'
        ),
        // 用户管理
        route('/user/user', '/user/User', 'User'),
        route('/user/userlist', '/user/UserList', 'UserList'),
        // 销售管理
        route('/trade/promotion', '/trade/Promotion', 'Promotion'),
        route('/trade/order', '/trade/Order', 'Order'),
        route('/trade/logistics', '/trade/Logistics', 'Logistics'),
        route('/trade/stock', '/trade/Stock', 'Stock'),
        //系统管理
        route('/system/loginOut', '/system/LoginOut', 'LoginOut'),
        route('/system/admin', '/system/Admin', 'Admin'),
      ]
    }
  ]
});
