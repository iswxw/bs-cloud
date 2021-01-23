<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-04-13 23:34:35
 -->
<template>
  <!-- 顶栏容器 -->
  <el-header>
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      active-text-color="#409eff"
      router>
      <div class="logo">
        <router-link to="/">
          <img src="../assets/image/logo.png" alt />
        </router-link>
      </div>
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/goods">全部商品</el-menu-item>
      <el-menu-item index="/about">关于我们</el-menu-item>

      <div class="so">
        <el-input placeholder="请输入搜索内容"  v-model="search">
        </el-input>
         <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
      </div>
    </el-menu>
  </el-header>
  <!-- 顶栏容器END -->
</template>
<script>
import { Tools } from "../tools/common";
export default {
  beforeUpdate() {
    this.activeIndex = this.$route.path;
  },
  data() {
    return {
      activeIndex: "全部商品", // 头部导航栏选中的标签
      search:"", //搜索条件
      goodsList: [], // 接收搜索得到的结果
      total: 0, // 总条数
      totalPage: 0 // 总页数
    };
  },
  created() {
    if (!location.search) {
      return;
    }
    const search = Tools.parse(location.search.substring(1));
    this.search = search;
  },
  methods: {
    // 点击搜索按钮
    searchClick() {
      if (this.search != "") {
        console.log(this.search);
        //this.$http.post("/page", this.search);
        // 跳转到全部商品页面,并传递搜索条件
        this.$router.push({ path: "/goods", query: { search: this.search } });
        this.search = "";
      }
    },
    submit() {
      alert("你好");
    }
  }
};
</script>
<style>
/* 顶栏容器CSS */
.el-header .el-menu {
  max-width: 1225px;
  margin: 0 auto;
}
.el-header .logo {
  height: 60px;
  width: 189px;
  float: left;
  margin-right: 100px;
}
.el-header .so {
  margin-top: 10px;
  width: 300px;
  float: right;
}
.btnBg:hover {
  background-color: darkorange;
}

/* 顶栏容器CSS END */
</style>
