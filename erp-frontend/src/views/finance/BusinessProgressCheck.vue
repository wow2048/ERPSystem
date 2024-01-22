<template>
  <Layout>
    <Title title="查看销售明细表"></Title>
    <div>查看一段时间里的所有单据，筛选条件有：时间区间，单据类型，单据对象（如销售单、进货单的客户，工资单的员工等），业务员</div>
    <div><br/></div>
    <div>注：工资单、付款单、收款单只需一级审批，无需二级审批</div>
    <div><br/></div>

    <span><strong>请选择单据类型:</strong></span>
    &ensp;
    <el-select v-model="SheetType" placeholder="请选择单据类型">
      <el-option
        v-for="item in sheetTypeList"
        :key="item"
        :label="item"
        :value="item">
      </el-option>
    </el-select>
    &emsp;

    <span><strong>请选择单据对象:</strong></span>
    &ensp;
    <el-select v-model="customer" placeholder="请选择单据对象">
      <el-option
        v-for="item in customerList"
        :key="item"
        :label="item.label"
        :value="item.name">
      </el-option>
    </el-select>
    &emsp;

    <span><strong>请选择业务员: </strong></span>
    &ensp;
    <el-select v-model="salesman" placeholder="请选择业务员">
      <el-option
        v-for="item in salesmanList"
        :key="item"
        :label="item.label"
        :value="item.name">
      </el-option>
    </el-select>   
      
    <div><br/></div>
     <span><strong>请选择时间段: </strong></span>
     &ensp;
      <el-date-picker
        v-model="date"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期">
      </el-date-picker>
      
    &emsp;
    <el-button type="primary" size="medium" @click="getData">查询</el-button>
    &emsp;&emsp;&emsp;
    <el-button type="primary" size="medium" @click="exportAsExcel">导出EXCEL</el-button>
    <div><br/></div>
    <!--各种单据展示-->
    <div class="body">
      <el-tabs v-model="activeName" :stretch="true">

        <el-tab-pane label="待一级审批" name="PENDING_LEVEL_1">
          <div v-if="pendingLevel1List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else-if="this.currentType === '销售出货单'">
            <sale-list :list="pendingLevel1List" :type="1"/>
          </div>
          <div v-else-if="this.currentType === '销售退货单'">
            <sale-return-list :list="pendingLevel1List" :type="1"/>
          </div>
          <div v-else-if="this.currentType === '进货单'">
            <purchase-list :list="pendingLevel1List" :type="1"/>
          </div>
          <div v-else-if="this.currentType === '进货退货单'">
            <purchase-return-list :list="pendingLevel1List" :type="1"/>
          </div>
          <div v-else-if="this.currentType === '工资单'">
            <salary-sheet-list :list="pendingLevel1List" :type="1"/>
          </div>
          <div v-else-if="this.currentType === '付款单'">
            <payment-sheet-list :list="pendingLevel1List" :type="1"/>
          </div>  
          <div v-else-if="this.currentType === '收款单'">
            <receipt-sheet-list :list="pendingLevel1List" :type="1"/>
          </div>                       
        </el-tab-pane>

        <el-tab-pane label="待二级审批" name="PENDING_LEVEL_2">
          <div v-if="pendingLevel2List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else-if="this.currentType === '销售出货单'">
            <sale-list :list="pendingLevel2List" :type="2"/>
          </div>
          <div v-else-if="this.currentType === '销售退货单'">
            <sale-return-list :list="pendingLevel2List" :type="2"/>
          </div>
          <div v-else-if="this.currentType === '进货单'">
            <purchase-list :list="pendingLevel2List" :type="2"/>
          </div>
          <div v-else-if="this.currentType === '进货退货单'">
            <purchase-return-list :list="pendingLevel2List" :type="2"/>
          </div>                 
        </el-tab-pane>

        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else-if="this.currentType === '销售出货单'">
            <sale-list :list="successList" :type="3"/>
          </div>
          <div v-else-if="this.currentType === '销售退货单'">
            <sale-return-list :list="successList" :type="3"/>
          </div>
          <div v-else-if="this.currentType === '进货单'">
            <purchase-list :list="successList" :type="3"/>
          </div>
          <div v-else-if="this.currentType === '进货退货单'">
            <purchase-return-list :list="successList" :type="3"/>
          </div>
          <div v-else-if="this.currentType === '工资单'">
            <salary-sheet-list :list="successList" :type="3"/>
          </div> 
          <div v-else-if="this.currentType === '付款单'">
            <payment-sheet-list :list="successList" :type="3"/>
          </div>    
          <div v-else-if="this.currentType === '收款单'">
            <receipt-sheet-list :list="successList" :type="3"/>
          </div>                            
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else-if="this.currentType === '销售出货单'">
            <sale-list :list="failureList" :type="4"/>
          </div>
          <div v-else-if="this.currentType === '销售退货单'">
            <sale-return-list :list="failureList" :type="4"/>
          </div>
          <div v-else-if="this.currentType === '进货单'">
            <sale-list :list="failureList" :type="4"/>
          </div>
          <div v-else-if="this.currentType === '进货退货单'">
            <purchase-return-list :list="failureList" :type="4"/>
          </div>
          <div v-else-if="this.currentType === '工资单'">
            <salary-sheet-list :list="failureList" :type="4"/>
          </div>   
          <div v-else-if="this.currentType === '付款单'">
            <payment-sheet-list :list="failureList" :type="4"/>
          </div>   
          <div v-else-if="this.currentType === '收款单'">
            <receipt-sheet-list :list="failureList" :type="4"/>
          </div>                           
        </el-tab-pane>

      </el-tabs>
    </div>    
    
  </Layout>
</template>

<script>
  import Layout from "@/components/content/Layout";
  import Title from "@/components/content/Title";
  import { formatDate } from "@/common/utils";
  import * as XLSX from 'xlsx';
  import { getAllCustomer } from '@/network/purchase';
  import { findAllUsers } from '@/network/auth';
  import { getSheet } from '@/network/finance';

  import SaleList from '@/views/sale/components/SaleList';
  import SaleReturnList from '@/views/sale/components/SaleReturnList';
  import PurchaseList from '@/views/purchase/components/PurchaseList';
  import PurchaseReturnList from '@/views/purchase/components/PurchaseReturnList';
  import PaymentSheetList from '@/views/finance/components/PaymentSheetList';
  import ReceiptSheetList from "@/views/finance/components/ReceiptSheetList"
  import SalarySheetList from '@/views/finance/components/SalarySheetList';
 
  export default {
    components: {
        Layout,
        Title,
        SaleList,
        SaleReturnList,
        PurchaseList,
        PurchaseReturnList,
        PaymentSheetList,
        ReceiptSheetList,
        SalarySheetList,
    },

    data() {
    return {
      sheetTypeList:  ['销售出货单','销售退货单','进货单','进货退货单','付款单',
      '收款单','工资单','现金费用单','报溢单','报损单','赠送单'],  
      customerList: [],
      salesmanList: [],
      date: '',
      SheetType: '',
      customer: '',
      salesman: '',

      currentType: '',
      currentCustomer: '',
      currentSalesman: '',

      activeName: 'PENDING_LEVEL_1',
      all: [],
      pendingLevel1List: [],
      pendingLevel2List: [],
      successList: [],
      failureList: [],
    }
  },

  computed: {
    beginDate: function(){
      return this.date === '' ? '' : formatDate(this.date[0])
    },
    endDate: function() {
      return this.date === '' ? '' : formatDate(this.date[1])
    }
  },

  mounted() {
    getAllCustomer({ params : { type: 'SELLER' } }).then(_res => {
      let res = _res.result
      res.forEach(item => this.customerList.push( {name : item.name ,label : item.name + '（客户）'}))
    })
    getAllCustomer({ params : { type: 'SUPPLIER' } }).then(_res => {
      let res = _res.result
      res.forEach(item => this.customerList.push( {name : item.name ,label : item.name + '（客户）'}))
    })
    findAllUsers({}).then(_res => {
      let sale_staff_list = _res.result.filter(item => item.role === 'SALE_STAFF')
      let sale_manage_list = _res.result.filter(item => item.role === 'SALE_MANAGER')
      let financial_staff_list = _res.result.filter(item => item.role === 'FINANCIAL_STAFF')
      sale_staff_list.forEach(item  => this.salesmanList.push({name : item.name ,label : item.name + '（销售人员）'}))
      sale_manage_list.forEach(item  => this.salesmanList.push({name : item.name ,label : item.name + '（销售经理）'}))
      financial_staff_list.forEach(item  => this.salesmanList.push({name : item.name ,label : item.name + '（财务人员）'}))
      let staff_list = _res.result.filter(item => item.role != 'ADMIN')
      staff_list.forEach(item  => this.customerList.push({name : item.name ,label : item.name + '（员工）'}))
    })
  },


  methods: { 
     // 查询
    getData() {
      const config = {
        params: {
          type: this.SheetType,
          beginDateStr: this.beginDate,
          endDateStr: this.endDate,
          customer: this.customer,
          salesman: this.salesman
        }
      }
     
    this.currentType = this.SheetType
    this.currentCustomer = this.customer
    this.currentSalesman = this.salesman
    if(this.SheetType == '' || this.beginDate == '' || this.endDate == ''||
     this.customer == '' || this.salesman == '') {
        this.$message.error('条件不足!')
        return
      }

    this.pendingLevel1List = []
    this.pendingLevel2List = []
    this.successList = []
    this.failureList = []
    this.all = []
    
    getSheet(config).then(_res => {
        if(_res.result == null || _res.result.length == 0) {
          this.$message.error('未能查询到相关数据!')
        }else{
          this.all = _res.result
          this.pendingLevel1List = this.all.filter(item => item.state === '待一级审批' || item.state === '待审批')
          this.pendingLevel2List = this.all.filter(item => item.state === '待二级审批')
          this.successList = this.all.filter(item => item.state === '审批完成')
          this.failureList = this.all.filter(item => item.state === '审批失败')
          this.$message.success('查询成功!')
        }
      })
    },

    // 导出Excel
    exportAsExcel() {
      if(this.all.length == 0) {
        this.$message.error('暂无记录!')
        return
      }

      let a = this.all;
      let s = a.length;
      let arr = [];
      for(let i = 0; i < s; i++){
        let obj = {};
        obj['单据类型'] = this.currentType;
        obj['单据编号'] = a[i].id;
        obj['单据对象'] = this.currentCustomer;
        obj['业务员'] = this.currentSalesman;
        obj['状态'] = a[i].state;       
        arr[i] = obj;
      }

      let jsonText = Array.from(arr);
      const data = XLSX.utils.json_to_sheet(jsonText);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb,data,'销售明细表');
      XLSX.writeFile(wb,"销售明细表.xlsx")
      alert("导出Excel成功");
    },


  } 

  };
</script>

<style scoped>
</style>