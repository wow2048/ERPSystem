package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface BusinessProgressDao {

    /**
     * 获取销售单
     * */
    public List<SaleSheetPO> getSaleSheet(Date beginDate, Date endDate, String customer, String salesman);

    /**
     * 获取销售退货单
     * */
    public List<SaleReturnsSheetPO> getSaleReturnsSheet(Date beginDate, Date endDate, String customer, String salesman);

    /**
     * 获取进货单
     * */
    public List<PurchaseSheetPO> getPurchaseSheet(Date beginDate, Date endDate, String customer, String salesman);


    /**
     * 获取进货退货单
     * */
    public List<PurchaseReturnsSheetPO> getPurchaseReturnsSheet(Date beginDate, Date endDate, String customer, String salesman);

    /**
     * 获取工资单
     * */
    public List<SalarySheetPO> getSalarySheet(Date beginDate, Date endDate, String customer, String salesman);

    /**
     * 获取付款单
     * */
    public List<PaymentSheetPO> getPaymentSheet(Date beginDate, Date endDate, String customer, String salesman);

    /**
     * 获取收款单
     * */
    public List<ReceiptSheetPO> getReceiptSheet(Date beginDate, Date endDate, String customer, String salesman);
}
