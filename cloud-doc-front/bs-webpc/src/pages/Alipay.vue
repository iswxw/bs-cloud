<!--
 * @Descripttion: 支付页
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-16 15:58:26
 -->
<template>
    <div v-html="apply">
        {{apply}}
    </div>
</template>
<script>
export default {
    data(){
        return{
          apply:""
        }
    },
    mounted(){
         let form = this.$route.query.htmls;
         this.apply = form;
         this.$nextTick(()=>{
             document.forms[0].submit();
        });
    },  
    watch:{
        $route(to,from){
        console.log(to);
        console.log(from);
        }

   }
}
</script>