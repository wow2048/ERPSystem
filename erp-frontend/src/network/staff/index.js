import request from "@/network/request"
import {CHECKIN_GETID, STAFF_REGISTER} from "@/apis";
const testAPI = require("@/apis")

export const getAllStaffById = config =>request._get(testAPI.STAFF_INQUIRE,config)
export const createStaff = config => request._post(testAPI.STAFF_CREATE, config)
export const updateStaff = config => request._post(testAPI.STAFF_UPDATE,config)
export const deleteById = config => request._get(testAPI.STAFF_DELETE,config)


export const createCheckIn = config => request._post(testAPI.CHECKIN_CREATE,config)
export const getAllCheckLatest = config =>request._get(testAPI.CHECKIN_QUERYALL,config)
export const findIdCheckInThisMonth = config =>request._get(testAPI.CHECKIN_THISMONTH,config)
export const findIdCheckInLastMonth = config =>request._get(testAPI.CHECKIN_LASTMONTH,config)
export const deleteCheckInById = config=>request._get(testAPI.CHECKIN_DELETE,config)
export const findStrategyByPosition = config =>request._get(testAPI.STAFF_SALARY_STRATEGY,config)
export const findBaseSalaryByPosition = config => request._get(testAPI.STAFF_SALARY,config)
export const getCommission = config => request._get(testAPI.STAFF_COMMISSION,config)

export const getBonus = config => request._get(testAPI.BONUS_GET, config);
export const addBonus = config => request._post(testAPI.BONUS_ADD, config);
