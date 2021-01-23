<!--
 * @Descripttion: 商品详情
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-10 20:21:20
 -->
<template>
   <div id="details">
    <!-- 头部 -->
    <div class="page-header">
      <div class="title">
        <div class="product-nav">
        <div class="subtitle" style="color:blue;">分类
           <span style="color:#999;margin:0 10px;">></span>{{categories[0].name}}
           <span style="color:#999;margin:0 10px;">></span>{{categories[1].name}}
           <span style="color:#999;margin:0 10px;">></span>
           <span style="color:gray;">{{categories[2].name}}</span>
        </div>
      </div>
        <div class="list">
          <ul>
            <li>
              <router-link to>概述</router-link>
            </li>
            <li>
              <router-link to>参数</router-link>
            </li>
            <li>
              <router-link to>用户评价</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 头部END -->

    <!-- 主要内容 -->
    <div class="main">
      <!-- 左侧商品轮播图 -->
      <div class="block">
        <el-carousel height="550px" v-if="skus.length>1">
          <el-carousel-item v-for="(item,index) in skus" :key="index">
            <img style="height:560px;" :src="item.images.split(',')[0]" :alt="item.stock" />
          </el-carousel-item>
        </el-carousel>
        <div v-if="skus.length==1">
          <img
            style="height:560px;"
            :src="$target + item[0].images.split(',')[0]"
            :alt="item[0].stock"
          />
        </div>
      </div>
      <!-- 左侧商品轮播图END -->

      <!-- 右侧内容区 -->
      <div class="content">
        <ul>
            <li v-for="(item,index) in skus" :key="index"></li>
        </ul>
        <h1 class="name">{{brand.name}}&nbsp;</h1>
        <br>
        <p class="intro">
           {{spu.title}}
         </p>
         <p class="store">平台自营</p>
        <br><i class="el-icon-circle-check"></i><span style="color:#999;">购买方式：官方标配</span>
        <br><i class="el-icon-circle-check"></i><span style="color:#999;">售后服务：{{spuDetail.afterService}}</span>
        <div class="pro-list">
         <br>
            <span>包装清单：{{spuDetail.packingList}}</span>
          <p class="price-sum">总计 : {{skus[0].price}}元</p>
        </div>
        <!-- 内容区底部按钮 -->
        <div class="button">
          <el-button class="shop-cart" :disabled="dis" @click="addShoppingCart">加入购物车</el-button>
          <el-button class="like" @click="addCollect">立即购买</el-button>
        </div>
        <!-- 内容区底部按钮END -->
        <div class="pro-policy">
          <ul>
            <li>
              <i class="el-icon-circle-check"></i> 平台自营
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 平台发货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天无理由退货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天价格保护
            </li>
          </ul>
        </div>
      </div>
      <!-- 右侧内容区END -->
    </div>
    <!-- 主要内容END -->
  </div>
</template>
<script>
import { tools } from "@/tools/common.js";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      dis: false, // 控制“加入购物车按钮是否可用”
      spuId: "", // 商品id
      num:1,//购物车数量
      mycarts:{}, // 购物车
      brand:{}, //品牌信息
      categories:[],// 类目信息
      paramMap:{},//规格参数
      skus:[], //商品信息
      spu:{},//SPU信息
      spuDetail:{},// SpU详情信息
    };
  },
  // 通过路由获取商品id
  activated() {
    if (this.$route.query.productID != undefined) {
      this.spuId = this.$route.query.productID;
      this.getDetails(this.spuId);
    }
  },
  watch: {
    // 监听商品id的变化，请求后端获取商品数据
    productID: function(val) {
      this.getDetails(val);
    }
  },
  
  methods: {
    ...mapActions(["unshiftShoppingCart", "addShoppingCartNum"]),
    // 获取商品详细信息
    getDetails(val) {
      console.log(val);
      this.$http.get("/item/item/detail",{
          params:{
              spuId:val
          }
      }).then(res => {
           // console.log(res.data);
            this.brand=res.data.brand;
            this.categories=res.data.categories;
            this.paramMap=res.data.paramMap;
            this.skus= res.data.skus;
            this.spu=res.data.spu;
            this.spuDetail=res.data.spuDetail;
            let mycarts={
                skuId:res.data.skus[0].id,
                image:res.data.skus[0].images.split(',')[0],
                title:res.data.skus[0].title,
                ownSpec:res.data.skus[0].ownSpec,
                price:res.data.skus[0].price,
                num:1,
            };
            this.mycarts =mycarts;
           // console.log("购物车信息：",mycarts);
            tools.store.set("BS_CART",mycarts);
      }).catch(err => {
          return Promise.reject(err);
      });
    },
    // 加入购物车
    addShoppingCart() {

        var num=this.num+1;;
        //判断有没有登录
        tools.http.get("/auth/verify").then(res=>{
           //登录
           console.log(res.data);
            this.$http.post("/cart", this.mycarts).then(res => {
              console.log(res.status);
              if(res.status == 201){
                  this.unshiftShoppingCart(1);
                  this.addShoppingCartNum(1);
                  this.notifySucceed("添加购物车成功");
                // 跳转购物车页面
                 this.$router.push("/shoppingCart");
              }
            }).catch(err => {
             // this.notifyError("系统繁忙，请稍后再试！");
              return Promise.reject(err);
            });
      
        }).catch(()=>{
             this.notifyError("你还没有登录，请先登录！");
            // 未登录，保存在LocalStorage
            // 先去查询购物车信息
            let carts = localStorage.getItem("BS_CART")||[];
            let cart =carts.id == this.skus[0].id;
            if(cart){
               carts.num=num;
            }else{
                // 没有则新增购物车记录
            this.mycarts.num=num;
            console.log("购物车信息：", this.mycarts);
            tools.store.set("BS_CART", this.mycarts);
              //页面跳转 到购物车
            console.log(tools.store.get("BS_CART"));
            }
              //页面跳转 到购物车
        });
        
        
    },
    addCollect() {

      // 调转到结算页
      this.$router.push("/confirmOrder");
      // 判断是否登录,没有登录则显示登录组件
    //   if (!this.$store.getters.getUser) {
    //     this.$store.dispatch("setShowLogin", true);
    //     return;
    //   }
       
    }
  }
};
</script>
<style>
/* 头部CSS */
#details .page-header {
  height: 64px;
  margin-top: -20px;
  z-index: 4;
  background: #fff;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
}
#details .page-header .title {
  width: 1225px;
  height: 64px;
  line-height: 64px;
  font-size: 18px;
  font-weight: 400;
  color: #212121;
  margin: 0 auto;
}
#details .page-header .title p {
  float: left;
}
#details .page-header .title .list {
  height: 64px;
  float: right;
}
#details .page-header .title .list li {
  float: left;
  margin-left: 20px;
}
#details .page-header .title .list li a {
  font-size: 14px;
  color: #616161;
}
#details .page-header .title .list li a:hover {
  font-size: 14px;
  color: #ff6700;
}
/* 头部CSS END */

/* 主要内容CSS */
#details .main {
  width: 1225px;
  height: 560px;
  padding-top: 30px;
  margin: 0 auto;
}
#details .main .block {
  float: left;
  width: 560px;
  height: 560px;
}
#details .el-carousel .el-carousel__indicator .el-carousel__button {
  background-color: rgba(163, 163, 163, 0.8);
}
#details .main .content {
  float: left;
  margin-left: 25px;
  width: 640px;
}
#details .main .content .name {
  height: 30px;
  line-height: 30px;
  font-size: 24px;
  font-weight: normal;
  color: #212121;
}
#details .main .content .intro {
  color: #b0b0b0;
  padding-top: 10px;
}
#details .main .content .store {
  color: #ff6700;
  padding-top: 10px;
}
#details .main .content .price {
  display: block;
  font-size: 18px;
  color: #ff6700;
  border-bottom: 1px solid #e0e0e0;
  padding: 25px 0 25px;
}
#details .main .content .price .del {
  font-size: 14px;
  margin-left: 10px;
  color: #b0b0b0;
  text-decoration: line-through;
}
#details .main .content .pro-list {
  background: #f9f9fa;
  padding: 30px 60px;
  margin: 50px 0 50px;
}
#details .main .content .pro-list span {
  line-height: 30px;
  color: #616161;
}
#details .main .content .pro-list .pro-price {
  float: right;
}
#details .main .content .pro-list .pro-price .pro-del {
  margin-left: 10px;
  text-decoration: line-through;
}
#details .main .content .pro-list .price-sum {
  color: #ff6700;
  font-size: 24px;
  padding-top: 20px;
}
#details .main .content .button {
  height: 55px;
  margin: 10px 0 20px 0;
}
#details .main .content .button .el-button {
  float: left;
  height: 55px;
  font-size: 16px;
  color: #fff;
  border: none;
  text-align: center;
}
#details .main .content .button .shop-cart {
  width: 300px;
  background-color: #ff6700;
}
#details .main .content .button .shop-cart:hover {
  background-color: #28d877;
}

#details .main .content .button .like {
  width: 300px;
  margin-left: 40px;
  background-color: #f50404b9;
}
#details .main .content .button .like:hover {
  background-color: #44cc6d;
}
#details .main .content .pro-policy li {
  float: left;
  margin-right: 20px;
  color: #b0b0b0;
}
/* 主要内容CSS END */

/* 实现尖括号 */
</style>