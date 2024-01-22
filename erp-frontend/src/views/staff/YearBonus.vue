<template>
  <Layout>
    <Title title="制定年终奖"></Title>

    <!-- 员工列表 -->
    <div style="margin-top: 15px">
        <el-table 
          :data="yearBonusList" 
          stripe 
          style="width: 100%" 
          :header-cell-style="{'text-align':'center'}" 
          :cell-style="{'text-align':'center'}">
            <el-table-column 
              prop="employeeID"
              label="employeeID">
            </el-table-column>
            <el-table-column 
              prop="name"
              label="员工名称">
            </el-table-column>
            <el-table-column 
              prop="totalSalaryOfElevenMonths"
              label="前11个月工资总和/元">
            </el-table-column>
            <el-table-column 
              prop="bonus"
              label="年终奖/元">
            </el-table-column>
            <el-table-column 
              label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="primary"
                  @click="addBonus(scope.row)">
                  添加年终奖
                </el-button>
              </template>
            </el-table-column>
        </el-table>
    </div>

    <!-- 编辑年终奖 -->
    <el-dialog
      title="添加年终奖"
      :visible.sync="addDialogVisible"
      width="40%"
      @close="reload()">
        <el-form :model="addInfo" :label-width="'100px'" size="mini" :rules="rules" ref="addInfo">
            <el-form-item label="年终奖金额/元" prop="bonus">
                <el-input v-model.number="addInfo.bonus" type="number" placeholder="请输入年终奖"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="handleAdd(false, 'addInfo')">取 消</el-button>
        <el-button type="primary" @click="handleAdd(true, 'addInfo')">确 定</el-button>
        </span>
    </el-dialog>      

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { getBonus,
         addBonus,
} from "../../network/staff";
export default {
  name: 'YearBonus',
  components: {
    Layout,
    Title
  },
  data() {
    return {

        yearBonusList: [],

        // 添加功能相关数据
        addDialogVisible: false,
        addInfo: {},

        // 验证规则
        rules: {
        bonus: [
          { type: "number", required: true, message: '年终奖金额不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '年终奖金额不能为负数', trigger: 'blur' }
        ],
      },
    }
  },

  async mounted() {
    getBonus().then(_res => {
      this.yearBonusList = _res.result;
    })
  },

  methods: {
    reload(){
      getBonus().then(_res => {
      this.yearBonusList = _res.result;
    })
    },

    // 处理添加功能
    addBonus(row){
      this.addDialogVisible = true;
      this.addInfo = row;
    },
    handleAdd(type, formName){
      if (type === false) {
        this.addInfo = {};
        this.addDialogVisible = false;
        getBonus().then(_res => {
        this.yearBonusList = _res.result;
      })
      } else if (type === true) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            addBonus(this.addInfo).then(_res => {
            this.$message({
              type: 'success',
              message: '修改成功！'
            })
            this.addInfo = {};
            this.addDialogVisible = false;
            })
          }
        })
      }
    },
  }
}
</script>

<style>
</style>