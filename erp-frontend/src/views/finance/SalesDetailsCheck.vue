<template>
  <Layout>
    <Title title="查看销售明细表"></Title>
    <div>统计一段时间内商品的销售情况（包括销售和销售后退货），筛选条件有：时间区间，商品名，客户，业务员</div>

      <div><br/></div>
      <span><strong>请选择商品名: </strong></span>
      &ensp;
      <el-select v-model="productName" placeholder="请选择商品名">
        <el-option
          v-for="item in productList"
          :key="item"
          :label="item"
          :value="item">
        </el-option>
      </el-select>
      &emsp;

      <span><strong>请选择客户:</strong></span>
      &ensp;
      <el-select v-model="customer" placeholder="请选择客户">
        <el-option
          v-for="item in customerList"
          :key="item"
          :label="item"
          :value="item">
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
    
    <div class="mt15">
          <span><strong>以下为商品的销售情况: </strong></span>
    </div>

    <!-- 用于展示查询得到的商品销售情况 -->
    <div style="margin-top: 10px">
      <el-table
        :data="SalesDetailsList"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="time"
          label="时间"
          width="150">
        </el-table-column>
        <el-table-column
          prop="productName"
          label="商品名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="productType"
          label="商品型号"
          width="200">
        </el-table-column>
        <el-table-column
          prop="quantity"
          label="商品数量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="unitPrice"
          label="单价"
          width="100">
        </el-table-column>
        <el-table-column
          prop="totalPrice"
          label="总金额"
          width="100">
        </el-table-column>
        <el-table-column
          prop="state"
          label="状态"
          width="120">
        </el-table-column>        
        <el-table-column
          prop="remark"
          label="备注"
          width="120">
        </el-table-column>
      </el-table>
    </div>

  </Layout>
</template>

<script>
  import Layout from "@/components/content/Layout";
  import Title from "@/components/content/Title";
  import { formatDate } from "@/common/utils";
  import { getAllCustomer } from '@/network/purchase';
  import { getAllCommodity } from '@/network/commodity';
  import { findAllUsers } from '@/network/auth';
  import { getSalesDetais } from '@/network/finance';
  import * as XLSX from 'xlsx';

  export default {
    components: {
        Layout,
        Title
    },
    data() {
    return {
      date: '',
      productList:  [],  
      customerList: [],
      salesmanList: [],

      productName: '',
      customer: '',
      salesman: '',

      SalesDetailsList: [],
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
    getAllCommodity({}).then(_res => {
      let res = _res.result
      res.forEach(item => this.productList.push(item.name))
    })
    getAllCustomer({ params : { type: 'SELLER' } }).then(_res => {
      let res = _res.result
      res.forEach(item => this.customerList.push( item.name ))
    })
    getAllCustomer({ params : { type: 'SUPPLIER' } }).then(_res => {
      let res = _res.result
      res.forEach(item => this.customerList.push( item.name ))
    })
    findAllUsers({}).then(_res => {
      let sale_staff_list = _res.result.filter(item => item.role === 'SALE_STAFF')
      let sale_manage_list = _res.result.filter(item => item.role === 'SALE_MANAGER')
      sale_staff_list.forEach(item  => this.salesmanList.push({name : item.name ,
       label :  item.name + '（销售人员）'}))
       sale_manage_list.forEach(item  => this.salesmanList.push({name : item.name ,
       label :  item.name + '（销售经理）'}))
    })
  },


  methods: {
     // 查询
    getData() {
      const config = {
        params: {
          beginDateStr: this.beginDate,
          endDateStr: this.endDate,
          productName: this.productName,
          customer: this.customer,
          salesman: this.salesman,
        }
      }


      if(this.beginDate == '' || this.endDate == '' || this.productName == ''
        || this.customer == '' || this.salesman == '') {
          this.$message.error('条件不足!')
          return
        }
      
    getSalesDetais(config).then(_res => {
        if(_res.result == null || _res.result.length == 0) {
          this.SalesDetailsList = []
          this.$message.error('该时间段内无商品销售记录!')
        }else{
          this.SalesDetailsList = _res.result
          this.$message.success('查询成功!')
        }
      })
    },
    
    // 导出Excel
    exportAsExcel() {
      if(this.SalesDetailsList.length == 0) {
        this.$message.error('暂无记录!')
        return
      }

      let a = this.SalesDetailsList;
      let s = a.length;
      let arr = [];
      for(let i = 0; i < s; i++){
        let obj = {};
        obj['时间'] = a[i].time;
        obj['商品名称'] = a[i].productName;
        obj['商品型号'] = a[i].productType;
        obj['商品数量'] = a[i].quantity;
        obj['单价'] = a[i].unitPrice;
        obj['总金额'] = a[i].totalPrice;
        obj['状态'] = a[i].state;
        obj['备注'] = a[i].remark;
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