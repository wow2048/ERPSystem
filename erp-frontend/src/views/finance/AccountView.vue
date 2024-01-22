<template>
  <Layout>
    <Title title="账户管理"></Title>
    <!-- 搜索栏 -->
    <div>
        <el-input v-model="searchInfo" placeholder="请输入账户名称关键字进行查询">
            <el-button slot="append" @click="handleSearch" icon="el-icon-search"></el-button>
        </el-input>
    </div>

    <!-- 搜索结果，账户列表 -->
    <div style="margin-top: 15px">
        <el-table 
          :data="accountList" 
          stripe 
          style="width: 100%" 
          :header-cell-style="{'text-align':'center'}" 
          :cell-style="{'text-align':'center'}">
            <el-table-column 
              prop="id"
              label="id">
            </el-table-column>
            <el-table-column 
              prop="name"
              label="账户名称">
            </el-table-column>
            <el-table-column 
              prop="amount"
              label="余额/元">
            </el-table-column>
            <el-table-column 
              label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="editAccount(scope.row)">
                  编辑
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.row.id)">
                  删除
                </el-button>
              </template>
            </el-table-column>
        </el-table>
    </div>

    <!-- 增加按钮 -->
    <div style="margin-top: 15px">
        <el-button type="primary" size="medium" @click="addAccount">新增账户</el-button>
    </div>

    <!-- 新增账户 -->
    <el-dialog
      title="新增账户"
      :visible.sync="addDialogVisible"
      width="40%"
      @close="reload()">
        <el-form :model="addInfo" :label-width="'100px'" size="mini" ref="addInfo" :rules="rules">
            <el-form-item label="账户名称" prop="name">
                <el-input v-model="addInfo.name" placeholder="请输入账户名称"></el-input>
            </el-form-item>
            <el-form-item label="余额/元" prop="amount">
                <el-input v-model.number="addInfo.amount" type="number" placeholder="请输入账户余额"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="handleAdd(false, 'addInfo')">取 消</el-button>
        <el-button type="primary" @click="handleAdd(true, 'addInfo')">确 定</el-button>
        </span>
    </el-dialog>    

    <!-- 编辑账户 -->
    <el-dialog
      title="编辑账户"
      :visible.sync="editDialogVisible"
      width="40%">
        <el-form :model="editInfo" :label-width="'100px'" size="mini" ref="editInfo" :rules="rules">
            <el-form-item label="账户id">
                <span> {{ editInfo.id }} </span>
            </el-form-item>
            <el-form-item label="账户名称" prop="name">
                <el-input v-model="editInfo.name" placeholder="请输入账户名称"></el-input>
            </el-form-item>
            <el-form-item label="余额/元">
                <span> {{ editInfo.amount }} </span>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false, 'editInfo')">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true, 'editInfo')">确 定</el-button>
        </span>
    </el-dialog>    

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { searchAccount,
         createAccount,
         updateAccount,
         deleteAccount,
} from "../../network/finance";
export default {
  name: 'AccountView',
  components: {
    Layout,
    Title
  },
  data() {
    return {

        accountList: [],

        // 搜索功能相关数据
        searchInfo: '',

        // 添加功能相关数据
        addDialogVisible: false,
        addInfo: {},

        // 编辑功能相关数据
        editDialogVisible: false,
        editInfo: {},

        // 删除功能相关数据
        deleteDialogVisible: false,

        // 验证规则
        rules: {
        name: [
          { required: true, message: '账户名称不能为空', trigger: 'blur' },
        ],
        amount: [
          { type: "number", required: true, message: '账户余额不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '账户余额需要为正数', trigger: 'blur' }
        ],
      },

    }
  },

  async mounted() {
    searchAccount({ params : { keys: this.searchInfo } }).then(_res => {
      this.accountList = _res.result;
    })
  },

  methods: {
    reload(){
      this.searchInfo = '';
      searchAccount({ params : { keys: this.searchInfo } }).then(_res => {
      this.accountList = _res.result;
    })
    },

    // 处理搜索功能
    handleSearch(){
        let config = {
          params: {
            keys: this.searchInfo
          }
        }
        searchAccount(config).then(_res => {
          this.accountList = _res.result;
        })
    },

    // 处理添加功能
    addAccount(){
        this.addDialogVisible = true;
    },
    handleAdd(type, formName) {
      if (type === false) {
        this.addDialogVisible = false;
        this.addInfo = {};
      } else if (type === true) {
        this.$refs[formName].validate((valid) => {
        if (valid) {
          createAccount(this.addInfo).then(_res => {
          this.$message({
            type: 'success',
            message: '新增成功!'
          });
          this.addInfo = {};
          this.addDialogVisible = false;
          })
        }
        })
      }
    },

    // 处理编辑功能
    editAccount(row){
      this.editDialogVisible = true;
      this.editInfo = row;
    },
    handleEdit(type, formName){
      if (type === false) {
        this.editInfo = {};
        this.editDialogVisible = false;
        searchAccount({ params : { keys: this.searchInfo } }).then(_res => {
          this.accountList = _res.result;
      })
      } else if (type === true) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            updateAccount(this.editInfo).then(_res => {
            this.$message({
              type: 'success',
              message: '修改成功！'
            })
            this.editInfo = {};
            this.editDialogVisible = false;
            })
          }
        })
      }
    },

    // 处理删除功能
    handleDelete(id){
        let config = {
        params: {
          id: id
        }
      };
      this.$confirm('是否要删除该账户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAccount(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          }
          searchAccount({ params : { keys: this.searchInfo } }).then(_res => {
            this.accountList = _res.result;
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style>
</style>