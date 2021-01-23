/*
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:32
 */
var menus = [
  {
    action: "home",
    title: "首页",
    path:"/index",
    items: [{ title: "最近消息", path: "/dashboard" }]
  },
  {
    action: "apps",
    title: "商品管理",
    path:"/item",
    items: [
      { title: "分类管理", path: "/category" },
      { title: "品牌管理", path: "/brand" },
      { title: "商品列表", path: "/list" },
      { title: "规格参数", path: "/specification" }
    ]
  },
  {
    action: "people",
    title: "用户管理",
    path:"/user",
    items: [
      { title: "用户统计", path: "/userlist" },
      { title: "用户数据", path: "/user" }
    ]
  },
  {
    action: "attach_money",
    title: "销售管理",
    path:"/trade",
    items: [
      { title: "仓库管理", path: "/stock" },
      { title: "订单管理", path: "/order" },
      { title: "物流管理", path: "/logistics" }
    //   { title: "促销管理", path: "/promotion" }
    ]
  },
  {
    action: "settings",
    title: "系统管理",
    path:"/system",
    items: [
      { title: "退出系统", path: "/loginOut" },
      { title: "管理员", path: "/admin" }
    ]
  }
]

export default menus;
