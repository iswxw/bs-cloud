<!--
 * @Descripttion: 渲染markdown文件
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-11 12:15:01
 -->
<template>
  <div id="my-markdown" class="markdown-body">
    <vue-markdown :source="md"></vue-markdown>
  </div>
</template>
<script>
import VueMarkdown from "vue-markdown";
export default {
  name: "MyMarkdown",
  components: {
    VueMarkdown
  },
  data() {
    return {
      md: ""
    };
  },
  created() {
    // 请求README.md 局部请求路径
    this.$http.get(`http://www.wxw.com/doc/README.md`)
         .then(res => {
         this.md = res.data;
      })
      .catch(err => {
        return Promise.reject(err);
      });
  }
};
</script>
<style>
@import "../assets/style/github-markdown.css";
.markdown-body {
  box-sizing: border-box;
  margin: 0 auto;
  padding: 0 40px;
}
</style>