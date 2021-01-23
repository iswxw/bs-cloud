<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:32
 -->
<template>
  <v-app class="login">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>BS2020商城后台管理</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" v-model="username" label="用户名" type="text" />
                  <v-text-field prepend-icon="lock" v-model="password" label="密码" id="password" :append-icon="e1 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e1 = !e1)" :type="e1 ? 'text' : 'password'"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="doLogin">登录</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-dialog v-model="dialog" width="300px">
      <v-alert icon="warning" color="error" :value="true">
        用户名和密码不能为空
      </v-alert>
    </v-dialog>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    username: "",
    password: "",
    dialog: false,
    e1: false
  }),
  methods: {
    doLogin() {
      if (!this.username || !this.password) {
        this.dialog = true;
        return false;
      }
      if ("admin" === this.username && "123456" === this.password) {
        // 临时跳转
        this.$router.push("/success");
      }
      // this.$http
      //   .post(
      //     "/auth/accredit?username=" +
      //       this.username +
      //       "&password=" +
      //       this.password
      //   )
      //   .then(res => {
      //     console.log(res);
      //     // “200”代表登录成功，其他的均为失败
      //     if (res.status === 200) {
      //       // 登录状态验证
      //       this.$http.get("/auth/verify").then(resp => {
      //         console.log(resp.data);
      //         this.$router.push("/success");
      //         this.$message.success("登录成功！");
      //       });
      //     }
      //   })
      //   .catch(() => {
      //     this.$message.error("登录失败！");
      //   });
      console.log(this.username + " ... " + this.password);
    }
  }
};
</script>
<style scoped>
.login {
  margin: 0;
  padding: 0;
  background-image: url("~@/assets/img/mbg.gif");
  background-repeat: no-repeat;
  background-size: 100%;
}
</style>
