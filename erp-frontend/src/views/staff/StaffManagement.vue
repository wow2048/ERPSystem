<template>
  <layout>
    <Title title="员工管理"></Title>
    <el-button type="primary" size="medium" @click="addStaff">新增员工</el-button>
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
            width="60">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="100">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            width="50">
        </el-table-column>
        <el-table-column
            prop="birth"
            label="出生日期"
            width="150"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="手机"
            width="150">
        </el-table-column>
        <el-table-column
            prop="type"
            label="工作岗位"
            width="100"
            :filters="[{ text: '库存管理人员', value: '库存管理人员'}, { text: '进货销售人员', value: '进货销售人员'}, {text: '销售经理',value:'销售经理'},{text:'财务人员',value:'财务人员'},{text:'人力资源人员',value:'人力资源人员'},{text:'销售经理',value:'销售经理'}]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag

                disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
            prop="salary"
            label="基本工资"
            width="100">
        </el-table-column>
        <el-table-column
            prop="jobSalary"
            label="岗位工资"
            width="100">
        </el-table-column>
        <el-table-column
            prop="jobLevel"
            label="岗位级别"
            width="100">
        </el-table-column>
        <el-table-column
            prop="moneyCalculate"
            label="薪资计算方式"
            width="150">
        </el-table-column>
        <el-table-column
            prop="moneyMethod"
            label="薪资发放方式"
            width="130">
        </el-table-column>
        <el-table-column
            prop="account"
            label="工资卡账户"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="editStaff(scope.row.id)"
                type="text"
                size="small">
              编辑
            </el-button>
            <!-- 删除todo -->
            <el-button
                @click="deleteStaff(scope.row.id)"
                type="text"
                size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 新增员工todo -->
    <el-dialog
        title="新增员工"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="close()">
      <el-form :model="addInfo" :label-width="'100px'" size="mini">
        <el-form-item label="id">
          <el-input v-model="addInfo.id" placeholder="请输入员工的id,不填写时为默认提供的新id"></el-input>
        </el-form-item>
        <el-form-item label="工作岗位">
          <el-select v-model="addInfo.type">
            <el-option
                v-for="item in typeList"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="岗位级别">
          <el-select v-model="addInfo.jobLevel">
            <el-option
                v-for="item in 5"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="addInfo.name" placeholder="请输入员工的姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="addInfo.phone" placeholder="请输入员工的电话号码"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="addInfo.gender" placeholder="请选择员工的性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-input v-model="addInfo.birth" placeholder="请输入员工的出生日期"></el-input>
        </el-form-item>
        <el-form-item label="基本工资">
          <el-input v-model="addInfo.salary" placeholder="请输入员工的基本工资"></el-input>
        </el-form-item>
        <el-form-item label="薪资发放方式">
          <el-select v-model="addInfo.moneyMethod" placeholder="请选择员工的薪资发放方式">
            <el-option label="发放现金" value="发放现金"></el-option>
            <el-option label="发放到工资卡" value="发放到工资卡"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账户">
          <el-input v-model="addInfo.account" placeholder="请输入员工的工资卡账户"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleAdd(false)">取 消</el-button>
        <el-button type="primary" @click="handleAdd(true)">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改员工 -->
    <el-dialog
        title="修改员工信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="close()">
      <el-form :model="editInfo" :label-width="'100px'" size="mini">
        <el-form-item label="工作岗位">
          <el-select v-model="editInfo.type">
            <el-option
                v-for="item in typeList"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="岗位级别">
          <el-select v-model="editInfo.jobLevel">
            <el-option
                v-for="item in 5"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="editInfo.name" placeholder="请输入员工的姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="editInfo.phone" placeholder="请输入员工的电话号码"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="editInfo.gender" placeholder="请选择员工的性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-input v-model="editInfo.birth" placeholder="请输入员工的出生日期" ></el-input>
        </el-form-item>
        <el-form-item label="基本工资">
          <el-input v-model="editInfo.salary" placeholder="请输入员工的基本工资"></el-input>
        </el-form-item>
        <el-form-item label="薪资发放方式">
          <el-select v-model="editInfo.moneyMethod" placeholder="请选择员工的薪资发放方式">
            <el-option label="发放现金" value="发放现金"></el-option>
            <el-option label="发放到工资卡" value="发放到工资卡"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账户">
          <el-input v-model="editInfo.account" placeholder="请输入员工的工资卡账户"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>
  </layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {
  createStaff,
  updateStaff,
  deleteById,
  getAllStaffById,
  deleteCheckInById,
  findStrategyByPosition,
  findBaseSalaryByPosition
} from "@/network/staff";
import {
  register,
} from "@/network/auth";
export default {
  name: "StaffManagement",
  components: {Title, Layout},
  data() {
    return {
      isGM: false,
      staffList: [],
      typeList: ['库存管理人员', '进货销售人员','销售经理','财务人员','人力资源人员','总经理'],
      methodList: ['月薪制','基本工资+提成','年薪制'],
      addDialogVisible: false,
      addInfo: {
        id: '',
        type: '',
        jobLevel: '',
        name: '',
        phone: '',
        gender: '',
        birth: '',
        salary: '',
        jobSalary: '',
        moneyMethod: '',
        moneyCalculate: '',
        account: '',
      },
      jobTypeMap:{
        '库存管理人员':'INVENTORY_MANAGER',
        '进货销售人员':'SALE_STAFF',
        '销售经理':'SALE_MANAGER',
        '财务人员':'FINANCE_STAFF',
        '人力资源人员':'HR',
        '总经理':'GM',

      },
      editDialogVisible: false,
      editInfo: {},
    }
  },
  async mounted() {
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
        this.staffList[i].jobSalary = _res.result;
      })
    }

  },
  methods: {
    formatDate(row,column){
      let data = row[column.property]
      if(data == null){
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate();
    },
    async reload(){
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
          this.staffList[i].jobSalary = _res.result;
        })
      }
    },
    filterTag(value, row) {
      return row.type === value
    },
    handleAdd(type) {
      if (type === false) {
        console.log('right')
        this.addDialogVisible = false;
        this.addInfo = {};
      } else if (type === true) {
        let user = {}
        user.name = this.addInfo.name;
        user.role = this.jobTypeMap[this.addInfo.type];
        user.password = '123456';
        createStaff(this.addInfo).then(_res => {
          if (_res.code === "D0000" || _res.code === "D0001") {
            this.$message({
              type: 'error',
              message: _res.msg
            });
          } else {
            console.log('right');
            this.$message({
              type: 'success',
              message: '新增成功!'
            });
            this.addInfo = {};
            this.addDialogVisible = false;
          }
        })
        register(user).then(_res=>{
        })
      }
    },
    handleEdit(type) {
      if (type === false) {
        this.editInfo = {};
        this.editDialogVisible = false;
      } else if (type === true) {
        updateStaff(this.editInfo).then(_res => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.editInfo = {};
          this.editDialogVisible = false;
        })
      }
    },
    addStaff() {
      // TODO: 新增员工
      this.addDialogVisible = true;
    },
    editStaff(id) {
      // TODO: 修改员工信息
      this.editInfo = this.staffList.filter(x => x.id === id)[0];
      let dt = new Date(this.editInfo.birth);
      this.editInfo.birth = dt.getFullYear()+'-'+(dt.getMonth()+1)+'-'+dt.getDate();
      this.editDialogVisible = true;
    },
    deleteStaff(id){
      let config = {
        params: {
          id: id
        }
      };
      this.$confirm('是否要删除该员工？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          }
          this.staffList = [];
          this.reload();
        })
        deleteCheckInById(config).then(_res=>{

        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
        this.staffList = [];
        this.reload();
      })
    },
    close() {
      this.addInfo = {};
      this.editInfo = {};
      this.staffList = [];
      this.reload();
    }
  }
}
</script>

<style>

</style>