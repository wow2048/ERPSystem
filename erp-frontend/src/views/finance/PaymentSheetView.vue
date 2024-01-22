<template>
  <Layout>

    <Title title="制定付款单"></Title>
    <el-button type="primary" size="medium" @click="addSheet">制定付款单</el-button>

    <div class="body">

      <el-tabs v-model="activeName" :stretch="true">

        <el-tab-pane label="待审批" name="PENDING">
          <div v-if="pendingList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <payment-sheet-list :list="pendingList" :type="1" @refresh="getPayment()"/>
          </div>
        </el-tab-pane>
        
        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <payment-sheet-list :list="successList" :type="2"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>

          <div v-else>
            <payment-sheet-list :list="failureList" :type="3"/>
          </div>
        </el-tab-pane>

      </el-tabs>

    </div>

    <!-- 创建窗口 -->
    <el-dialog
      title="创建付款单"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="paymentSheet" label-width="80px" ref="paymentSheet" :rules="rules">

          <el-form-item label="客户: " prop="customerId">
            <el-select v-model="paymentSheet.customerId" placeholder="请选择客户">
              <el-option
                v-for="item in customers"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item
            v-for="(item, index) in paymentSheet.payAccountList"
            :key="index"
            :label="'付款列表' + index">
            <div>

              <el-select v-model="item.bankId" placeholder="请选择银行账户" style="width: 50%; margin-right: 5%">
                <el-option
                  v-for="item1 in accounts"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.id">
                </el-option>
              </el-select>

              <el-input v-model="item.payMoney" style="width: 40%; margin-right: 5%" placeholder="请输入付款金额"></el-input>

            </div>

            <div style="margin-top: 10px">

              <el-input v-model="item.remark" style="width: 70%; margin-right: 10%" placeholder="请填写备注"></el-input>

              <el-button type="text" size="small" @click="addProduct" v-if="index === paymentSheet.payAccountList.length - 1">添加</el-button>
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

        <el-row v-for="accountItem in item.payAccountList" :key="accountItem.bankId">
          <el-col :span="10">银行账户id: {{accountItem.bankId}}</el-col>
          <el-col :span="14">支出金额: {{accountItem.payMoney}}</el-col>
        </el-row>

      </div>

    </el-dialog>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import PaymentSheetList from "./components/PaymentSheetList"
import { getAllPaymentSheet , getAllAccount, makePaymentSheet, paymentSheetUnread} from "../../network/finance"
import { getAllCustomer } from '../../network/purchase'

export default {

  name: 'PaymentSheetView',
  components: {
    Layout,
    Title,
    PaymentSheetList
  },

  data() {
    return {
      activeName: 'PENDING',
      unreadList: [],
      paymentSheetList: [],
      pendingList: [],
      successList: [],
      failureList: [],
      dialogVisible: false,
      informationVisible: false,
      customers: [],
      accounts: [],
      paymentSheet: {
        payAccountList: [
          {
            bankId: '',
            payMoney: '',
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
    this.getPayment()
    this.dialogVisible = false
    this.customers = []
    paymentSheetUnread({ params : { operator: sessionStorage.getItem("name")} }).then(_res => {
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
    getPayment() {
      getAllPaymentSheet({}).then(_res => {
        this.paymentSheetList = _res.result
        this.pendingList = this.paymentSheetList.filter(item => item.state === '待审批')
        this.successList = this.paymentSheetList.filter(item => item.state === '审批完成')
        this.failureList = this.paymentSheetList.filter(item => item.state === '审批失败')
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
          this.paymentSheet.id = null
          this.paymentSheet.operator = sessionStorage.getItem("name")
          this.paymentSheet.totalAmount = null
          this.paymentSheet.state = null
          this.paymentSheet.payAccountList.forEach((item) => {
            item.id = null
            item.payMoney = parseInt(item.payMoney)
          })
          makePaymentSheet(this.paymentSheet).then(_res => {
            if (_res.code === "E0002") {
            this.$message({
              type: 'error',
              message: _res.msg
            });
          }
            if (_res.msg === 'Success') {
              this.$message.success('创建成功!')
              this.reset()
              this.dialogVisible = false
              this.getPayment()
            }
          })
    },

    reset() {
      this.paymentSheet = {
        payAccountList: [
          {
            bankId: '',
            payMoney: '',
            remark: '',
          }
        ]
      }
    },

    addProduct() {
      this.paymentSheet.payAccountList.push({});
    },
    removeProduct(item) {
      var index = this.paymentSheet.payAccountList.indexOf(item)
      if (index !== -1) {
        this.paymentSheet.payAccountList.splice(index, 1)
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