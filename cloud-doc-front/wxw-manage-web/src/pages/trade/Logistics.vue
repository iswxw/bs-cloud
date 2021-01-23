<!--
 * @Descripttion: 物流管理
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-08 16:03:47
 -->
<template>
  <v-data-table
    :headers="headers"
    :items="logisticsList"
    :items-per-page="5"
    class="elevation-1">
    <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.name }}</td>
        <td class="text-xs-left">{{ props.item.phone }}</td>
        <td class="text-xs-left">{{ props.item.zipcode }}</td>
        <td class="text-xs-left">{{ props.item.city }}</td>
        <td class="text-xs-left">{{ props.item.address }}</td>
        <td class="text-xs-left">{{ props.item.defaultAddress === false ? "否":"是"}}</td>
    </template>
    </v-data-table>
</template>
<script>
  export default {
     name: "logistics",
    data () {
      return {
        headers: [
          {
            text: '编号',
            align: 'center',
            value: 'id',
          },
          { text: '收货人', sortable: false, value: 'name' },
          { text: '收货电话', sortable: false, value: 'phone' },
          { text: '邮编', sortable: false, value: 'zipcode' },
          { text: '城市', sortable: false, value: 'city' },
          { text: '详细地址', sortable: false, value: 'address' },
          { text: '是否默认地址', sortable: false, value: 'defaultAddress' },
        ],
        logisticsList: [], //物流信息
      }
    },
    mounted(){
      // 获取物流信息
      this.getLogisticsList();
    },
    methods:{
      // 获取物流信息
      getLogisticsList(){
          this.$http.get("/order/address/list")
              .then(resp=>{
                 this.logisticsList = resp.data;  
            }).catch(()=>{
                 this.$message.error("登录状态已失效,请重新登录");
            });
      }
    }
  }
</script>
<style scoped>

</style>
