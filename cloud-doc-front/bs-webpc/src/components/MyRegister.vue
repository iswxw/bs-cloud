<!--
 * @Descripttion:  注册组件
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-07 12:05:19
 -->
<template>
  <div id="register">
    <el-dialog title="注册" width="500px" center :visible.sync="isRegister">
      <el-form
        :model="RegisterUser"
        :rules="rules"
        status-icon
        ref="ruleForm"
        class="demo-ruleForm">
        <el-form-item prop="name">
          <el-input
            prefix-icon="el-icon-user-solid"
            placeholder="请输入账号"
            v-model="RegisterUser.name"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input
            prefix-icon="el-icon-view"
            type="password"
            placeholder="请输入密码"
            v-model="RegisterUser.pass"
          ></el-input>
        </el-form-item>
        <el-form-item prop="confirmPass">
          <el-input
            prefix-icon="el-icon-view"
            type="password"
            placeholder="请再次输入密码"
            v-model="RegisterUser.confirmPass"
          ></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input
            prefix-icon="el-icon-view"
            placeholder="请输入手机号"
            v-model="RegisterUser.phone"
          ></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            prefix-icon="el-icon-view"
            placeholder="请输入验证码"
            v-model="RegisterUser.code"
            style="width:200px;height:50px; float:left;"
          ></el-input>
          <el-image :src="path" style="margin-left:10px;"></el-image>
        </el-form-item>
        <el-form-item>
          <el-button
            size="medium"
            type="primary"
            @click="Register"
            style="width:100%;">注册</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import { tools } from "@/tools/common.js";
export default {
  name: "MyRegister",
  props: ["register"],
  data() {
    // 用户名的校验方法
    let validateName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      }
      // 用户名
      const userNameRule = /^.{2,10}$/;
      if (userNameRule.test(value)) {
        //判断数据库中是否已经存在该用户名
        this.$axios
          .get("/user/check/"+this.RegisterUser.name+"/1", )
          .then(res => {
            console.log(res.data);
            // “200”代表用户名不存在，可以注册
            if (res.data === true) {
              this.$refs.ruleForm.validateField("checkPass");
              return callback();
            } else {
              return callback(new Error("此用户名已经存在"));
            }
          })
          .catch(err => {
            return Promise.reject(err);
          });
      } else {
        return callback(new Error("用户名长度必须在2-10之间"));
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
        return callback(
          new Error("密码长度必须在6-15之间")
        );
      }
    };
    // 确认密码的校验方法
    let validateConfirmPass = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入确认密码"));
      }
      // 校验是否以密码一致
      if (this.RegisterUser.pass != "" && value === this.RegisterUser.pass) {
        this.$refs.ruleForm.validateField("checkPass");
        return callback();
      } else {
        return callback(new Error("两次输入的密码不一致"));
      }
    };
    let validatePhone = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入手机号"));
      }
      // 校验是否以密码一致
       //const phoneRule = /^[1]([3-9])[0-9]{9}$/;
       const phoneRule = /^1[35678]\d{9}$/;
       if (phoneRule.test(value)) {
          this.$refs.ruleForm.validateField("checkPass");
          return callback();
       }else{
          return callback(
          new Error("手机号不合法，请重新输入")
        );
       }

    };
    // 手机号校验
    return {
      path:"", //验证码
      isRegister: false, // 控制注册组件是否显示
      RegisterUser: {
        name: "",
        pass: "",
        confirmPass: "",
        phone:"",
        code:""
      },
      // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }],
        confirmPass: [{ validator: validateConfirmPass, trigger: "blur" }],
        phone: [{ validator: validatePhone, trigger: "blur" }]
      }
    };
  },
  watch: {
    // 监听父组件传过来的register变量，设置this.isRegister的值
    register: function(val) {
      if (val) {
        this.isRegister = val;
        //生成验证码
      this.$http
          .get("/user/validete",{responseType:"arraybuffer"})
          .then(resp=>{
          // 处理图片流
          this.path =  'data:image/png;base64,' + btoa(new Uint8Array(resp.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))
          });  
      }
    },
    // 监听this.isRegister变量的值，更新父组件register变量的值
    isRegister: function(val) {
      if (!val) {
        this.$refs["ruleForm"].resetFields();
        this.$emit("fromChild", val);
      }
    }
  },
  methods: {
    Register() {      
      // 通过element自定义表单校验规则，校验用户输入的用户信息
      this.$refs["ruleForm"].validate(valid => {
        //如果通过校验开始注册
        if (valid) {

          let user = {
              username: this.RegisterUser.name,
              password: this.RegisterUser.pass,
              phone:this.RegisterUser.phone,
              code:this.RegisterUser.code
          };
          tools.http.post("/user/register",tools.stringify(user))
               .then(res => {
                 console.log(res);
              // “001”代表注册成功，其他的均为失败
              if (res.status === 201) {
                // 隐藏注册组件
                this.isRegister = false;
                // 弹出通知框提示注册成功信息
                this.notifySucceed("注册成功，请前往登录！");
              } else {
                // 弹出通知框提示注册失败信息
                this.notifyError("注册失败");
              }
            })
            .catch(err => {
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
<style>
 

</style>
