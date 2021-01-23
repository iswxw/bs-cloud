<!--
 * @Descripttion: 登录组件
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-06 22:13:07
 -->

<template>
  <div id="myLogin">
    <el-dialog title="登录" width="400px" center :visible.sync="isLogin">
      <el-form
        :model="LoginUser"
        :rules="rules"
        status-icon
        ref="ruleForm"
        class="demo-ruleForm">
        <el-form-item prop="name">
          <el-input
            prefix-icon="el-icon-user-solid"
            placeholder="请输入用户名"
            v-model="LoginUser.name"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input
            prefix-icon="el-icon-view"
            type="password"
            placeholder="请输入密码"
            v-model="LoginUser.pass"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            size="medium"
            type="primary"
            @click="Login"
            style="width:100%;"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import { mapActions } from "vuex";
import { tools } from "@/tools/common.js";
export default {
  name: "MyLogin",
  data() {
    // 用户名的校验方法
    let validateName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      }
      // 用户名
      const userNameRule = /^.{2,10}$/;
      if (userNameRule.test(value)) {
        this.$refs.ruleForm.validateField("checkPass");
        return callback();
      } else {
        return callback(new Error("用户名长度必须在5-16之间"));
      }
    };
    // 密码的校验方法
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入密码"));
      }
      // 密码
      const passwordRule = /^.{6,15}$/;
      if (passwordRule.test(value)) {
        this.$refs.ruleForm.validateField("checkPass");
        return callback();
      } else {
        return callback(new Error("密码长度必须在6-15之间"));
      }
    };
    return {
      LoginUser: {
        name: "",
        pass: ""
      },
      // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }]
      }
    };
  },
  computed: {
    // 获取vuex中的showLogin，控制登录组件是否显示
    isLogin: {
      get() {
        return this.$store.getters.getShowLogin;
      },
      set(val) {
        this.$refs["ruleForm"].resetFields();
        this.setShowLogin(val);
      }
    }
  },
  methods: {
    ...mapActions(["setUser", "setShowLogin"]),
    Login() {
      // 通过element自定义表单校验规则，校验用户输入的用户信息
      this.$refs["ruleForm"].validate(valid => {
        //如果通过校验开始登录
        if (valid) {
         let data = {
           username: this.LoginUser.name,
           password: this.LoginUser.pass
         };
          this.$http.post("/auth/accredit",tools.stringify(data)).then(res => {
              // “200”代表登录成功，其他的均为失败
            if (res.status == 200) {
            // 登录状态验证
            this.$axios.get("/auth/verify")
                  .then(resp=>{
                    // 隐藏登录组件
                    this.isLogin = false;
                      // 登录信息存到本地
                    let user = JSON.stringify(resp.data.username);
                    localStorage.setItem("user", user);
                    // 登录信息存到vuex
                    this.setUser(res.data.user);
                    // 弹出通知框提示登录成功信息
                    this.notifySucceed("登录成功！");
                  })     
              } else {
                // 清空输入框的校验状态
                this.$refs["ruleForm"].resetFields();
                 // 弹出通知框提示登录失败信息
                this.notifyError("用户名或密码不正确，登录失败！");
              }
            }).catch(err => {
               // 弹出通知框提示登录失败信息
              this.notifyError("用户名或密码不正确，登录失败！");
              return Promise.reject(err);
            });
        } else {
          return false;
        }
      });
    }
  }
};
</script>
<style></style>
