<template>
  <layout>
    <Title title="薪酬规则"></Title>
    <div style="margin-top: 10px">
      <el-table
          :data="staffList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">

        <el-table-column
            prop="id"
            label="id"
            width="150">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="150">
        </el-table-column>
        <el-table-column
            prop="type"
            label="工作岗位"
            width="100"
            :filters="[{ text: '库存管理人员', value: '库存管理人员'}, { text: '进货销售人员', value: '进货销售人员'}, {text: '销售经理',value:'销售经理'},{text:'财务人员',value:'财务人员'},{text:'人力资源人员',value:'人力资源人员'},{text:'总经理',value:'总经理'}]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag

                disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="moneyCalculate"
            label="薪酬规则"
            width="150">
        </el-table-column>
        <el-table-column
            prop="monthSalary"
            label="月薪"
            width="150">
        </el-table-column>
        <el-table-column
            prop="yearSalary"
            label="年薪"
            width="150">
        </el-table-column>
        <el-table-column
            prop="commission"
            label="提成"
            width="150">
        </el-table-column>

      </el-table>
    </div>
  </layout>
</template>

<script>
import Title from "@/components/content/Title";
import Layout from "@/components/content/Layout";
import {
  getAllStaffById,
  findStrategyByPosition,
  findBaseSalaryByPosition,
  getCommission,

} from "@/network/staff";
export default {
  components: {Title, Layout},
  name: "StaffSalaryStrategy",

  data(){
    return{
      staffList: [],
    }
  },
  async mounted(){
    await getAllStaffById().then(_res=>{
      this.staffList = this.staffList.concat(_res.result)
    })
    for(let i = 0;i<this.staffList.length;i++){
      let config = {
        params: {
          position: this.staffList[i].type
        }
      };
      findStrategyByPosition(config).then(_res=>{
        this.staffList[i].moneyCalculate = _res.result;
      })

      findBaseSalaryByPosition(config).then(_res=>{
          this.staffList[i].monthSalary = _res.result;
          this.staffList[i].yearSalary = 0;
          if(this.staffList[i].type == '总经理'){
            this.staffList[i].yearSalary = _res.result;
            this.staffList[i].monthSalary = 0;
          }

      })

      let config2 = {
        params: {
          name: this.staffList[i].name
        }
      };
       getCommission(config2).then(res=>{
         this.staffList[i].commission = res.result;
       })
      this.$set(this.staffList,i,this.staffList[i])
    }

  },



  methods: {
    filterTag(value, row) {
      return row.type === value
    },

  }

}
</script>

<style scoped>

</style>