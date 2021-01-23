<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:33
 -->
<template>
   <v-data-table
    :headers="headers"
    :items="userList"
    :items-per-page="5"
     class="elevation-1">
    <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.username }}</td>
        <!-- <td class="text-xs-center">{{ props.item.password }}</td> -->
        <td class="text-xs-left">{{ props.item.phone }}</td>
        <td class="text-xs-left">{{ props.item.created }}</td>
    </template>
    </v-data-table>
</template>

<script>
export default {
   name: "user",
   data () {
      return {
        headers: [
          {
            text: '用户编号',
            align: 'start',
            value: 'id',
          },
          { text: '用户名', sortable: false, value: 'username' },
        //   { text: '密码', sortable: false, value: 'password' },
          { text: '手机号',  sortable: false, value: 'phone' },
          { text: '注册时间',  value: 'created' },
        ],
        userList:[], // 用户列表
      }
    },
    mounted(){
        // 获取用户信息
      this.getusers();
    },
    methods:{
       // 获取用户信息
    getusers(){
        this.$http.get("/user/list")
            .then(resp=>{
                this.userList = resp.data;
            }).catch(()=>{
                this.$message.error("获取异常！");
            });
    }
    },

  }
</script>
    
<style scoped>

</style>
