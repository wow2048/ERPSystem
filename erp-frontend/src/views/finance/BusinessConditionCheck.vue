<template>
  <Layout>
    <Title title="查看经营情况表"></Title>
    <div>统计显示一段时间内的经营收支状况和利润。</div>
    
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
    
    <div v-if="showData === true" class="mt15">

    <h4>以下为&nbsp;{{beginDate}}&nbsp;至&nbsp;{{endDate}}&nbsp;内的经营情况</h4>      
    <!-- 用于展示查询得到的商品销售情况 -->
    <div style="margin-top: 10px">
      <el-table
        :data="businessCondition"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="salesRevenue"
          label="销售收入"
          width="100">
        </el-table-column>
        <el-table-column
          prop="costChange"
          label="成本调价收入"
          width="150">
        </el-table-column>
        <el-table-column
          prop="gap"
          label="进货退货差价"
          width="150">
        </el-table-column>
        <el-table-column
          prop="voucher"
          label="代金券与实际收款差额收入"
          width="200">
        </el-table-column>
        <el-table-column
          prop="discount"
          label="收入折让"
          width="100">
        </el-table-column>
        <el-table-column
          prop="totalRevenue"
          label="总收入"
          width="100">
        </el-table-column>
        <el-table-column
          prop="salesCost"
          label="销售成本"
          width="100">
        </el-table-column>
        <el-table-column
          prop="commodityLossCost"
          label="商品报损支出"
          width="150">
        </el-table-column>
        <el-table-column
          prop="commodityGiveCost"
          label="商品赠出支出"
          width="150">
        </el-table-column>
        <el-table-column
          prop="laborCost"
          label="人力成本"
          width="100">
        </el-table-column> 
        <el-table-column
          prop="totalCost"
          label="总支出"
          width="100">
        </el-table-column> 
        <el-table-column
          prop="profit"
          label="利润"
          width="100">
        </el-table-column>                                                                         
      </el-table>
    </div>

  </div>

  </Layout>
</template>

<script>
  import Layout from "@/components/content/Layout";
  import Title from "@/components/content/Title";
  import { formatDate } from "@/common/utils";
  import { getBusinessCondition } from '@/network/finance';
  import * as XLSX from 'xlsx';

  export default {
    components: {
        Layout,
        Title
    },
    data() {
    return {
      date: '',  
      businessCondition: [],
      showData: false
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

  methods: {
     // 查询
    getData() {
      const config = {
        params: {
          beginDateStr: this.beginDate,
          endDateStr: this.endDate,
        }
      }


      if(this.beginDate == '' || this.endDate == '') {
          this.$message.error('条件不足!')
          return
        }
      
    getBusinessCondition(config).then(_res => {
        this.showData = true
        if(_res.result == null || _res.result.length == 0) {
          this.businessCondition = []
          this.$message.error('该时间段暂无记录!')
        }else{
          this.businessCondition = [_res.result]
          this.$message.success('查询成功!')
        }
      })
    },
    
    // 导出Excel
    exportAsExcel() {
      if(this.businessCondition.length == 0) {
        this.$message.error('暂无记录!')
        return
      }

      let a = this.businessCondition;
      let s = a.length;
      let arr = [];
      for(let i = 0; i < s; i++){
        let obj = {};
        obj['销售收入'] = a[i].salesRevenue;
        obj['商品报溢收入'] = a[i].commodityOverflow;
        obj['成本调价收入'] = a[i].costChange;
        obj['进货退货差价'] = a[i].gap;
        obj['代金券与实际收款差额收入'] = a[i].voucher;
        obj['收入折让'] = a[i].discount;
        obj['总收入'] = a[i].totalRevenue;
        obj['销售成本'] = a[i].salesCost;
        obj['商品报损支出'] = a[i].commodityLossCost;
        obj['商品赠出支出'] = a[i].commodityGiveCost;
        obj['人力成本'] = a[i].laborCost;
        obj['总支出'] = a[i].totalCost;
        obj['利润'] = a[i].profit;        
        arr[i] = obj;
      }

      let jsonText = Array.from(arr);
      const data = XLSX.utils.json_to_sheet(jsonText);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb,data,'经营情况表');
      XLSX.writeFile(wb,"经营情况表.xlsx")
      alert("导出Excel成功");
    },
  } 

  };
</script>

<style scoped>
</style>
