<template>
  <Layout>
    <Title title="客户管理"></Title>
    <el-button type="primary" size="medium" @click="addCustomer">新增客户</el-button>
    <div style="margin-top: 10px">
      <el-table
        :data="customerList"
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
          width="70">
        </el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          width="100"
          :filters="[{ text: '供应商', value: '供应商' }, { text: '销售商', value: '销售商' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.type === '供应商' ? 'primary' : 'success'"
              disable-transitions>{{scope.row.type}}</el-tag>
          </template>
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
          width="150">
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
          width="120">
        </el-table-column>
        <el-table-column
          prop="payable"
          label="应付(元)"
          width="120">
        </el-table-column>
        <el-table-column
          prop="operator"
          label="操作员"
          width="120">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editCustomer(scope.row.id)"
              type="text"
              size="small">
              编辑
            </el-button>
            <!-- 删除todo -->
            <el-button
              @click="deleteCustomer(scope.row.id)"
              type="text"
              size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 新增客户todo -->
      <el-dialog
      title="新增客户"
      :visible.sync="addDialogVisible"
      width="40%"
      @close="close()">
      <el-form :model="addInfo" :label-width="'100px'" size="mini">
        <el-form-item label="id">
          <el-input v-model="addInfo.id" placeholder="请输入客户的id,不填写时为默认提供的新id"></el-input>
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="addInfo.type">
            <el-option
              v-for="item in typeList"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="级别">
          <el-select v-model="addInfo.level">
            <el-option
              v-for="item in 5"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="addInfo.name" placeholder="请输入客户的姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="addInfo.phone" placeholder="请输入客户的电话号码"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="addInfo.address" placeholder="请输入客户的地址"></el-input>
        </el-form-item>
        <el-form-item label="邮编">
          <el-input v-model="addInfo.zipcode" placeholder="请输入客户的邮编"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="addInfo.email" placeholder="请输入客户的电子邮箱"></el-input>
        </el-form-item>
        <el-form-item label="应收额度">
          <el-input v-model="addInfo.lineOfCredit" placeholder="请输入客户的应收额度"></el-input>
        </el-form-item>
        <el-form-item label="应收">
          <el-input v-model="addInfo.receivable" placeholder="请输入公司应收取客户的金额"></el-input>
        </el-form-item>
        <el-form-item label="应付">
          <el-input v-model="addInfo.payable" placeholder="请输入公司应付给客户的金额"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleAdd(false)">取 消</el-button>
        <el-button type="primary" @click="handleAdd(true)">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改客户信息"
      :visible.sync="editDialogVisible"
      width="40%"
      @close="close()">
      <el-form :model="editInfo" :label-width="'100px'" size="mini">
        <el-form-item label="分类">
          <el-select v-model="editInfo.type">
            <el-option
              v-for="item in typeList"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="级别">
          <el-select v-model="editInfo.level">
            <el-option
              v-for="item in 5"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="editInfo.name" placeholder="请输入客户的姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="editInfo.phone" placeholder="请输入客户的电话号码"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="editInfo.address" placeholder="请输入客户的地址"></el-input>
        </el-form-item>
        <el-form-item label="邮编">
          <el-input v-model="editInfo.zipcode" placeholder="请输入客户的邮编"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="editInfo.email" placeholder="请输入客户的电子邮箱"></el-input>
        </el-form-item>
        <el-form-item label="应收额度" v-if="isGM">
          <el-input v-model="editInfo.lineOfCredit" placeholder="请输入客户的应收额度"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { getAllCustomer,
         createCustomer,
         updateCustomer,
         deleteById,
} from "../../network/purchase";
export default {
  name: 'CustomerView',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      isGM: false,
      customerList: [],
      typeList: ['供应商', '销售商'],
      addDialogVisible: false,
        addInfo: {
          id: '',
          type: '',
          level: '',
          name: '',
          phone: '',
          address: '',
          zipcode: '',
          email: '',
          lineOfCredit: '',
          receivable: '',
          payable: '',
          operator: '',
        },
        editDialogVisible: false,
        editInfo: {},
    }
  },
  async mounted() {
    this.isGM = (sessionStorage.getItem("role") === "GM")
    await getAllCustomer({ params : { type: 'SUPPLIER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    await getAllCustomer({ params : { type: 'SELLER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
  },
  methods: {
    async reload(){
      await getAllCustomer({ params : { type: 'SUPPLIER' } }).then(_res => {
        this.customerList = this.customerList.concat(_res.result)
      })
      await getAllCustomer({ params : { type: 'SELLER' } }).then(_res => {
        this.customerList = this.customerList.concat(_res.result)
      })
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
        this.addInfo.operator = sessionStorage.getItem("name");
        createCustomer(this.addInfo).then(_res => {
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
      }
    },
    handleEdit(type) {
      if (type === false) {
        this.editInfo = {};
        this.editDialogVisible = false;
      } else if (type === true) {
        updateCustomer(this.editInfo).then(_res => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.editInfo = {};
          this.editDialogVisible = false;
        })
      }
    },      
    addCustomer() {
      // TODO: 新增客户
      this.addDialogVisible = true;
    },
    editCustomer(id) {
      // TODO: 修改客户信息
      this.editInfo = this.customerList.filter(x => x.id === id)[0];
      this.editDialogVisible = true;
    },
    deleteCustomer(id){
      let config = {
        params: {
          id: id
        }
      };
      this.$confirm('是否要删除该客户？', '提示', {
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
          this.customerList = [];
          this.reload();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
        this.customerList = [];
        this.reload();
      })
    },
    close() {
        this.addInfo = {};
        this.editInfo = {};
        this.customerList = [];
        this.reload();
      }
  }
}
</script>

<style>

</style>