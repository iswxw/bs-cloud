<template>
  <div class="Service">
    <div class="container">
      <ul>
        <li class="Service-item">
          <span class="iconfont">&#xe924;</span>
          商品微服务
        </li>
        <li class="Service-item">
          <span class="iconfont">&#xe603;</span>
          7天无理由退货
        </li>
        <li class="Service-item">
          <span class="iconfont">&#xe6bd;</span>
          15天免费换货
        </li>
        <li class="Service-item">
          <span class="iconfont">&#xe600;</span>
          满99元包邮
        </li>
        <li class="Service-item">
          <span class="iconfont">&#xe6c9;</span>
          520余家售后网点
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "ServiceBar",
  data() {
    return {};
  },
  components: {}
};
</script>

<style scoped lang="scss">
@import "./../assets/scss/base";
@import "./../assets/scss/config";
.Service {
  padding: 33px 0;
  color: $colorC;
  font-size: 16px;
  li {
    display: inline-block;
    width: 19.8%;
    text-align: center;
    border-right: 1px solid #cccccc;
    &:last-child {
      border: none;
    }
    span {
      margin-right: 6px;
      font-size: 24px;
      vertical-align: -4px;
    }
  }
  li:hover {
    color: $colorA;
    cursor: pointer;
  }
}
</style>
