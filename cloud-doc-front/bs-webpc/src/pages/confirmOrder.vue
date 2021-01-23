<!--
 * @Descripttion: 确认订单页面组件
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-14 16:28:19
 -->

<template>
  <div class="confirmOrder">
    <!-- 头部 -->
    <div class="confirmOrder-header">
      <div class="header-content">
        <p>
          <i class="el-icon-s-order"></i>
        </p>
        <p>确认订单</p>
        <router-link to></router-link>
      </div>
    </div>
    <!-- 头部END -->

    <!-- 主要内容容器 -->
    <div class="content">
      <!-- 选择地址 -->
      <div class="section-address">
        <p class="title">收货地址</p>
        <div class="address-body">
          <ul>
            <li
              :class="item.id == confirmAddress ? 'in-section' : ''"
              v-for="item in address"
              :key="item.id">
              <h2>收货人：{{item.name}}</h2>
              <p class="phone">联系电话：{{item.phone}}</p>
              <p class="address">所在城市：{{item.city}}</p>
              <p class="address">详细地址：{{item.address}}</p>
              <p class="default">默认地址：{{item.defaultAddress?"是":"备选地址"}}</p>
            </li>
            <li class="add-address">
              <i class="el-icon-circle-plus-outline"></i>
              <p>添加新地址</p>
            </li>
          </ul>
        </div>
      </div>
      <!-- 选择地址END -->

      <!-- 商品及优惠券 -->
      <div class="section-goods">
        <p class="title">商品及优惠券</p>
        <div class="goods-list">
          <ul>
            <li v-for="item in orders" :key="item.id">
              <img :src="item.image" />
              <span class="pro-name">{{item.title}}</span>
              <span class="pro-price">{{item.price}}元 x {{item.num}}</span>
              <span class="pro-status">待支付</span>
              <span class="pro-total">{{item.price * item.num}}元</span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 商品及优惠券END -->

      <!-- 配送方式 -->
      <div class="section-shipment">
        <p class="title">配送方式</p>
        <p class="shipment">包邮</p>
      </div>
      <!-- 配送方式END -->

      <!-- 发票 -->
      <div class="section-invoice">
        <p class="title">发票</p>
        <p class="invoice">电子发票</p>
        <p class="invoice">个人</p>
        <p class="invoice">商品明细</p>
      </div>
      <!-- 发票END -->

      <!-- 结算列表 -->
      <div class="section-count">
        <div class="money-box">
          <ul>
            <li>
              <span class="title">商品件数：</span>
              <span class="value">{{counts}}件</span>
            </li>
            <li>
              <span class="title">商品总价：</span>
              <span class="value">{{totals}}元</span>
            </li>
            <!-- <li>
              <span class="title">活动优惠：</span>
              <span class="value">-0元</span>
            </li>
            <li>
              <span class="title">优惠券抵扣：</span>
              <span class="value">-0元</span>
            </li> -->
            <li>
              <span class="title">运费：</span>
              <span class="value">1元</span>
            </li>
            <li class="total">
              <span class="title">应付总额：</span>
              <span class="value">
                <span class="total-price">{{totals+1}}</span>元
              </span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 结算列表END -->

      <!-- 结算导航 -->
      <div class="section-bar">
        <div class="btn">
          <router-link to="/shoppingCart" class="btn-base btn-return">返回购物车</router-link>
          <a href="javascript:void(0);" @click="addOrder" class="btn-base btn-primary">结算</a>
        </div>
      </div>
      <!-- 结算导航END -->
    </div>
    <!-- 主要内容容器END -->
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mapActions } from "vuex";
export default {
  name: "",
  data() {
    return {
      // 虚拟数据
      confirmAddress: 1, // 选择的地址id
      //订单数据
      orders:[],
      //商品件数
      counts:0,
      //总价格
      totals:0,
      // 支付回调也
      htmls:"",
      // 地址列表
      address: [
        {
          id: 1,
          name: "魏小伟",
          phone: "13580018623",
          address: "北京 朝阳区"
        }
      ],
      orderIds:[],
    };
  },
  created() {
     //获取收获地址
     this.getAddress();
     // 订单
     this.getPreOrder();

    // 如果没有勾选购物车商品直接进入确认订单页面,提示信息并返回购物车
    if (this.getCheckNum < 1) {
      this.notifyError("请勾选商品后再结算");
      this.$router.push({ path: "/shoppingCart" });
    }
  },
  computed: {
    // 结算的商品数量; 结算商品总计; 结算商品信息
    ...mapGetters(["getCheckNum", "getTotalPrice", "getCheckGoods"])
  },
  methods: {
    ...mapActions(["deleteShoppingCart"]),
     //创建定订单
      addOrder() {
       
      //封装订单入参
      let order ={
            "totalPay": this.totals,
            "postFee": 1,
            "paymentType": 2,
            "actualPay": this.totals,
            "buyerMessage": "测试IT",
            "buyerNick": "Cloud",
            "orderDetails":this.orders||[],
            "receiver": this.address[0].name,
            "receiverMobile": this.address[0].phone,
            "receiverState": 1,
            "receiverCity":this.address[0].city,
            "receiverDistrict": this.address[0].district,
            "receiverAddress": this.address[0].address,
            "receiverZip": this.address[0].zipcode,
            "invoiceType": 0,
            "sourceType":2
            };
      // 生成订单 
      this.$http.post("/order",order).then(res => {
          this.orderIds =res.data;
           // 创建成功则前往支付宝支付
           this.$http.get("/order/goAlipay",{
               params:{
                   orderId:res.data[0]
               }
           }).then(res=>{
              console.log(res.data);
               //新打开窗口
               let resp = this.$router.resolve({path:'/alipay',query:{htmls:res.date}});
               this.htmls =res.data;
               // 打开新页面
               window.open(resp.href,'_black');
               const div = document.createElement('div');
               div.innerHTML = this.htmls;
               document.body.appendChild(div);
               document.forms[0].submit();
                // 跳转我的订单页面
               // this.$router.push({ path: "/order" });
               // 提示结算结果
              this.notifySucceed("订单已经生成");
           });
        }).catch(err => {
          return Promise.reject(err);
        });
    },

    // 获取收获地址
    getAddress(){
        this.$http.get("/order/address/list")
            .then(res=>{
                this.address=res.data;
            });
    },
    // 整合订单数据
    getPreOrder(){
        this.$http.get("/cart/find")
            .then(res=>{
              this.orders=res.data;
             // 计算价格和件数
              this.counts = res.data.length;
              let arrOrder = res.data;
              var count = 0;
              var price = 0;
              for(var n=0;n<arrOrder.length;n++){
                   count=count+arrOrder[n].num;
                   price=price+arrOrder[n].num*arrOrder[n].price;
              }
              this.counts = count;
              this.totals = price;
         });  
    }
  }
};
</script>
<style scoped>
.confirmOrder {
  background-color: #f5f5f5;
  padding-bottom: 20px;
}
/* 头部CSS */
.confirmOrder .confirmOrder-header {
  background-color: #fff;
  border-bottom: 2px solid #ff6700;
  margin-bottom: 20px;
}
.confirmOrder .confirmOrder-header .header-content {
  width: 1225px;
  margin: 0 auto;
  height: 80px;
}
.confirmOrder .confirmOrder-header .header-content p {
  float: left;
  font-size: 28px;
  line-height: 80px;
  color: #424242;
  margin-right: 20px;
}
.confirmOrder .confirmOrder-header .header-content p i {
  font-size: 45px;
  color: #ff6700;
  line-height: 80px;
}
/* 头部CSS END */

/* 主要内容容器CSS */
.confirmOrder .content {
  width: 1225px;
  margin: 0 auto;
  padding: 48px 0 0;
  background-color: #fff;
}

/* 选择地址CSS */
.confirmOrder .content .section-address {
  margin: 0 48px;
  overflow: hidden;
}
.confirmOrder .content .section-address .title {
  color: #333;
  font-size: 18px;
  line-height: 20px;
  margin-bottom: 20px;
}
.confirmOrder .content .address-body li {
  float: left;
  color: #333;
  width: 220px;
  height: 178px;
  border: 1px solid #e0e0e0;
  padding: 15px 24px 0;
  margin-right: 17px;
  margin-bottom: 24px;
}
.confirmOrder .content .address-body .in-section {
  border: 1px solid #ff6700;
}
.confirmOrder .content .address-body li h2 {
  font-size: 18px;
  font-weight: normal;
  line-height: 30px;
  margin-bottom: 10px;
}
.confirmOrder .content .address-body li p {
  font-size: 14px;
  color: #757575;
}
.confirmOrder .content .address-body li .address {
  padding: 10px 0;
  max-width: 180px;
  max-height: 88px;
  line-height: 22px;
  overflow: hidden;
}
.confirmOrder .content .address-body .add-address {
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .address-body .add-address i {
  font-size: 30px;
  padding-top: 50px;
  text-align: center;
}
/* 选择地址CSS END */

/* 商品及优惠券CSS */
.confirmOrder .content .section-goods {
  margin: 0 48px;
}
.confirmOrder .content .section-goods p.title {
  color: #333;
  font-size: 18px;
  line-height: 40px;
}
.confirmOrder .content .section-goods .goods-list {
  padding: 5px 0;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
}
.confirmOrder .content .section-goods .goods-list li {
  padding: 10px 0;
  color: #424242;
  overflow: hidden;
}
.confirmOrder .content .section-goods .goods-list li img {
  float: left;
  width: 30px;
  height: 30px;
  margin-right: 10px;
}
.confirmOrder .content .section-goods .goods-list li .pro-name {
  float: left;
  width: 650px;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-price {
  float: left;
  width: 150px;
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-status {
  float: left;
  width: 99px;
  height: 30px;
  text-align: center;
  line-height: 30px;
}
.confirmOrder .content .section-goods .goods-list li .pro-total {
  float: left;
  width: 190px;
  text-align: center;
  color: #ff6700;
  line-height: 30px;
}
/* 商品及优惠券CSS END */

/* 配送方式CSS */
.confirmOrder .content .section-shipment {
  margin: 0 48px;
  padding: 25px 0;
  border-bottom: 1px solid #e0e0e0;
  overflow: hidden;
}
.confirmOrder .content .section-shipment .title {
  float: left;
  width: 150px;
  color: #333;
  font-size: 18px;
  line-height: 38px;
}
.confirmOrder .content .section-shipment .shipment {
  float: left;
  line-height: 38px;
  font-size: 14px;
  color: #ff6700;
}
/* 配送方式CSS END */

/* 发票CSS */
.confirmOrder .content .section-invoice {
  margin: 0 48px;
  padding: 25px 0;
  border-bottom: 1px solid #e0e0e0;
  overflow: hidden;
}
.confirmOrder .content .section-invoice .title {
  float: left;
  width: 150px;
  color: #333;
  font-size: 18px;
  line-height: 38px;
}
.confirmOrder .content .section-invoice .invoice {
  float: left;
  line-height: 38px;
  font-size: 14px;
  margin-right: 20px;
  color: #ff6700;
}
/* 发票CSS END */

/* 结算列表CSS */
.confirmOrder .content .section-count {
  margin: 0 48px;
  padding: 20px 0;
  overflow: hidden;
}
.confirmOrder .content .section-count .money-box {
  float: right;
  text-align: right;
}
.confirmOrder .content .section-count .money-box .title {
  float: left;
  width: 126px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #757575;
}
.confirmOrder .content .section-count .money-box .value {
  float: left;
  min-width: 105px;
  height: 30px;
  display: block;
  line-height: 30px;
  color: #ff6700;
}
.confirmOrder .content .section-count .money-box .total .title {
  padding-top: 15px;
}
.confirmOrder .content .section-count .money-box .total .value {
  padding-top: 10px;
}
.confirmOrder .content .section-count .money-box .total-price {
  font-size: 30px;
}
/* 结算列表CSS END */

/* 结算导航CSS */
.confirmOrder .content .section-bar {
  padding: 20px 48px;
  border-top: 2px solid #f5f5f5;
  overflow: hidden;
}
.confirmOrder .content .section-bar .btn {
  float: right;
}
.confirmOrder .content .section-bar .btn .btn-base {
  float: left;
  margin-left: 30px;
  width: 158px;
  height: 38px;
  border: 1px solid #b0b0b0;
  font-size: 14px;
  line-height: 38px;
  text-align: center;
}
.confirmOrder .content .section-bar .btn .btn-return {
  color: rgba(0, 0, 0, 0.27);
  border-color: rgba(0, 0, 0, 0.27);
}
.confirmOrder .content .section-bar .btn .btn-primary {
  background: #ff6700;
  border-color: #ff6700;
  color: #fff;
}
/* 结算导航CSS */

/* 主要内容容器CSS END */
</style>