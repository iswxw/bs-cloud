<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-08 22:24:46
 -->
<template>
   <v-dialog 
       v-model="dialog" 
       width="500"
       transition="dialog-right-transition">
       <v-card>
       <v-toolbar dark color="primary">
                <v-toolbar-title>退出登录窗口</v-toolbar-title>
                <v-spacer></v-spacer>
      </v-toolbar>
        <v-card-text>确定要退出系统吗？</v-card-text>
        <v-divider></v-divider>  
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="gray darken-1" text  @click="dialog = false">取消</v-btn>
          <v-btn color="primary" text @click="loginOut"> 确定</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
  export default {
    data () {
      return {
        dialog: true,
      }
    },
    methods:{
    loginOut(){
        this.dialog = false;
        this.$router.push("/");
        this.$message.success("成功退出系统");
    }
    }
  }
</script>