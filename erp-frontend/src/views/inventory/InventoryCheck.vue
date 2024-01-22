<template>
  <Layout>
    <Title title="库存盘点"></Title>
    盘点当天的库存快照，包括当天的各种商品的名称，型号，库存数量，库存均价，批次，批号，出厂日期。
    <el-button type="primary" size="medium" @click="exportAsExcel">导出Excel</el-button>
    <div class="table-body">
      <el-table
        :data="cur_list"
        style="width: 100%;"
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
      >
        <el-table-column fixed type="index" label="行号" min-width="12%">
        </el-table-column>
        <el-table-column label="商品编号" min-width="13%">
          <template slot-scope="scope">
            <el-button type="text" @click="showProduct(scope.row)">{{ scope.row.product.id }}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="quantity" label="数量" min-width="12%">
        </el-table-column>
        <el-table-column prop="batchId" label="批次" min-width="12%">
        </el-table-column>
        <el-table-column prop="purchasePrice" label="价格" min-width="12%">
        </el-table-column>
        <el-table-column prop="productionDate" label="出厂日期" min-width="12%">
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="商品详细信息"
      :visible.sync="dialogVisible"
      width="80%"
      :before-close="handleClose">
      <div>
        <el-table
          :data="cur_product"
          style="width: 100%;"
          :header-cell-style="{'text-align': 'center'}"
          :cell-style="{'text-align': 'center'}">
          <el-table-column
            fixed
            prop="id"
            label="商品编号"
            min-width="15%">
          </el-table-column>
          <el-table-column
            prop="name"
            label="商品名称"
            min-width="10%">
          </el-table-column>
          <el-table-column
            prop="type"
            label="商品型号"
            min-width="15%">
          </el-table-column>
          <el-table-column
            prop="quantity"
            label="库存数量"
            min-width="8%">
          </el-table-column>
          <el-table-column
            prop="purchasePrice"
            label="进价"
            min-width="8%">
          </el-table-column>
          <el-table-column
            prop="retailPrice"
            label="零售价"
            min-width="8%">
          </el-table-column>
          <el-table-column
            prop="recentPp"
            label="最近进价"
            min-width="8%">
          </el-table-column>
          <el-table-column
            prop="recentRp"
            label="最近零售价"
            min-width="8%">
          </el-table-column>
        </el-table>
      </div>

    </el-dialog>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { getWarehouseCounting } from "@/network/warehouse";
import { formatDate } from "@/common/utils";
import * as XLSX from 'xlsx';


export default {
  components: {
    Layout,
    Title
  },
  data(){
    return {
      cur_list: [],
      cur_row: {},
      dialogVisible: false,
    }
  },
  computed: {
    cur_product() {
      let temp = [];
      temp.push(this.cur_row.product);
      return temp;
    }
  },
  mounted() {
    getWarehouseCounting().then(_res => {
      this.cur_list = _res.result;
      for(let i = 0; i < this.cur_list.length; i++) {
        if(this.cur_list[i].productionDate == null) this.cur_list[i].productionDate = ''
        else this.cur_list[i].productionDate = formatDate(this.cur_list[i].productionDate)
      }
      this.cur_row = this.cur_list[0];
    })

  },
  methods: {
    exportAsExcel() {
      let a = this.cur_list;
      let s = a.length;
      let arr = [];
      for(let i = 0;i<s;i++){
        let obj = {};
        obj['商品编号'] = a[i].product.id;
        obj['商品名称'] = a[i].product.name;
        //obj.categoryId = a[i].product.categoryId;
        //obj.purchasePrice = a[i].product.purchasePrice;
        //obj.quantity = a[i].product.quantity;
        //obj.recentPp = a[i].product.recentPp;
        //obj.recentRp = a[i].product.recentRp;
        //obj.retailPrice = a[i].product.retailPrice;
        //obj.type = a[i].product.type;
        obj['商品数量'] = a[i].quantity;
        obj['出厂日期'] = a[i].productionDate;
        obj['商品批次'] = a[i].batchId;
        obj['价格'] = a[i].purchasePrice;
        arr[i] = obj;
      }

      let jsonText = Array.from(arr);
      const data = XLSX.utils.json_to_sheet(jsonText);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb,data,'库存盘点');
      XLSX.writeFile(wb,"库存盘点.xlsx")
      alert("导出Excel成功");
    },
    showProduct(row) {
      this.cur_row = row;
      this.dialogVisible = true;
    },
    handleClose(done) {
      done();
    }
  }
};
</script>

<style scoped>

.table-body {
  width: 80%;
  margin: 20px auto;
}
</style>
