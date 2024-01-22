<template>
  <Layout>
    <Title title="审批"></Title>
    <div>
      <el-select v-model="sheetType" placeholder="请选择要审批的单据" style="width: 90%; margin: 0 auto" @change="selectTrigger(sheetType)">
        <el-option
          v-for="item in typeList"
          :key="item"
          :label="item"
          :value="item">
        </el-option>
      </el-select>
    </div>
    <div class="body">

      <el-tabs v-model="activeName" :stretch="true">

        <el-tab-pane label="待审批" name="PENDING">
          <div v-if="pendingList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else-if="sheetType === '付款单'">
            <payment-sheet-list :list="pendingList" :type="1" @refresh="getPayment()"/>
          </div>

          <div v-else-if="sheetType === '收款单'">
            <receipt-sheet-list :list="pendingList" :type="1" @refresh="getReceipt()"/>
          </div>

          <div v-else-if="sheetType === '工资单'">
            <salary-sheet-list :list="pendingList" :type="1" @refresh="getSalary()"/>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else-if="sheetType === '付款单'">
            <payment-sheet-list :list="successList" :type="2"/>
          </div>

          <div v-else-if="sheetType === '收款单'">
            <receipt-sheet-list :list="successList" :type="2"/>
          </div>

          <div v-else-if="sheetType === '工资单'">
            <salary-sheet-list :list="successList" :type="2"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else-if="sheetType === '付款单'">
            <payment-sheet-list :list="failureList" :type="3"/>
          </div>

          <div v-else-if="sheetType === '收款单'">
            <receipt-sheet-list :list="failureList" :type="3"/>
          </div>

          <div v-else-if="sheetType === '工资单'">
            <salary-sheet-list :list="failureList" :type="3"/>
          </div>
        </el-tab-pane>

      </el-tabs>

    </div>
  </Layout>
</template>

<script>
  import Layout from "@/components/content/Layout";
  import Title from "@/components/content/Title";
  import { getAllPaymentSheet , getAllReceiptSheet, getAllSalarySheet} from "../../network/finance"
  import PaymentSheetList from '@/views/finance/components/PaymentSheetList';
  import ReceiptSheetList from "@/views/finance/components/ReceiptSheetList"
  import SalarySheetList from '@/views/finance/components/SalarySheetList';
  export default {
    components: {
        Layout,
        Title,
        PaymentSheetList,
        ReceiptSheetList,
        SalarySheetList,
    },

    data() {
    
    return {
      activeName: 'PENDING',
      sheetList: [],
      pendingList: [],
      successList: [],
      failureList: [],
      typeList: ['付款单', '收款单', '工资单'],
      sheetType: '',
      }
    },

    methods: {
      selectTrigger(val){
        if (val === '付款单'){
          this.getPayment()
        } else if (val === '收款单'){
          this.getReceipt()
        } else if (val === '工资单'){
          this.getSalary()
        }
      },

      getPayment(){
        getAllPaymentSheet({}).then(_res => {
          this.sheetList = _res.result
          this.pendingList = this.sheetList.filter(item => item.state === '待审批')
          this.successList = this.sheetList.filter(item => item.state === '审批完成')
          this.failureList = this.sheetList.filter(item => item.state === '审批失败')
        })
      },

      getReceipt(){
        getAllReceiptSheet({}).then(_res => {
          this.sheetList = _res.result
          this.pendingList = this.sheetList.filter(item => item.state === '待审批')
          this.successList = this.sheetList.filter(item => item.state === '审批完成')
          this.failureList = this.sheetList.filter(item => item.state === '审批失败')
        })
      },

      getSalary(){
        getAllSalarySheet({}).then(_res => {
          this.sheetList = _res.result
          this.pendingList = this.sheetList.filter(item => item.state === '待审批')
          this.successList = this.sheetList.filter(item => item.state === '审批完成')
          this.failureList = this.sheetList.filter(item => item.state === '审批失败')
        })
      }
    },


  };
</script>

<style scoped>
</style>
