<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-06 23:45:25
 -->
<template>
  <div class="home" id="home" name="home">
    <!-- 轮播图 -->
    <div class="block">
      <!-- 三级类目 -->
      <div class="menu">
        <ul class="menu-wrap" v-for="cid1 in categoryOnes" :key="cid1.name">
          <li class="menu-item">
            <a href="javascript:;"  @mouseover="getCategoryTwoList(cid1.id)">  {{ cid1.name }} </a>
            <div class="two">
              <ul v-for="(item,index) in categoryTwos" :key="index">
                <a href="javascript:;" @mouseover="getCategoryThreeList(item.id)"> {{ item.name }} </a>
                <div class="three">
                    <li v-for="(item2,index2) in categoryThrees" :key="index2">
                     <!-- item2?'/#/product/'+(item2.id || 0):'' -->
                    <a href="javascript:;">{{ item2 === null ? "" : item2.name }}</a>
                    </li>
                </div>
              </ul>
            </div>
          </li>
        </ul>
      </div>
      <!-- 三级类目END -->
      <!-- 轮播图布局 -->
      <el-carousel height="460px">
        <el-carousel-item v-for="item in carousel" :key="item.carousel_id">
          <img style="height:460px;" :src="item.img" :alt="item.id" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- 轮播图END -->
    <div class="main-box">
      <div class="main">
        <!-- 蔬菜商品展示区域 -->
        <div class="veget">
          <div class="box-hd">
            <div class="title">畅销蔬菜</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link to>
                <img :src="$target +'/imgs/ads/bs2020-1.png'" class="veget-img" />
              </router-link>
            </div>
            <div class="list">
              <MyList2 :list="vegetables" :isMore="true"></MyList2>
            </div>
          </div>
        </div>
        <!-- 商品展示区域END -->
        <div class="hr-line"></div>
        <!-- 水果商品展示区域 -->
        <div class="fruit">
          <div class="box-hd">
            <div class="title">热卖水果</div>
          </div>
          <div class="box-bd">
            <div class="promo-list" style="height:375px;">
              <router-link to>
                <img :src="$target +'/imgs/ads/bs2020-2.png'" class="fruit-img"/>
              </router-link>
            </div>
            <div class="list" style="height:375px;">
              <MyList2 :list="fruitList" :isMore="false"></MyList2>
            </div>
          </div>
        </div>
        <!-- 商品展示区域END -->
        <!-- 农机商品展示区域 -->
        <div class="mechine-3">
          <div class="mechine">
          <div class="box-hd-1">
            <div class="title-1">热销农机</div>
          </div>
          <div class="box-bd-1">
            <div class="promo-list-1"> 
              <router-link to>
                <img :src="$target +'/imgs/ads/bs2020-3.png'" class="mechine-img" />
              </router-link>
            </div>
            <div class="list-1">
              <MyList2 :list="agrimachinery" :isMore="true"></MyList2>
            </div>
          </div>
        </div>
        </div>
        <!-- 商品展示区域END -->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Home",
  data() {
    return {
      carousel: [
        // 轮播图数据
        {
          id: "42",
          img: "/imgs/slider/slide-1.jpg"
        },
        {
          id: "52",
          img: "/imgs/slider/slide-2.jpg"
        },
        {
          id: "53",
          img: "/imgs/slider/slide-3.jpg"
        },
        {
          id: "54",
          img: "/imgs/slider/slide-4.jpg"
        },
        {
          id: "55",
          img: "/imgs/slider/slide-5.jpg"
        }
      ],
      categoryOnes:[], // 一级类目
      categoryTwos:[], // 二级类目
      categoryThrees:[], // 三级类目
      fruitList:[], // 水果
      vegetables:[], // 蔬菜
      agrimachinery:[], //农机
    };
  },
  watch: {
    // 家电当前选中的商品分类，响应不同的商品数据
    applianceActive: function(val) {
      // 页面初始化的时候把applianceHotList(热门家电商品列表)直接赋值给applianceList(家电商品列表)
      // 所以在切换商品列表时判断applianceHotList是否为空,为空则是第一次切换,把applianceList赋值给applianceHotList
      if (this.applianceHotList == "") {
        this.applianceHotList = this.applianceList;
      }
      if (val == 1) {
        // 1为热门商品
        this.applianceList = this.applianceHotList;
        return;
      }
      if (val == 2) {
        // 2为电视商品
        this.applianceList = this.miTvList;
        return;
      }
    },
    accessoryActive: function(val) {
      // 页面初始化的时候把accessoryHotList(热门配件商品列表)直接赋值给accessoryList(配件商品列表)
      // 所以在切换商品列表时判断accessoryHotList是否为空,为空则是第一次切换,把accessoryList赋值给accessoryHotList
      if (this.accessoryHotList == "") {
        this.accessoryHotList = this.accessoryList;
      }
      if (val == 1) {
        // 1为热门商品
        this.accessoryList = this.accessoryHotList;
        return;
      }
      if (val == 2) {
        // 2为保护套商品
        this.accessoryList = this.protectingShellList;
        return;
      }
      if (val == 3) {
        //3 为充电器商品
        this.accessoryList = this.chargerList;
        return;
      }
    }
  },
  created() {
    
    // 获取一级类目
    this.getCategoryOneList();

    // 获取热销蔬菜数据
    this.getfruitList();

    // 获取热销水果
    this.getbannas();

    // 农机
    this.getagrimachinery();
  },
  methods: {

    // 蔬菜
    getfruitList(){
        // this.notifySucceed("登录成功！");
         this.$http.get("/item/sku/list?id=18").then(resp=>{
             this.vegetables = resp.data;
             console.log(resp.data);
        }).catch(resp=>{
           console.log("异常信息：" + resp);
        });
    },
    // 水果
    getbannas(){
        this.$http.get("/item/sku/list?id=6").then(resp=>{
            this.fruitList = resp.data;
                console.log(resp.data);
        }).catch(resp=>{
            console.log("异常信息：" + resp);
        });
    },
    //农机
     getagrimachinery(){
        this.$http.get("/item/sku/list?id=13").then(resp=>{
            this.agrimachinery = resp.data;
                console.log(resp.data);
        }).catch(resp=>{
            console.log("异常信息：" + resp);
        });
    },
    // 获取家电模块子组件传过来的数据
    getChildMsg(val) {
      this.applianceActive = val;
    },
    // 获取配件模块子组件传过来的数据
    getChildMsg2(val) {
      this.accessoryActive = val;
    },
    // 一级类目实现
    getCategoryOneList(){
        this.$http.get("/item/category/list",{
            params:{
                pid:0
            }
        }).then(resp=>{
            this.categoryOnes = resp.data;
        }).catch(resp=>{
           console.log("异常信息：" + resp);
        });
    },
    // 添加二级类目子组件
    getCategoryTwoList(cid2){
        this.$http.get("/item/category/list",{
            params:{
                pid:cid2
            }
        }).then(resp=>{
            this.categoryTwos = resp.data;
        }).catch(resp=>{
           console.log("异常信息：" + resp);
        });
    },
    // 添加三级子组件
    getCategoryThreeList(cid3){
        this.$http.get("/item/category/list",{
            params:{
                pid:cid3
            }
        }).then(resp=>{
            this.categoryThrees = resp.data;
        }).catch(resp=>{
           console.log("异常信息：" + resp);
        });
    },
  }
};
</script>
<style scoped lang="scss">
@import "../assets/style/index.css";
.menu {
  background-color: #55555a7a;
  box-sizing: border-box;
  position: absolute;
  width: 264px;
  height: 451px;
  z-index: 8;
  padding: 26px 0;

  .menu-wrap {
    .menu-item {
      height: 50px;
      line-height: 50px;
      //  一级目录
      a {
        position: relative;
        font-size: 16px;
        height: 42px;
        line-height: 42px;
        display: block;
        color: white;
        padding-left: 30px;

        &:after {
          position: absolute;
          top: 0;
          right: 70px;
          content: "\e6ef";
          color: #e0e0e0;
          font-family: iconfont;
        }
      }
      &:hover {
        background-color: #55555a7a;
        .two {
          display: block;
        }
        .three {
          display: block;
        }
      }
    }
    // 二级类目
    .two {
      background-color: #55555a7a;
      display: none;
      width: 650px;
      height: 451px;
      position: absolute;
      border: 1px gray;
      top: 0;
      left: 263px;
      ul {
        display: flex;
        justify-content: space-between;
        height: 75px;
        padding-left: 23px;
        // 三级类目
        .three {
          width: 500px;
          height: 451px;
          position: absolute;
          border: 1px gray;
          top: 0;
          left: 300px;
        }
        li {
          height: 75px;
          width: 100px;
          line-height: 75px;
          flex: 1;
          padding-left: 23px;
        }
        a {
          color: whitesmoke;
          font-size: 14px;
        }
      }
    }
  }
  
}
  // 水果CSS样式
  .fruit{
    height: 400px;
  }
  .mechine{
      float: left;
      padding-right: 0px;
      background-color:  #f1f3f7;
  }
  .box-bd-1{
      float: left;
  }
  .promo-list-1{
     height: 320px;
     float: left;
     width: 228px;

  }
  .list-1{
      float: right;
      height: 320px;
      margin-right: 0px;
      background-color:  #f1f3f7;
  }
  .title-1{
    float: left;
    font-size: 22px;
    font-weight: 200;
    line-height: 58px;
    color: #333;
  }
 .fruit-img,.mechine-img{
      width: 227px;
      height: 320px;
  }
  .veget-img{
      height: 650px;
  }
  .hr-line{
      margin-top: 30px;
      height: 20px;
  }
  // 设置热销机械的背景色
  .mechine-3{
    // border: 1px red solid;
    width: 1370px;
    float: left;
    margin-left: -147px;
    padding-left: 147px;
    padding-bottom: 10px;
    background-color:  #f1f3f7;
  }

</style>
