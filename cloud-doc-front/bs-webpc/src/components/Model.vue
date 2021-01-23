<template>
  <transition name="slider">
    <div class="model" v-show="showModel">
      <div class="mask"></div>
      <div class="model-dialog">
        <div class="model-header">
          <span>{{ title }}</span>
          <span class="iconfont cancel" @click="$emit('cancel')">&#xe611;</span>
        </div>
        <div class="model-body">
          <slot name="body"></slot>
        </div>
        <div class="model-footer">
          <button class="btn" v-if="btnType === '1'" @click="$emit('submit')">
            确定
          </button>
          <button
            class="btn btn-default"
            v-if="btnType === '2'"
            @click="$emit('cancel')"
          >
            取消
          </button>
          <div class="btn-group" v-if="btnType === '3'">
            <button class="btn" @click="$emit('submit')">{{ sureText }}</button>
            <button class="btn btn-default" @click="$emit('cancel')">
              {{ cancelText }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "Model",
  props: {
    // 弹框类型 small middle big
    modelType: {
      type: String,
      default: "form"
    },
    // 弹框标题
    title: String,
    // 按钮类型 1 确定  2取消 3都有
    btnType: String,
    sureText: {
      type: String,
      default: "确定"
    },
    cancelText: {
      type: String,
      default: "取消"
    },
    showModel: Boolean
  },
  data() {
    return {};
  }
};
</script>

<style scoped lang="scss">
// 注意引入顺序
@import "./../assets/scss/config";
@import "./../assets/scss/mixin";
@import "./../assets/scss/model";
@import "./../assets/scss/btn";
</style>
