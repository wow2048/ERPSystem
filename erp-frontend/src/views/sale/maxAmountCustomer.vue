<template>
  <Layout>
    <Title title="查询消费总金额最大的客户"></Title>

      <span><strong>请选择业务员: </strong></span>
      &ensp;
      <el-select v-model="salesman" placeholder="请选择业务员">
        <el-option
          v-for="item in operatorList"
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
      <div><br/></div>

    <div class="mt15">
          <span><strong>该销售人员在这个时间段内销售额最大的客户通过其购买的商品的总金额为: </strong></span>
          &emsp;
          <span>{{totalFinalAmount}}</span>
    </div>

    <div><br/></div>
    <div class="mt15">
          <span><strong>以下为客户详细信息: </strong></span>
    </div>

    <!-- 用于展示查询得到的客户信息 -->
    <div style="margin-top: 10px">
      <el-table
        :data="customer"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="id"
          label="客户编号"
          width="80">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="80">
        </el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          width="80">
        </el-table-column>
        <el-table-column
          prop="level"
          label="级别"
          width="50">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="150">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="120">
        </el-table-column>
        <el-table-column
          prop="zipcode"
          label="邮编"
          width="100">
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          width="200">
        </el-table-column>
        <el-table-column
          prop="lineOfCredit"
          label="应收额度(元)"
          width="120">
        </el-table-column>
        <el-table-column
          prop="receivable"
          label="应收(元)"
          width="100">
        </el-table-column>
        <el-table-column
          prop="payable"
          label="应付(元)"
          width="100">
        </el-table-column>
        <el-table-column
          prop="operator"
          label="操作员"
          width="100">
        </el-table-column>
      </el-table>
    </div>

  </Layout>
</template>


<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { formatDate } from "@/common/utils";
import { findAllUsers } from '@/network/auth';
import { getMaxAmountCustomerOfSalesmanByTime } from "@/network/sale";
export default {
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date: '',
      customer: [],
      salesman: '',

      operatorList: [],
      totalFinalAmount: 0,
      a : [],
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
    findAllUsers({}).then(_res => {
      let sale_staff_list = _res.result.filter(item => item.role === 'SALE_STAFF')
      let sale_manage_list = _res.result.filter(item => item.role === 'SALE_MANAGER')
      sale_staff_list.forEach(item  => this.operatorList.push({name : item.name ,
       label :  item.name + '（销售人员）'}))
       sale_manage_list.forEach(item  => this.operatorList.push({name : item.name ,
       label :  item.name + '（销售经理）'}))
    })
  },


  methods: {
    getData() {
      const config = {
        params: {
          salesman: this.salesman,
          beginDateStr: this.beginDate,
          endDateStr: this.endDate
        }
      }

      if(this.salesman == '' || this.beginDate == '' || this.endDate == '') {
          this.$message.error('条件不足!')
          return
        }

     getMaxAmountCustomerOfSalesmanByTime(config).then(_res => {
        if(_res.result == null){
          this.customer = []
          this.totalFinalAmount = 0
          this.$message.error('暂无记录!')
        }else{
          this.customer = [_res.result.customerPO]
          this.totalFinalAmount = _res.result.totalFinalAmount
          this.$message.success('查询成功!')
        }
        
      })
    },
  }
};
</script>

<style scoped lang="scss">
.mt15 {
  margin-top: 15px;
}
</style>
