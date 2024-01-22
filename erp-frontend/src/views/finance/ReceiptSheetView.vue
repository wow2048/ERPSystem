<template>
  <Layout>

    <Title title="制定收款单"></Title>
    <el-button type="primary" size="medium" @click="addSheet">制定收款单</el-button>

    <div class="body">

      <el-tabs v-model="activeName" :stretch="true">

        <el-tab-pane label="待审批" name="PENDING">
          <div v-if="pendingList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <receipt-sheet-list :list="pendingList" :type="1" @refresh="getReceipt()"/>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <receipt-sheet-list :list="successList" :type="2"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <receipt-sheet-list :list="failureList" :type="3"/>
          </div>
        </el-tab-pane>

      </el-tabs>

    </div>
    <!-- 创建窗口 -->
    <el-dialog
      title="创建收款单"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="receiptSheet" label-width="80px" ref="receiptSheet" :rules="rules">

          <el-form-item label="客户: " prop="customerId">
            <el-select v-model="receiptSheet.customerId" placeholder="请选择客户">
              <el-option
                v-for="item in customers"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item
            v-for="(item, index) in receiptSheet.receiveAccountList"
            :key="index"
            :label="'收款列表' + index">
            <div>

              <el-select v-model="item.bankId" placeholder="请选择银行账户" style="width: 50%; margin-right: 5%">
                <el-option
                  v-for="item1 in accounts"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.id">
                </el-option>
              </el-select>

              <el-input v-model="item.receiveMoney" style="width: 40%; margin-right: 5%" placeholder="请输入收款金额"></el-input>

            </div>

            <div style="margin-top: 10px">

              <el-input v-model="item.remark" style="width: 70%; margin-right: 10%" placeholder="请填写备注"></el-input>

              <el-button type="text" size="small" @click="addProduct" v-if="index === receiptSheet.receiveAccountList.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProduct(item)" v-if="index !== 0">删除</el-button>

            </div>

          </el-form-item>

        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit()">立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="通知"
      :visible.sync="informationVisible"
      width="40%"
      :before-close="handleClose">
      <div v-for="item in unreadList" :key="item.id" style="margin-bottom: 35px; margin-top: 17rpx; font-size: large;">

        <el-row style="margin-bottom: 5px">
          <span ><strong>id: </strong>{{item.id}}的单据审核通过</span>
        </el-row>

        <el-row v-for="accountItem in item.receiveAccountList" :key="accountItem.bankId">
          <el-col :span="10">银行账户id: {{accountItem.bankId}}</el-col>
          <el-col :span="14">收入金额: {{accountItem.receiveMoney}}</el-col>
        </el-row>

      </div>

    </el-dialog>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import ReceiptSheetList from "./components/ReceiptSheetList"
import { getAllReceiptSheet , getAllAccount, makeReceiptSheet, receiptSheetUnread} from "../../network/finance"
import { getAllCustomer } from '../../network/purchase'

export default {

  name: 'ReceiptSheetView',
  components: {
    Layout,
    Title,
    ReceiptSheetList
  },

  data() {
    return {
      activeName: 'PENDING',
      unreadList: [],
      receiptSheetList: [],
      pendingList: [],
      successList: [],
      failureList: [],
      dialogVisible: false,
      informationVisible: false,
      customers: [],
      accounts: [],
      receiptSheet: {
        receiveAccountList: [
          {
            bankId: '',
            receiveMoney: '',
            remark: '',
          }
        ]
      },
      rules: {
        customerId: [
          { required: true, message: '请选择一个客户', trigger: 'change' }
        ],
      },
    }
  },

  mounted() {
    this.getReceipt()
    this.dialogVisible = false
    this.customers = []
    receiptSheetUnread({ params : { operator: sessionStorage.getItem("name")} }).then(_res => {
      this.unreadList = _res.result
      if (_res.result.length != 0) {
        this.informationVisible = true
      }
    })
    getAllCustomer({ params : { type: 'SUPPLIER' } }).then(_res => {
      this.customers = this.customers.concat(_res.result)
    })
    getAllCustomer({ params : { type: 'SELLER' } }).then(_res => {
      this.customers = this.customers.concat(_res.result)
    })
    getAllAccount({}).then(_res => {
      this.accounts = _res.result
    })
  },

  methods: {
    getReceipt() {
      getAllReceiptSheet({}).then(_res => {
        this.receiptSheetList = _res.result
        this.pendingList = this.receiptSheetList.filter(item => item.state === '待审批')
        this.successList = this.receiptSheetList.filter(item => item.state === '审批完成')
        this.failureList = this.receiptSheetList.filter(item => item.state === '审批失败')
      })
    },

     addSheet(){
        this.dialogVisible = true
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.reset()
          done();
        })
        .catch(_ => {});
    },

    submit() {
          this.receiptSheet.id = null
          this.receiptSheet.operator = sessionStorage.getItem("name")
          this.receiptSheet.totalAmount = null
          this.receiptSheet.state = null
          this.receiptSheet.receiveAccountList.forEach((item) => {
            item.id = null
            item.receiveMoney = parseInt(item.receiveMoney)
          })
          makeReceiptSheet(this.receiptSheet).then(_res => {
            if (_res.code === "E0001") {
            this.$message({
              type: 'error',
              message: _res.msg
            });
          }
            if (_res.msg === 'Success') {
              this.$message.success('创建成功!')
              this.reset()
              this.dialogVisible = false
              this.getReceipt()
            }
          })
    },

    reset() {
      this.receiptSheet = {
        receiveAccountList: [
          {
            bankId: '',
            receiveMoney: '',
            remark: '',
          }
        ]
      }
    },

    addProduct() {
      this.receiptSheet.receiveAccountList.push({});
    },
    removeProduct(item) {
      var index = this.receiptSheet.receiveAccountList.indexOf(item)
      if (index !== -1) {
        this.receiptSheet.receiveAccountList.splice(index, 1)
      }
    }

  }
}
</script>

<style lang="scss" scoped>
.body {
  margin: 0 auto;
  margin-top: 10px;
  height: 80vh;
  overflow-y: auto;
  width: 100%;
  background: rgba($color: #fff, $alpha: 0.5);
}
</style>