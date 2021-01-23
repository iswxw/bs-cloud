<template>
  <v-card>
    <v-flex xs12
            sm10>
      <v-tree url="/item/category/list"
              :isEdit="isEdit"
              @handleAdd="handleAdd"
              @handleEdit="handleEdit"
              @handleDelete="handleDelete"
              @handleClick="handleClick" />
    </v-flex>
  </v-card>
</template>
<script>
//导入静态树组件
//:treeData="treeData"
//import { treeData } from "../../mockDB";  
export default {
  name: "category",
  data() {
    return {
      isEdit: true,
      //treeData:treeData
    };
  },
  methods: {
    handleAdd(node) {
      console.log("add .... ");
      console.log(node);
    },
    handleEdit(id, name) {
      console.log("edit... id: " + id + ", name: " + name);
    },
    handleDelete(id) {
      console.log("被删除节点的id delete ... " + id);
      //删除节点
      this.$http.delete("/item/category/delete/"+id)
                .then(res=>{
                    this.$message.success("删除成功");
                }).catch(error=>{
                   this.$message.error("删除失败");
                });
    },
    handleClick(node) {
      // //提交当前节点信息  更新
      this.$http.post("/item/category/saveOrUpdate",node)
                .then(resp=>{  // 成功时的回调
                    console.log("成功时的回调:",resp);
                     // this.$message.success("修改节点成功");
                }).catch(resp=>{ //失败时的回调
                    console.log("失败时的回调:",resp);
                     //this.$message.error("修改节点失败");
                });
      console.log("被点击节点的node对象,包含全部信息");
    }
  }
};
</script>

<style scoped>
</style>
