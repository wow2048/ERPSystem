<template>
  <Layout>

    <Title title="制定工资单"></Title>
    <el-button type="primary" size="medium" @click="addSheet">制定工资单</el-button>

    <div class="body">

      <el-tabs v-model="activeName" :stretch="true">

        <el-tab-pane label="待审批" name="PENDING">
          <div v-if="pendingList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <salary-sheet-list :list="pendingList" :type="1" @refresh="getSalarySheet()"/>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <salary-sheet-list :list="successList" :type="2"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <salary-sheet-list :list="failureList" :type="3"/>
          </div>
        </el-tab-pane>

      </el-tabs>

    </div>

<!-- 创建窗口 -->
    <el-dialog
      title="创建工资单"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="salarySheet" label-width="80px" ref="receiptSheet" :rules="rules">

          <el-form-item label="员工: " prop="staffId">
            <el-select v-model="salarySheet.staffId" placeholder="请选择员工" @change="selectTrigger(salarySheet.staffId)">
              <el-option
                v-for="item in staffs"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        <el-form-item label="账户: " prop="bankId">
          <el-select v-model="salarySheet.bankId" placeholder="请选择公司银行账户" style="width: 50%; margin-right: 5%">
            <el-option
              v-for="item in accounts"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="应付工资: ">
          <el-input v-model="payableSalary" autocomplete="off" disabled></el-input>
        </el-form-item>
          
        </el-form>
        

      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit()">立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="通知"
      :visible.sync="informationVisible"
      width="40%"
      :before-close="handleClose">
      <div v-for="item in unreadList" :key="item.id" style="margin-bottom: 35px; margin-top: 17rpx; font-size: large;">

        <el-row style="margin-bottom: 5px">
          <span ><strong>id: </strong>{{item.id}}的单据审核通过</span>
        </el-row>

        <el-row>
          <el-col :span="10">银行账户id: {{item.bankId}}</el-col>
          <el-col :span="14">收入金额: {{item.payableSalary}}</el-col>
        </el-row>

      </div>

    </el-dialog>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import SalarySheetList from "./components/SalarySheetList"
import { getAllSalarySheet, getAllAccount, makeSalarySheet, salarySheetUnread, getPayableSalary} from "../../network/finance"
import { getAllStaffById } from "../../network/staff"

export default {

  name: 'SalarySheetView',
  components: {
    Layout,
    Title,
    SalarySheetList
  },

  data() {
    return {
      activeName: 'PENDING',
      unreadList: [],
      salarySheetList: [],
      pendingList: [],
      successList: [],
      failureList: [],
      dialogVisible: false,
      informationVisible: false,
      staffs: [],
      accounts: [],
      payableSalary: 0,
      salarySheet: {
        
      },
      rules: {
        staffId: [
          { required: true, message: '请选择一个员工', trigger: 'change' }
        ],
        bankId: [
          { required: true, message: '请选择一个银行账户', trigger: 'change' }
        ]
      },
    }
  },

  mounted() {
    this.getSalarySheet()
    salarySheetUnread({ params : { operator: sessionStorage.getItem("name")} }).then(_res => {
      this.unreadList = _res.result
      if (_res.result.length != 0) {
        this.informationVisible = true
      }
    })
    getAllStaffById({}).then(_res => {
      this.staffs = _res.result
    })
    getAllAccount({}).then(_res => {
      this.accounts = _res.result
    })
  },

  methods: {
    selectTrigger(val){
      let config = {
        params: {
          id: val
        }
      }
      console.log(config)
      console.log(val)
      getPayableSalary(config).then(_res => {
        this.payableSalary = _res.result
      })
    },
    getSalarySheet() {
      getAllSalarySheet({}).then(_res => {
        this.salarySheetList = _res.result
        this.pendingList = this.salarySheetList.filter(item => item.state === '待审批')
        this.successList = this.salarySheetList.filter(item => item.state === '审批完成')
        this.failureList = this.salarySheetList.filter(item => item.state === '审批失败')
      })
    },

    addSheet(){
        this.dialogVisible = true
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },

    submit() {
      this.salarySheet.id = null
      this.salarySheet.staffName = null
      this.salarySheet.staffAccount = null
      this.salarySheet.operator = sessionStorage.getItem("name")
      this.salarySheet.state = null
      makeSalarySheet(this.salarySheet).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.dialogVisible = false
          this.getSalarySheet()
        }
      })
    },

  }
}
</script>

<style lang="scss" scoped>
.body {
  margin: 0 auto;
  margin-top: 10px;
  height: 80vh;
  overflow-y: auto;
  width: 100%;
  background: rgba($color: #fff, $alpha: 0.5);
}
</style>