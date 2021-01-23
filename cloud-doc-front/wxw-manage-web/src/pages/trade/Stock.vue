<!--
 * @Descripttion: 库存管理
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-08 16:02:10
 -->
<template>
 <v-data-table
    :headers="headers"
    :items="stockList"
    :items-per-page="5"
    class="elevation-1">
    <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.skuId }}</td>
        <td class="text-xs-left">{{ props.item.seckillStock }} </td>
        <td class="text-xs-left" >{{ props.item.seckillTotal }} </td>
        <td class="text-xs-left">{{ props.item.stock }} </td>
    </template>
    </v-data-table>
</template>
<script>
  export default {
     name: "stock",
    data () {
      return {
        headers: [
          {
            text: '商品SKUID',
            align: 'center',
            value: 'skuId',
          },
          { text: '可秒杀库存 （件）', value: 'seckillStock'},
          { text: '秒杀总数量 （件）', value: 'seckillTotal' },
          { text: '库存数量 （件）',  value: 'stock' }
        ],
        stockList: [], //库存信息
      }
    },
    mounted(){
      // 获取库存信息
      this.getStockList();
    },
    methods:{
      // 获取库存信息
      getStockList(){
          this.$http.get("/order/stock/list")
              .then(resp=>{
                 this.stockList = resp.data; 
         }).catch(()=>{
          this.$message.error("登录状态已失效,请重新登录");
         });
      }
    }
  }
</script>
<style scoped>

</style>
