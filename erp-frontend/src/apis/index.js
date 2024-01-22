//TEST
const TEST_GET = "/api/test/get";
const TEST_POST = "/api/test/post";

const AUTH = "/api/user/auth"
const LOGIN = "/api/user/login"
const REGISTER = '/api/user/register'
const FIND_ALL_USERS = '/api/user/findAllUsers'


// 商品分类管理
const COMMODITY_CLASSIFICATION_ALL = '/api/category/queryAll'
const COMMODITY_CLASSIFICATION_CREATE = '/api/category/create'
const COMMODITY_CLASSIFICATION_UPDATE = '/api/category/update'
const COMMODITY_CLASSIFICATION_DELETE = '/api/category/delete'

// 商品管理
const COMMODITY_ALL = '/api/product/queryAll';
const COMMODITY_CREATE = '/api/product/create';
const COMMODITY_UPDATE = '/api/product/update';
const COMMODITY_DELETE = '/api/product/delete';


const WAREHOUSE_INPUT = '/api/warehouse/input';
const WAREHOUSE_OUTPUT_PRE = '/api/warehouse/product/count';
const WAREHOUSE_OUTPUT = '/api/warehouse/output';
const WAREHOUSE_GET_INPUTSHEET = '/api/warehouse/inputSheet/state';
const WAREHOUSE_GET_OUTPUTSHEET = '/api/warehouse/outputSheet/state';
const WAREHOUSE_IO_DEATIL_BY_TIME = '/api/warehouse/sheetContent/time';
const WAREHOUSE_IPQ_BY_TIME = '/api/warehouse/inputSheet/time/quantity';
const WAREHOUSE_OPQ_BY_TIME = '/api/warehouse/outputSheet/time/quantity';
const WAREHOUSE_OUTPUTSHEET_APPROVE = '/api/warehouse/outputSheet/approve';
const WAREHOUSE_INPUTSHEET_APPROVE = '/api/warehouse/inputSheet/approve';
const WAREHOUSE_DAILY_COUNT = '/api/warehouse/warehouse/counting';

// 进货管理
const PURCHASE_ALL = '/api/purchase/sheet-show';
const PURCHASE_FIND_SHEET_BY_ID = '/api/purchase/find-sheet';
const PURCHASE_CREATE = '/api/purchase/sheet-make';
const PURCHASE_FIRST_APPROVAL = '/api/purchase/first-approval';
const PURCHASE_SECOND_APPROVAL = '/api/purchase/second-approval';
// 进货退货管理
const PURCHASE_RETURN_ALL = '/api/purchase-returns/sheet-show';
const PURCHASE_RETURN_CREATE = '/api/purchase-returns/sheet-make';
const PURCHASE_RETURN_FIRST_APPROVAL = '/api/purchase-returns/first-approval';
const PURCHASE_RETURN_SECOND_APPROVAL = '/api/purchase-returns/second-approval';
// 销售管理
const SALE_ALL = '/api/sale/sheet-show';
const SALE_CREATE = '/api/sale/sheet-make';
const SALE_FIRST_APPROVAL = '/api/sale/first-approval';
const SALE_SECOND_APPROVAL = '/api/sale/second-approval';
const SALE_FIND_SHEET_BY_ID = '/api/sale/find-sheet';
// 销售退货管理
const SALE_RETURN_ALL = '/api/sale-returns/sheet-show';
const SALE_RETURN_CREATE = '/api/sale-returns/sheet-make';
const SALE_RETURN_FIRST_APPROVAL = '/api/sale-returns/first-approval';
const SALE_RETURN_SECOND_APPROVAL = '/api/sale-returns/second-approval';
// 客户管理
const CUSTOMER_QUERY = '/api/customer/findByType';
const CUSTOMER_CREATE = '/api/customer/createCustomer';
const CUSTOMER_UPDATE = '/api/customer/updateOne';
const CUSTOMER_DELETE = '/api/customer/deleteById';
const SALE_PURCHASE_ALL = '/api/purchase/sheet-show';
const SALE_PURCHASE_CREATE = '/api/purchase/sheet-make';
const SALE_CUSTOMER_QUERY = '/api/customer/findByType';
const SALE_CUSTOMER_MAX = '/api/sale/maxAmountCustomer';

//员工管理
// const STAFF_QUERY = '/api/staff/findByType';
const STAFF_CREATE = '/api/staff/createStaff';
const STAFF_UPDATE = '/api/staff/updateOne';
const STAFF_DELETE = '/api/staff/deleteById';
const STAFF_INQUIRE = '/api/staff/findById';
const STAFF_SALARY = '/api/staff/findPositionSalary';
const STAFF_SALARY_STRATEGY = '/api/staff/findStrategyByPosition';
const STAFF_COMMISSION = '/api/staff/findCommission';

//员工打卡
// const CHECKIN_QUERY = '/api/staff/findCheckById';
const CHECKIN_CREATE = '/api/staff/addCheckIn';
const CHECKIN_QUERYALL = '/api/staff/getAllLatest';
// const CHECKIN_GETID = '/api/staff/findIdLatest';
const CHECKIN_THISMONTH = '/api/staff/findAllCheckInThisMonth';
const CHECKIN_LASTMONTH = '/api/staff/findAllCheckInLastMonth';
const CHECKIN_DELETE = '/api/staff/deleteCheckInById'

// 制定年终奖
const BONUS_ADD = '/api/yearBonus/addBonus';
const BONUS_GET = '/api/yearBonus/getBonus';

//财务管理
const SALE_DETAIL_CHECK = '/api/finance/salesDetails'; // 查看销售明细表
const GET_SHEET = '/api/finance/getSheet';
const GET_BUSINESS_CONDITION = 'api/finance/getBusinessCondition';
const PAYMENT_SHEET_ALL = '/api/payment-sheet/sheet-show';
const RECEIPT_SHEET_ALL = '/api/receipt-sheet/sheet-show';
const SALARY_SHEET_ALL = '/api/salary-sheet/sheet-show';
const PAYMENT_SHEET_MAKE = 'api/payment-sheet/sheet-make';
const RECEIPT_SHEET_MAKE = 'api/receipt-sheet/sheet-make';
const SALARY_SHEET_MAKE = 'api/salary-sheet/sheet-make';
const PAYMENT_SHEET_APPROVAL = 'api/payment-sheet/approval';
const RECEIPT_SHEET_APPROVAL = 'api/receipt-sheet/approval';
const SALARY_SHEET_APPROVAL = 'api/salary-sheet/approval';
const PAYMENT_SHEET_UNREAD = 'api/payment-sheet/unread-get';
const RECEIPT_SHEET_UNREAD = 'api/receipt-sheet/unread-get';
const SALARY_SHEET_UNREAD = 'api/salary-sheet/unread-get';
const PAYABLESALAY_GET = 'api/salary-sheet/payableSalary-get';
const ACCOUNT_ALL = 'api/account/getAllAccount';

// 账户管理
const ACCOUNT_CREATE = '/api/account/createAccount';
const ACCOUNT_SEARCH = '/api/account/searchAccount';
const ACCOUNT_UPDATE = '/api/account/updateAccount';
const ACCOUNT_DELETE = '/api/account/deleteAccount';

// 制定销售策略
const SALE_STRATEGY_CREATE = 'api/saleStrategy/createStrategy';
const SALE_STRATEGY_GET = 'api/saleStrategy/getStrategy';


module.exports = {
  TEST_GET,
  TEST_POST,

  AUTH,
  LOGIN,
  REGISTER,
  FIND_ALL_USERS,


  COMMODITY_CLASSIFICATION_ALL,
  COMMODITY_CLASSIFICATION_CREATE,
  COMMODITY_CLASSIFICATION_UPDATE,
  COMMODITY_CLASSIFICATION_DELETE,

  COMMODITY_ALL,
  COMMODITY_CREATE,
  COMMODITY_UPDATE,
  COMMODITY_DELETE,

  WAREHOUSE_INPUT,
  WAREHOUSE_OUTPUT_PRE,
  WAREHOUSE_OUTPUT,
  WAREHOUSE_GET_INPUTSHEET,
  WAREHOUSE_GET_OUTPUTSHEET,
  WAREHOUSE_IO_DEATIL_BY_TIME,
  WAREHOUSE_IPQ_BY_TIME,
  WAREHOUSE_OPQ_BY_TIME,
  WAREHOUSE_OUTPUTSHEET_APPROVE,
  WAREHOUSE_INPUTSHEET_APPROVE,
  WAREHOUSE_DAILY_COUNT,

  PURCHASE_ALL,
  PURCHASE_CREATE,
  PURCHASE_FIRST_APPROVAL,
  PURCHASE_SECOND_APPROVAL,
  PURCHASE_RETURN_ALL,
  PURCHASE_RETURN_CREATE,
  PURCHASE_RETURN_FIRST_APPROVAL,
  PURCHASE_RETURN_SECOND_APPROVAL,
  PURCHASE_FIND_SHEET_BY_ID,

  SALE_ALL,
  SALE_CREATE,
  SALE_FIRST_APPROVAL,
  SALE_SECOND_APPROVAL,

  SALE_RETURN_ALL,
  SALE_RETURN_CREATE,
  SALE_RETURN_FIRST_APPROVAL,
  SALE_RETURN_SECOND_APPROVAL,

  SALE_CUSTOMER_QUERY,
  SALE_CUSTOMER_MAX,
  SALE_FIND_SHEET_BY_ID,
  CUSTOMER_QUERY,
  CUSTOMER_CREATE,
  CUSTOMER_UPDATE,
  CUSTOMER_DELETE,

  // STAFF_QUERY,
  STAFF_CREATE,
  STAFF_UPDATE,
  STAFF_DELETE,
  STAFF_INQUIRE,
  STAFF_SALARY,
  STAFF_SALARY_STRATEGY,
  STAFF_COMMISSION,

  // CHECKIN_QUERY,
  CHECKIN_CREATE,
  CHECKIN_QUERYALL,
  // CHECKIN_GETID,
  CHECKIN_THISMONTH,
  CHECKIN_LASTMONTH,
  CHECKIN_DELETE,

  SALE_DETAIL_CHECK,
  GET_SHEET,

  ACCOUNT_CREATE,
  ACCOUNT_SEARCH,
  ACCOUNT_UPDATE,
  ACCOUNT_DELETE,

  SALE_STRATEGY_CREATE,
  SALE_STRATEGY_GET,
  GET_BUSINESS_CONDITION,
  PAYMENT_SHEET_ALL,
  RECEIPT_SHEET_ALL,
  SALARY_SHEET_ALL,
  PAYMENT_SHEET_MAKE,
  RECEIPT_SHEET_MAKE,
  SALARY_SHEET_MAKE,
  PAYMENT_SHEET_APPROVAL,
  RECEIPT_SHEET_APPROVAL,
  SALARY_SHEET_APPROVAL,
  PAYMENT_SHEET_UNREAD,
  RECEIPT_SHEET_UNREAD,
  SALARY_SHEET_UNREAD,
  PAYABLESALAY_GET,
  ACCOUNT_ALL,

  BONUS_ADD,
  BONUS_GET,

};
