// 部署url
export const REQUEST_BASE_URL_PROD = "http://ismzl.com/";
// 开发url （mock的地址
export const REQUEST_BASE_URL_DEV = "http://localhost:8080";

export const ROLE = {
  INVENTORY_MANAGER: "INVENTORY_MANAGER", //库存管理人员
  SALE_STAFF: "SALE_STAFF", // 进货销售人员
  SALE_MANAGER: "SALE_MANAGER", //销售经理
  FINANCIAL_STAFF: "FINANCIAL_STAFF", // 财务人员
  HR: "HR", // 人力资源人员
  GM: "GM", // 总经理
  ADMIN: "ADMIN" // 最高权限
};


export const PATH = {
  // INVENTORY_MANAGER
  COMMODITY_CLASSIFICATION: {
    path: '/commodityClassification',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  COMMODITY_MANAGEMENT: {
    path: '/commodityManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_MANAGEMENT: {
    path: '/inventoryManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OPERATION: {
    path: '/inventoryOperation',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_CHECK: {
    path: '/inventoryCheck',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_IN: {
    path: '/inventoryIn',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OUT: {
    path: '/inventoryOut',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_LOSS: {
    path: '/inventoryLoss',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_WARNING: {
    path: '/inventoryWarning',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OVERFLOW: {
    path: '/inventoryOverflow',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_PRESENT: {
    path: '/inventoryPresent',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_VIEW: {
    path: '/inventoryView',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },

  // SALE_STAFF & SALE_MANAGER
  PURCHASE_VIEW: {
    path: '/purchaseView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  PURCHASE_RETURN_VIEW: {
    path: '/purchaseReturnView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_VIEW: {
    path: '/saleView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_RETURN_VIEW: {
    path: '/saleReturnView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  CUSTOMER_VIEW: {
    path: '/customerView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  MAX_AMOUNT_CUSTOMER: {
    path: '/maxAmountCustomer',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_STRATEGY: {
    path: '/saleStrategy',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  },

  // GM
  GM_APPROVAL: {
    path: '/approval',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  },

  // FINANCIAL_STAFF
  PAYMENT_SHEET_VIEW: {
    path: '/paymentSheetView',
    requiresAuth: [ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  RECEIPT_SHEET_VIEW: {
    path: '/receiptSheetView',
    requiresAuth: [ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  SALARY_SHEET_VIEW: {
    path: '/salarySheetView',
    requiresAuth: [ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },

  // GM & FINANCIAL_STAFF
  FINANCE_MANAGEMENT: {
    path: '/financeManagement',
    requiresAuth: [ROLE.GM,ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  SALE_DETAIL_CHECK: {
    path: '/salesDetailsCheck',
    requiresAuth: [ROLE.GM,ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  BUSINESS_PROGRESS_CHECK: {
    path: '/businessProgressCheck',
    requiresAuth: [ROLE.GM,ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  BUSINESS_CONDITION_CHECK: {
    path: '/businessConditionCheck',
    requiresAuth: [ROLE.GM,ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },
  ACCOUNT_VIEW: {
    path: '/accountView',
    requiresAuth: [ROLE.FINANCIAL_STAFF,ROLE.ADMIN]
  },

  //HR
  STAFF_MANAGEMENT: {
    path: '/staffManagement',
    requiresAuth: [ROLE.HR,ROLE.ADMIN]
  },
  STAFF_CHECKIN: {
    path: '/staffCheckIn',
    requiresAuth: [ROLE.HR,ROLE.ADMIN]
  },
  STAFF_SALARYSTRATEGY: {
    path: '/staffSalaryStrategy',
    requiresAuth: [ROLE.HR,ROLE.ADMIN]
  },
  YEAR_BONUS: {
    path: '/yearBonus',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  }
}
