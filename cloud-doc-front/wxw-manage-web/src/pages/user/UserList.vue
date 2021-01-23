<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-08 17:15:01
 -->
<template>
 <v-container fluid grid-list-md class="container">
    <v-layout row wrap>
      <v-flex xs10 md6>
        <v-card class="card2">
          <v-card-text class="px2">
              用户总数量: {{ userCount }} 人/次
          </v-card-text>
        </v-card>
        <v-card class="card2">
          <v-card-text class="px2">
              最近活跃度: {{ latestCount }}
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
    <v-flex xs10 md6>
        <v-card class="card3">
          <v-card-text class="px2">
              <div ref="sale" style="width: 100%;height:350px"></div>
          </v-card-text>
        </v-card>
      </v-flex>
  </v-container>
</template>

<script>
// 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts'); 
require('echarts/lib/chart/line')
 // 以下的组件按需引入
require('echarts/lib/component/tooltip')   // tooltip组件
require('echarts/lib/component/title')   //  title组件
require('echarts/lib/component/legend')  // legend组件
  export default {
    name: "userlist",
    data(){
      return{
         userCount:0,
         latestCount:0,
      }
    },
    mounted(){
      this.$nextTick(()=>{
        // 销售额
        var sale = echarts.init(this.$refs.sale);
        // 指定图表的配置项和数据
        var option = {
          title: {
            text: '用户活跃度统计'
          },
          tooltip: {
             trigger: 'axis'
          },
          legend: {
            data:['按照时间统计','按照活跃频率统计','按照地区统计']
          },
           grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
         },
         toolbox: {
            feature: {
                saveAsImage: {}
            }
         },
          xAxis: {
            type:'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
          },
          yAxis: {
            type: 'value'
         },
         series: [
            {
                name: '按照时间统计',
                data: [820, 932, 901, 934, 1290, 1330, 1320],
                type: 'line'
            },
            {
                name: '按照活跃频率统计',
                data: [620, 711, 823, 934, 1445, 1456, 1178],
                type: 'line'
            },
            {
                name: '按照地区统计',
                data: [612, 920, 1140, 1160, 1190, 1234, 1321],
                type: 'line'
            },]
        };

        // 使用刚指定的配置项和数据显示图表。
        sale.setOption(option);
      }); 
      
      // 实时更新用户量
      this.getCount()
    },
    methods:{
      getCount(){
        this.$http.get("/user/count")
            .then(resp=>{
                this.userCount = resp.data;
                this.latestCount = 2000;
            });
        }
    }

  }
</script>

<style scoped>
  .container{
      margin-left: 50px;
      flex: 1;
  }
  .card2{
      width: 200px;
  }
  .card3{
      width: 800px;
  }
</style>
