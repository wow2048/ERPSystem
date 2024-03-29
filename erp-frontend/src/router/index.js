import Vue from "vue";
import VueRouter from "vue-router";
import { ROLE, PATH } from "../common/const";

const Error = () => import("../components/content/Error");
const Login = () => import("../views/auth/Login");
const Home = () => import("../views/Home");
const CommodityManagement = () =>
  import("../views/commodity/CommodityManagement");
const CommodityClassification = () =>
  import("../views/commodity/CommodityClassification");
const InventoryManagement = () => import("../views/inventory/InventoryManagement");
const InventoryCheck = () => import("../views/inventory/InventoryCheck");
const InventoryOperation = () =>
  import("../views/inventory/InventoryOperation");
const InventoryIn = () => import("../views/inventory/InventoryIn");
const InventoryOut = () => import("../views/inventory/InventoryOut");
const InventoryLoss = () => import("../views/inventory/InventoryLoss");
const InventoryOverflow = () => import("../views/inventory/InventoryOverflow");
const InventoryPresent = () => import("../views/inventory/InventoryPresent");
const InventoryView = () => import("../views/inventory/InventoryView");
const InventoryWarning = () => import("../views/inventory/InventoryWarning");
const PurchaseView = () => import("../views/purchase/PurchaseView");
const PurchaseReturnView = () => import("../views/purchase/PurchaseReturnView");
const SaleView = () => import("../views/sale/SaleView");
const SaleReturnView = () => import("../views/sale/SaleReturnView");
const CustomerView = () => import ("../views/purchase/CustomerView");
const Approval = () => import("../views/approval/Approval");

const maxAmountCustomer = () => import("../views/sale/maxAmountCustomer");
const SaleStrategy = () => import("../views/sale/SaleStrategy");
//staff相关
const StaffManagement = () => import("../views/staff/StaffManagement");
const StaffCheckIn = () => import("../views/staff/StaffCheckIn");
const StaffSalaryStrategy = () => import("../views/staff/StaffSalaryStrategy");
const YearBonus = () => import("../views/staff/YearBonus");

//财务管理相关
const AccountView = () => import("../views/finance/AccountView");
const salesDetailsCheck = () => import("../views/finance/SalesDetailsCheck");
const businessProgressCheck = () => import("../views/finance/BusinessProgressCheck");
const businessConditionCheck = () => import("../views/finance/BusinessConditionCheck");
const paymentSheetView = () => import("../views/finance/PaymentSheetView");
const receiptSheetView = () => import("../views/finance/ReceiptSheetView");
const salarySheetView = () => import("../views/finance/SalarySheetView");

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Home
  },
  {
    path: "/error",
    component: Error
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/register",
    component: () => import("@/views/auth/register.vue")
  },
  // 商品管理
  {
    path: PATH.COMMODITY_CLASSIFICATION.path,
    component: CommodityClassification,
    meta: { requiresAuth: PATH.COMMODITY_CLASSIFICATION.requiresAuth }
  },
  {
    path: PATH.COMMODITY_MANAGEMENT.path,
    component: CommodityManagement,
    meta: { requiresAuth: PATH.COMMODITY_MANAGEMENT.requiresAuth }
  },
  // 库存管理
  {
    path: PATH.INVENTORY_MANAGEMENT.path,
    component: InventoryManagement,
    meta: { requiresAuth: PATH.INVENTORY_MANAGEMENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_CHECK.path,
    component: InventoryCheck,
    meta: { requiresAuth: PATH.INVENTORY_CHECK.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OPERATION.path,
    component: InventoryOperation,
    name: "InventoryOperation",
    meta: { requiresAuth: PATH.INVENTORY_OPERATION.requiresAuth }
  },
  {
    path: PATH.INVENTORY_IN.path,
    component: InventoryIn,
    name: "InventoryIn",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OUT.path,
    component: InventoryOut,
    name: "InventoryOut",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_LOSS.path,
    component: InventoryLoss,
    name: "InventoryLoss",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OVERFLOW.path,
    component: InventoryOverflow,
    name: "InventoryOverflow",
    meta: { requiresAuth: PATH.INVENTORY_OVERFLOW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_PRESENT.path,
    component: InventoryPresent,
    name: "InventoryPresent",
    meta: { requiresAuth: PATH.INVENTORY_PRESENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_VIEW.path,
    component: InventoryView,
    meta: { requiresAuth: PATH.INVENTORY_VIEW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_WARNING.path,
    component: InventoryWarning,
    name: "InventoryWarning",
    meta: { requiresAuth: PATH.INVENTORY_WARNING.requiresAuth }
  },
  // 销售管理
  {
    path: PATH.PURCHASE_VIEW.path,
    component: PurchaseView,
    name: "PurchaseView",
    meta: { requiresAuth: PATH.PURCHASE_VIEW.requiresAuth }
  },
  {
    path: PATH.PURCHASE_RETURN_VIEW.path,
    component: PurchaseReturnView,
    name: "PurchaseReturnView",
    meta: { requiresAuth: PATH.PURCHASE_RETURN_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_VIEW.path,
    component: SaleView,
    name: "SaleView",
    meta: { requiresAuth: PATH.SALE_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_RETURN_VIEW.path,
    component: SaleReturnView,
    name: "SaleReturnView",
    meta: { requiresAuth: PATH.SALE_RETURN_VIEW.requiresAuth }
  },
  {
    path: PATH.CUSTOMER_VIEW.path,
    component: CustomerView,
    name: "CustomerView",
    meta: { requiresAuth: PATH.CUSTOMER_VIEW.requiresAuth }
  },
// 获取某个销售人员某段时间内消费总金额最大的客户
{
  path: PATH.MAX_AMOUNT_CUSTOMER.path,
  component: maxAmountCustomer,
  name: "maxAmountCustomer",
  meta: { requiresAuth: PATH.MAX_AMOUNT_CUSTOMER.requiresAuth }
},
  {
    path: PATH.SALE_STRATEGY.path,
    component: SaleStrategy,
    meta: { requiresAuth: PATH.SALE_STRATEGY.requiresAuth}
  },
  // 审批
  {
    path: PATH.GM_APPROVAL.path,
    component: Approval,
    meta: { requiresAuth: PATH.GM_APPROVAL.requiresAuth }
  },
  //员工管理
  {
    path: PATH.STAFF_MANAGEMENT.path,
    component: StaffManagement,
    meta: { requiresAuth: PATH.STAFF_MANAGEMENT.requiresAuth }
  },
    //员工打卡
  {
    path: PATH.STAFF_CHECKIN.path,
    component: StaffCheckIn,
    meta: { requiresAuth: PATH.STAFF_MANAGEMENT.requiresAuth }
  },
    //薪酬制定
  {
    path: PATH.STAFF_SALARYSTRATEGY.path,
    component: StaffSalaryStrategy,
    meta: { requiresAuth: PATH.STAFF_MANAGEMENT.requiresAuth }
  },
   //制定年终奖
   {
    path: PATH.YEAR_BONUS.path,
    component: YearBonus,
    meta: {requiresAuth: PATH.YEAR_BONUS.requiresAuth}
   },

  /**
   * 财务管理相关功能
   */

  // 查看销售明细表
   {
    path: PATH.SALE_DETAIL_CHECK.path,
    component: salesDetailsCheck,
    meta: { requiresAuth: PATH.FINANCE_MANAGEMENT.requiresAuth }
  },
  // 查看经营历程表
  {
    path: PATH.BUSINESS_PROGRESS_CHECK.path,
    component: businessProgressCheck,
    meta: { requiresAuth: PATH.FINANCE_MANAGEMENT.requiresAuth }
  },
  // 查看经营情况表
  {
    path: PATH.BUSINESS_CONDITION_CHECK.path,
    component: businessConditionCheck,
    meta: { requiresAuth: PATH.FINANCE_MANAGEMENT.requiresAuth }
  },
  // 制定付款单
  {
    path: PATH.PAYMENT_SHEET_VIEW.path,
    component: paymentSheetView,
    meta: { requiresAuth: PATH.PAYMENT_SHEET_VIEW.requiresAuth }
  },
  // 制定收款单
  {
    path: PATH.RECEIPT_SHEET_VIEW.path,
    component: receiptSheetView,
    meta: { requiresAuth: PATH.RECEIPT_SHEET_VIEW.requiresAuth }
  },
  // 制定工资单
  {
    path: PATH.SALARY_SHEET_VIEW.path,
    component: salarySheetView,
    meta: { requiresAuth: PATH.SALARY_SHEET_VIEW.requiresAuth }
  },
  // 管理账户
  {
    path: PATH.ACCOUNT_VIEW.path,
    component: AccountView,
    meta: {requiresAuth: PATH.ACCOUNT_VIEW.requiresAuth}
  },

  // -----------------------未找到页面-----------------------------
  {
    path: "*",
    redirect: "/error"
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
  // console.log(to.path);
  if (to.path === "/error" || to.path === "/login") {
    next();
  } else if (to.path === "/") {
    let token = sessionStorage.getItem("token");
    let role = sessionStorage.getItem("role");
    if (token == null || role == null) next("/login");
    else next();
  } else if (to.meta.requiresAuth) {
    if (
      to.meta.requiresAuth.some(
        role => role.toString() === sessionStorage.getItem("role")
      )
    ) {
      //有权限
      // console.log("获得访问权限");
      next()
    } else {
      // console.log("无权限访问");
      next("/"); //无权限,跳回主页
    }
  } else {
    // 非法路径, 直接next (跳转error)
    next();
  }
});

export default router;
