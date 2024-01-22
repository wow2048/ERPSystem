<template>
  <Layout>
    <Title title="制定销售策略"></Title>
    <!-- 展示各种不同的策略 -->
    <div class="body">
      <el-tabs v-model="activeName" :stretch="true">
      <!-- 客户级别销售策略列表 -->
        <el-tab-pane label="针对客户级别的销售策略" name="SALE_STRATEGY_CUSTOMER">
          <div style="margin-left: 20px">
            <el-button type="primary" size="medium" @click="addSaleStrategyCustomer">新增策略</el-button>
          </div>
          <div v-if="saleStrategyCustomerList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <el-card v-for="item in saleStrategyCustomerList" :index="item.index" :key="item.id" class="box-card">
              <div slot="header">
                <span><strong>id: </strong>{{item.id}}</span>
              </div>
              <div>
                <el-row>
                  <el-col :span="8">
                    <span><strong>用户级别: </strong>{{item.level}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span><strong>折让: </strong>{{item.discount}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span><strong>赠送代金券: </strong>{{item.voucherAmountCustomer}}（元）</span>
                  </el-col>
                </el-row>

                <div class="customerTable">
                  <el-row>
                  <span><strong>赠品: </strong></span>
                </el-row>
                <el-table
                  :data="item.giftsCustomer"
                  stripe
                  border
                  style="{
                    width: 80%,
                  }"
                  :header-cell-style="{'text-align':'center'}"
                  :cell-style="{'text-align':'center'}">
                  <el-table-column
                    prop="pid"
                    label="商品id"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="商品名称"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="quantity"
                    label="数量"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="unitPrice"
                    label="单价(元)"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="subTotal"
                    label="金额(元)"
                    width="200">
                  </el-table-column>
                </el-table>
                </div>

                <el-row>
                  <el-col :span="12">
                    <span><strong>起始时间: </strong>{{item.beginTime}}</span>
                  </el-col>
                  <el-col :span="12">
                    <span><strong>结束时间: </strong>{{item.endTime}}</span>
                  </el-col>
                </el-row>
              </div>
            </el-card>
            
          </div>
        </el-tab-pane>
        <!-- 组合商品销售策略列表 -->
        <el-tab-pane label="组合商品销售策略" name="SALE_STRATEGY_PRODUCT">
          <div style="margin-left: 20px">
            <el-button type="primary" size="medium" @click="addSaleStrategyProduct">新增策略</el-button>
          </div>
          <div v-if="saleStrategyProductList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <el-card v-for="item in saleStrategyProductList" :index="item.index" :key="item.id" class="box-card">
              <div slot="header">
                <span><strong>id: </strong>{{item.id}}</span>
              </div>
              <div>
                <el-row>
                  <el-col :span="12">
                    <span><strong>组合商品总价: </strong>{{item.totalPrice}}（元）</span>
                  </el-col>
                  <el-col :span="12">
                    <span><strong>优惠金额: </strong>{{item.specialPrice}}（元）</span>
                  </el-col>
                </el-row>

                <div class="customerTable">
                  <el-row>
                  <span><strong>组合商品: </strong></span>
                </el-row>
                <el-table
                  :data="item.specialProducts"
                  stripe
                  border
                  style="{
                    width: 80%,
                  }"
                  :header-cell-style="{'text-align':'center'}"
                  :cell-style="{'text-align':'center'}">
                  <el-table-column
                    prop="pid"
                    label="商品id"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="商品名称"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="quantity"
                    label="数量"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="unitPrice"
                    label="单价(元)"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="subTotal"
                    label="金额(元)"
                    width="200">
                  </el-table-column>
                </el-table>
                </div>

                <el-row>
                  <el-col :span="12">
                    <span><strong>起始时间: </strong>{{item.beginTime}}</span>
                  </el-col>
                  <el-col :span="12">
                    <span><strong>结束时间: </strong>{{item.endTime}}</span>
                  </el-col>
                </el-row>
              </div>
            </el-card>
          </div>
        </el-tab-pane>
        <!-- 不同总价销售策略列表 -->
        <el-tab-pane label="不同总价的销售策略" name="SALE_STRATEGY_PRICE">
          <div style="margin-left: 20px">
            <el-button type="primary" size="medium" @click="addSaleStrategyPrice">新增策略</el-button>
          </div>
          <div v-if="saleStrategyPriceList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <el-card v-for="item in saleStrategyPriceList" :index="item.index" :key="item.id" class="box-card">
              <div slot="header">
                <span><strong>id: </strong>{{item.id}}</span>
              </div>
              <div>
                <el-row>
                  <el-col :span="8">
                    <span><strong>触发总价: </strong>{{item.triggerPrice}}（元）</span>
                  </el-col>
                  <el-col :span="8">
                    <span><strong>赠送代金券: </strong>{{item.voucherAmountPrice}}（元）</span>
                  </el-col>
                </el-row>

                <div class="customerTable">
                  <el-row>
                  <span><strong>赠品: </strong></span>
                </el-row>
                <el-table
                  :data="item.giftsPrice"
                  stripe
                  border
                  style="{
                    width: 80%,
                  }"
                  :header-cell-style="{'text-align':'center'}"
                  :cell-style="{'text-align':'center'}">
                  <el-table-column
                    prop="pid"
                    label="商品id"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="商品名称"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="quantity"
                    label="数量"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="unitPrice"
                    label="单价(元)"
                    width="200">
                  </el-table-column>
                  <el-table-column
                    prop="subTotal"
                    label="金额(元)"
                    width="200">
                  </el-table-column>
                </el-table>
                </div>

                <el-row>
                  <el-col :span="12">
                    <span><strong>起始时间: </strong>{{item.beginTime}}</span>
                  </el-col>
                  <el-col :span="12">
                    <span><strong>结束时间: </strong>{{item.endTime}}</span>
                  </el-col>
                </el-row>
              </div>
            </el-card>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 创建新的客户销售策略 -->
    <el-dialog
      title="创建销售策略"
      :visible.sync="customerDialogVisible"
      width="60%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="addInfoCustomer" label-width="120px" :rules="rules" ref="addInfoCustomer">
          <el-form-item label="销售策略类型">
            <span>{{ addInfoCustomer.saleStrategyType }}</span>
          </el-form-item>
          <el-form-item label="起始时间" prop="beginTime">
            <el-date-picker
              v-model="addInfoCustomer.beginTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="addInfoCustomer.endTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="客户级别" prop="level">
            <el-select v-model="addInfoCustomer.level">
              <el-option
                v-for="item in 5"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="折让" prop="discount">
            <el-input v-model.number ="addInfoCustomer.discount" type="number" placeholder="请输入折让"></el-input>
          </el-form-item>
          <el-form-item label="赠送代金券/元" prop="voucherAmountCustomer">
            <el-input v-model.number="addInfoCustomer.voucherAmountCustomer" type="number" placeholder="请输入赠送代金券金额"></el-input>
          </el-form-item>

          <el-form-item
            v-for="(item, index) in addInfoCustomer.giftsCustomer"
            :key="index"
            :label="'赠品' + index">
            <div>
              <el-select v-model="item.pid" placeholder="请选择商品id" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.id"
                  :value="item1.id">
                </el-option>
              </el-select>
              <el-input v-model="item.quantity" style="width: 25%; margin-right: 5%" placeholder="请输入商品数量"></el-input>
              <el-input v-model="item.unitPrice" style="width: 25%;" placeholder="请输入商品单价/元"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-select v-model="item.name" placeholder="请选择商品名称" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.name">
                </el-option>
              </el-select>
            </div>
            <div style="margin-top: 10px">
              <el-button type="text" size="small" @click="addProduct" v-if="index === addInfoCustomer.giftsCustomer.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProduct(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSaleStrategyCustomer('addInfoCustomer')">立即创建</el-button>
      </span>
    </el-dialog>    

    <!-- 创建新的组合商品销售策略 -->
    <el-dialog
      title="创建销售策略"
      :visible.sync="productDialogVisible"
      width="60%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="addInfoProduct" label-width="120px" :rules="rules" ref="addInfoProduct">
          <el-form-item label="销售策略类型">
            <span>{{ addInfoProduct.saleStrategyType }}</span>
          </el-form-item>
          <el-form-item label="起始时间" prop="beginTime">
            <el-date-picker
              v-model="addInfoProduct.beginTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="addInfoProduct.endTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="优惠金额/元" prop="specialPrice">
            <el-input v-model.number="addInfoProduct.specialPrice" type="number" placeholder="请输入优惠金额"></el-input>
          </el-form-item>

          <el-form-item
            v-for="(item, index) in addInfoProduct.specialProducts"
            :key="index"
            :label="'组合商品' + index">
            <div>
              <el-select v-model="item.pid" placeholder="请选择商品id" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.id"
                  :value="item1.id">
                </el-option>
              </el-select>
              <el-input v-model="item.quantity" style="width: 25%; margin-right: 5%" placeholder="请输入商品数量"></el-input>
              <el-input v-model="item.unitPrice" style="width: 25%;" placeholder="请输入商品单价/元"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-select v-model="item.name" placeholder="请选择商品名称" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.name">
                </el-option>
              </el-select>
            </div>
            <div style="margin-top: 10px">
              <el-button type="text" size="small" @click="addProductV2" v-if="index === addInfoProduct.specialProducts.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProductV2(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSaleStrategyProduct('addInfoProduct')">立即创建</el-button>
      </span>
    </el-dialog>    


    <!-- 创建新的不同总价销售策略 -->
    <el-dialog
      title="创建销售策略"
      :visible.sync="priceDialogVisible"
      width="60%"
      :before-close="handleClose">

      <div style="width: 90%; margin: 0 auto">
        <el-form :model="addInfoPrice" label-width="120px" :rules="rules" ref="addInfoPrice">
          <el-form-item label="销售策略类型">
            <span>{{ addInfoPrice.saleStrategyType }}</span>
          </el-form-item>
          <el-form-item label="起始时间" prop="beginTime">
            <el-date-picker
              v-model="addInfoPrice.beginTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="addInfoPrice.endTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="触发价格/元" prop="triggerPrice">
            <el-input v-model.number="addInfoPrice.triggerPrice" type="number" placeholder="请输入触发销售策略的金额"></el-input>
          </el-form-item>
          <el-form-item label="赠送代金券/元" prop="voucherAmountPrice">
            <el-input v-model.number="addInfoPrice.voucherAmountPrice" type="number" placeholder="请输入赠送代金券金额"></el-input>
          </el-form-item>
          <el-form-item
            v-for="(item, index) in addInfoPrice.giftsPrice"
            :key="index"
            :label="'赠品' + index">
            <div>
              <el-select v-model="item.pid" placeholder="请选择商品id" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.id"
                  :value="item1.id">
                </el-option>
              </el-select>
              <el-input v-model="item.quantity" style="width: 25%; margin-right: 5%" placeholder="请输入商品数量"></el-input>
              <el-input v-model="item.unitPrice" style="width: 25%;" placeholder="请输入商品单价/元"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-select v-model="item.name" placeholder="请选择商品名称" style="width: 40%; margin-right: 5%">
                <el-option
                  v-for="item1 in commodityList"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.name">
                </el-option>
              </el-select>
            </div>
            <div style="margin-top: 10px">
              <el-button type="text" size="small" @click="addProductV3" v-if="index === addInfoPrice.giftsPrice.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProductV3(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSaleStrategyPrice('addInfoPrice')">立即创建</el-button>
      </span>
    </el-dialog>    


  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { createSaleStrategy, getSaleStrategy } from '../../network/sale'
import { getAllCommodity } from '../../network/commodity'
export default {
  name: 'SaleStrategy',
  components: {
    Layout,
    Title,
  },
  data() {
    return {

      activeName: 'SALE_STRATEGY_CUSTOMER',

      saleStrategyCustomerList: [],
      saleStrategyProductList: [],
      saleStrategyPriceList: [],

      commodityList: [],

      customerDialogVisible: false,
      productDialogVisible: false,
      priceDialogVisible: false,

      // 新增销售策略相关数据
      addInfoCustomer: {
        saleStrategyType: 'Customer',
        giftsCustomer: [
          {
            pid: '',
            quantity: '',
            name: '',
            unitPrice: '',
            subTotal: '',

          }
        ],
      },

      addInfoProduct: {
        saleStrategyType: 'Product',
        specialProducts: [
          {
            pid: '',
            quantity: '',
            name: '',
            unitPrice: '',
            subTotal: '',
          }
      ] 
      },

      addInfoPrice: {
        saleStrategyType: 'Price',
        giftsPrice: [
          {
            pid: '',
            quantity: '',
            name: '',
            unitPrice: '',
            subTotal: '',

          }
        ],
      },

      // 验证规则
      rules: {
        level: [
          { required: true, message: '用户等级不能为空', trigger: 'change' },
        ],
        beginTime: [
          { required: true, message: '起始时间不能为空', trigger: 'change' },
        ],
        endTime: [
          { required: true, message: '起始时间不能为空', trigger: 'change' },
        ],
        discount: [
          { type: "number", required: true, message: '折让不能为空', trigger: 'blur' },
          { type: "number", min: 0, max: 1, message: '折让大小在0~1之间', trigger: 'blur' }
        ],
        voucherAmountCustomer:[
          { type: "number", required: true, message: '赠送代金券不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '代金券金额不能为负数', trigger: 'blur' }
        ],
        specialPrice: [
          { type: "number", required: true, message: '优惠金额不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '优惠价格不能为负数', trigger: 'blur' }
        ],
        triggerPrice: [
          { type: "number", required: true, message: '触发价格不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '触发价格不能为负数', trigger: 'blur' }
        ],
        voucherAmountPrice: [
          { type: "number", required: true, message: '赠送代金券不能为空', trigger: 'blur' },
          { type: "number", min: 0, message: '代金券金额不能为负数', trigger: 'blur' }
        ]
      },


    }
  },

  mounted() {
    getSaleStrategy({ params : { strategyType: 'Customer' } }).then(_res => {
      this.saleStrategyCustomerList = _res.result;
    })
    getSaleStrategy({ params : { strategyType: 'Product' } }).then(_res => {
      this.saleStrategyProductList = _res.result;
    })
    getSaleStrategy({ params : { strategyType: 'Price' } }).then(_res => {
      this.saleStrategyPriceList = _res.result;
    })
    getAllCommodity({}).then(_res => {
      let res = _res.result
      res.forEach(item => this.commodityList.push(item))
    })

  },

  methods: {
    addSaleStrategyCustomer(){
      this.customerDialogVisible = true;
    },

    addSaleStrategyProduct(){
      this.productDialogVisible = true;
    },

    addSaleStrategyPrice(){
      this.priceDialogVisible = true;
    },

    // 添加商品
    addProduct(){
      this.addInfoCustomer.giftsCustomer.push({});
    },

    addProductV2(){
      this.addInfoProduct.specialProducts.push({});
    },

    addProductV3(){
      this.addInfoPrice.giftsPrice.push({});
    },

    // 删除商品
    removeProduct(item) {
      var index = this.addInfoCustomer.giftsCustomer.indexOf(item)
      if (index !== -1) {
        this.addInfoCustomer.giftsCustomer.splice(index, 1)
      }
    },

    removeProductV2(item) {
      var index = this.addInfoProduct.specialProducts.indexOf(item)
      if (index !== -1) {
        this.addInfoProduct.specialProducts.splice(index, 1)
      }
    },

    removeProductV3(item) {
      var index = this.addInfoPrice.giftsPrice.indexOf(item)
      if (index !== -1) {
        this.addInfoPrice.giftsPrice.splice(index, 1)
      }
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.resetForm()
          done();
        })
        .catch(_ => {});
    },

    // 重置的方法
    resetForm() {
      this.addInfoCustomer = {
        saleStrategyType: 'Customer',
        giftsCustomer: [
          {
            pid: '',
            name: '',
            quantity: '',
            unitPrice: '',
            subTotal: ''
          }
        ]
      };
      this.addInfoProduct = {
        saleStrategyType: 'Product',
        specialProducts: [
          {
            pid: '',
            quantity: '',
            name: '',
            unitPrice: '',
            subTotal: '',
          }
      ] 
      };
      this.addInfoPrice = {
        saleStrategyType: 'Price',
        giftsPrice: [
          {
            pid: '',
            quantity: '',
            name: '',
            unitPrice: '',
            subTotal: '',

          }
        ],
      };
    },

    // 提交表单
    submitSaleStrategyCustomer(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          createSaleStrategy(this.addInfoCustomer).then(_res => {
            console.log('right');
            this.$message({
              type: 'success',
              message: '新增成功!',
            });
            this.resetForm();
            this.customerDialogVisible = false;
            getSaleStrategy({ params : { strategyType: 'Customer' } }).then(_res => {
              this.saleStrategyCustomerList = _res.result;
            })
          })
        }
      })
    },

    submitSaleStrategyProduct(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          createSaleStrategy(this.addInfoProduct).then(_res => {
            console.log('right');
            this.$message({
              type: 'success',
              message: '新增成功!',
            });
            this.resetForm();
            this.productDialogVisible = false;
            getSaleStrategy({ params : { strategyType: 'Product' } }).then(_res => {
              this.saleStrategyProductList = _res.result;
            })
          })
        }
      })
    },

    submitSaleStrategyPrice(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          createSaleStrategy(this.addInfoPrice).then(_res => {
            console.log('right');
            this.$message({
              type: 'success',
              message: '新增成功!',
            });
            this.resetForm();
            this.priceDialogVisible = false;
            getSaleStrategy({ params : { strategyType: 'Price' } }).then(_res => {
              this.saleStrategyPriceList = _res.result;
            })
          })
        }
      })
    },
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

.box-card {
  margin: 0 auto;
  width: 90%;
  margin-top: 10px;
  overflow-y: auto;

}


.customerTable{
  margin: 0 auto;
  width: 90%;
  margin: 10px;
  overflow-y: auto;
}

</style>