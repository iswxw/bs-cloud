<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-03-27 19:30:33
 -->
<template>
 <v-data-table
    :headers="headers"
    :items="orderList"
    :items-per-page="5"
    class="elevation-1">
    <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.orderId }}</td>
        <td class="text-xs-left">{{ props.item.actualPay }}</td>
        <td class="text-xs-left" >{{ props.item.buyerNick }}</td>
        <td class="text-xs-left">{{ props.item.createTime }}</td>
        <td class="text-xs-left">{{ props.item.receiver }}</td>
        <td class="text-xs-left">{{ props.item.receiverMobile }}</td>
        <td class="text-xs-left">{{ props.item.receiverAddress }}</td>
        <td class="text-xs-left">{{ props.item.paymentType == true?"在线支付":"货到付款" }}</td>
    </template>
    </v-data-table>
</template>
<script>
  export default {
     name: "order",
    data () {
      return {
        headers: [
          {
            text: '订单号',
            align: 'center',
            value: 'orderId',
          },
          { text: '总金额（元）', value: 'actualPay'},
          { text: '买家昵称', sortable: false, value: 'buyerNick' },
          { text: '下单时间',  value: 'createTime' },
          { text: '收货人', sortable: false, value: 'receiver' },
          { text: '收货电话', sortable: false, value: 'receiverMobile' },
          { text: '收货地址', sortable: false, value: 'receiverAddress' },
          { text: '支付类型', sortable: false, value: 'paymentType' },
        ],
        orderList: [], //订单信息
      }
    },
    mounted(){
      // 获取订单信息
      this.getOrderList();
    },
    methods:{
      // 获取订单信息
      getOrderList(){
          this.$http.get("/order/list")
              .then(resp=>{
                 this.orderList = resp.data.items; 
         }).catch(()=>{
                 this.$message.error("登录状态已失效,请重新登录");
         });
      }
    }
  }
</script>
<style scoped>

</style>
