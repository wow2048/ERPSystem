<template>
  <div class="card">
    <el-card v-for="(item) in list" :index="item.index" :key="item.id" shadow="hover">

      <template #header>

        <el-row>
          <el-col :span="18">
            <span><strong>id: </strong>{{item.id}}</span>
            <el-button v-if="authorization() == true" style="margin-left: 10px"
              type="primary" icon="el-icon-check" circle size="mini" @click="approval(item.id)"></el-button>
            <el-button v-if="authorization() == true" 
              type="danger" icon="el-icon-close" circle size="mini" @click="deny(item.id)"></el-button>
          </el-col>
        </el-row>

      </template>

        <el-table
          :data="[item]"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">

          <el-table-column
            prop="staffId"
            label="员工id"
            width="100">
          </el-table-column>

          <el-table-column
            prop="staffName"
            label="员工姓名"
            width="100">
          </el-table-column>

          <el-table-column
            prop="bankId"
            label="公司银行账户id"
            width="100">
          </el-table-column>

          <el-table-column
            prop="staffAccount"
            label="员工银行账户"
            width="200">
          </el-table-column>

          <el-table-column
            prop="payableSalary"
            label="应发工资"
            width="150">
          </el-table-column>
          
          <el-table-column
            prop="tax"
            label="扣除税款"
            width="150">
          </el-table-column>

          <el-table-column
            prop="actualSalary"
            label="实发工资"
            width="150">
          </el-table-column>

          <el-table-column
            prop="operator"
            label="操作员"
            width="150">
          </el-table-column>

        </el-table>

    </el-card>

  </div>

</template>

<script>

import { salarySheetApproval } from '../../../network/finance'
export default {
  name: "SalarySheetList",
  props: {
    list: Array,
    type: Number,
  },
  data() {
    return {

    }
  },
  mounted() {

  },

  methods: {
    authorization() {
      if (this.type === 1 && sessionStorage.getItem('role') === 'GM') {
        return true
      }else {
        return false
      }
    },

    approval(id) {
      let config = {
        params: {
          sheetId: id,
          state: 'SUCCESS'
        }
      }
      salarySheetApproval(config).then(res => {
        this.$emit("refresh")
        this.$message({
          message: '操作成功!',
          type: 'success'
        })
      })
    },

    deny(id) {
      let config = {
        params: {
          sheetId: id,
          state: 'FAILURE'
        }
      }
      salarySheetApproval(config).then(res => {
        this.$emit("refresh")
        this.$message({
          message: '操作成功!',
          type: 'success'
        })
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.card {
  width: 80%;
  margin: 0 auto;
  .button {
    float: right;
    padding: 3px 0
  }
}
.el-card {
  margin-bottom: 20px;
  background: #EEF7F2;
}
</style>