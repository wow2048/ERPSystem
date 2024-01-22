import request from "@/network/request"
const testAPI = require("@/apis")

export const getSalesDetais = config => request._get(testAPI.SALE_DETAIL_CHECK, config);
export const getSheet = config => request._get(testAPI.GET_SHEET, config);
export const getBusinessCondition = config => request._get(testAPI.GET_BUSINESS_CONDITION, config);
export const getAllPaymentSheet = config => request._get(testAPI.PAYMENT_SHEET_ALL, config);
export const getAllReceiptSheet = config => request._get(testAPI.RECEIPT_SHEET_ALL, config);
export const getAllSalarySheet = config => request._get(testAPI.SALARY_SHEET_ALL, config);
export const getAllAccount = config => request._get(testAPI.ACCOUNT_ALL, config);
export const makePaymentSheet = config => request._post(testAPI.PAYMENT_SHEET_MAKE, config);
export const makeReceiptSheet = config => request._post(testAPI.RECEIPT_SHEET_MAKE, config);
export const makeSalarySheet = config => request._post(testAPI.SALARY_SHEET_MAKE, config);
export const paymentSheetApproval = config => request._get(testAPI.PAYMENT_SHEET_APPROVAL, config);
export const receiptSheetApproval = config => request._get(testAPI.RECEIPT_SHEET_APPROVAL, config);
export const salarySheetApproval = config => request._get(testAPI.SALARY_SHEET_APPROVAL, config);
export const paymentSheetUnread = config => request._get(testAPI.PAYMENT_SHEET_UNREAD, config);
export const receiptSheetUnread = config => request._get(testAPI.RECEIPT_SHEET_UNREAD, config);
export const salarySheetUnread = config => request._get(testAPI.SALARY_SHEET_UNREAD, config);
export const getPayableSalary = config => request._get(testAPI.PAYABLESALAY_GET, config);

export const createAccount = config => request._post(testAPI.ACCOUNT_CREATE, config);
export const updateAccount = config => request._post(testAPI.ACCOUNT_UPDATE, config);
export const deleteAccount = config => request._get(testAPI.ACCOUNT_DELETE, config);
export const searchAccount = config => request._get(testAPI.ACCOUNT_SEARCH, config);