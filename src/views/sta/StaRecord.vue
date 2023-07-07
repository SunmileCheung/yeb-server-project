<template>
  <el-table
      size="small"
      stripe
      border>
    <el-table-column
        prop="number"
        label="序号"
        width="70">
    </el-table-column>
    <el-table-column
        prop="createDate"
        label="添加时间"
        width="250">
    </el-table-column>
    <el-table-column
        prop="record"
        label="操作记录"
        width="600">
    </el-table-column>
    <el-table-column
        prop="id"
        label="操作ID"
        width="150">
    </el-table-column>
    <el-table-column
        label="操作">
      <template slot-scope="scope">
        <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
      </template>


<script>
export default {
  name: "StaRecord",
  data() {
    return {

    }
  },
  methods:{
    handleDelete(index, row) {
      console.log(index, row);
    }
  }
}
</script>

<style scoped>

</style>