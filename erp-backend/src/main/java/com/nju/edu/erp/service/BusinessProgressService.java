package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.PaymentSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.ReceiptSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.SalarySheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;

import java.util.List;

public interface BusinessProgressService {

    /**
     * 获取销售单
     * */
    public List<SaleSheetVO> getSaleSheet(String beginDateStr, String endDateStr, String customer, String salesman);

    /**
     * 获取销售退货单
     * */
    public List<SaleReturnsSheetVO> getSaleReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman);

    /**
     * 获取进货单
     * */
    public List<PurchaseSheetVO> getPurchaseSheet(String beginDateStr, String endDateStr, String customer, String salesman);


    /**
     * 获取进货退货单
     * */
    public List<PurchaseReturnsSheetVO> getPurchaseReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman);

    /**
     * 获取工资单
     * */
    public List<SalarySheetVO> getSalarySheet(String beginDateStr, String endDateStr, String customer, String salesman);

    /**
     * 获取付款单
     * */
    public List<PaymentSheetVO> getPaymentSheet(String beginDateStr, String endDateStr, String customer, String salesman);

    /**
     * 获取收款单
     * */
    public List<ReceiptSheetVO> getReceiptSheet(String beginDateStr, String endDateStr, String customer, String salesman);
}
