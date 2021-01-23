<template>
  <div>
    <div class="footer">
      <div class="footer-logo">
        <img src="../assets/image/logo-footer.png" alt="" />
        <p>BS2020商城</p>
      </div>
      <div class="footer-link">
        <a href="#">年少轻狂博客</a><span>|</span> <a href="#">BS2020商城</a
        ><span>|</span> <a href="https://blog.wxw.plus">blog.wxw.plus</a
        ><span>|</span>
        <a href="#">其他</a>
      </div>
      <div class="copyright">
        版权所有
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavFooter",
  data() {
    return {};
  }
};
</script>

<style scoped lang="scss">
@import "../assets/scss/config.scss";

.footer {
  height: 234px;
  border-top: 4px solid $colorA;
  background-color: $colorB;
  color: $colorD;
  font-size: 16px;
  text-align: center;
  .footer-logo {
    margin-top: 46px;
    margin-bottom: 31px;
    img {
      width: 53px;
      height: 36px;
      margin-bottom: 13px;
    }
  }
  .footer-link {
    a {
      color: $colorD;
      display: inline-block;
    }
    span {
      margin: 0 10px;
    }
    margin-bottom: 20px;
  }
}
</style>
