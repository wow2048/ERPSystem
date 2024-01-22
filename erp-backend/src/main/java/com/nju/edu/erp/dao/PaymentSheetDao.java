package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentSheetDao {
    /**
     * 存入一张付款单
     * @param paymentSheet 付款单
     */
    void saveSheet(PaymentSheetPO paymentSheet);

    /**
     * 把付款单上的转账（转出账目）列表存入数据库
     * @param payAccountList 转账（转出账目）列表
     */
    int savePayAccounts(List<PayAccountPO> payAccountList);

    /**
     * 查找所有的付款单
     */
    List<PaymentSheetPO> findAllSheet();

    /**
     * 查找指定id的付款单
     * @param id 付款单id
     * @return 付款单
     */
    PaymentSheetPO findSheetById(String id);

    /**
     * 根据付款单id查找指定付款单下的转账列表
     * @param sheetId 付款单id
     */
    List<PayAccountPO> findPayAccountsBySheetId(String sheetId);

    /**
     * 更新指定付款单的状态
     * @param sheetId 付款单id
     * @param state 新状态
     */
    void updateSheetState(String sheetId, FinanceSheetState state);

    /**
     * 获取最近一条付款单
     * @return 最近一条付款单
     */
    PaymentSheetPO getLatest();

    /**
     * 查找指定state的付款单
     * @param state 指定的状态
     * return 付款单列表
     */
    List<PaymentSheetPO> findSheetByState(FinanceSheetState state);

    /**
     * 获取某操作员未读的且审批结束的单据
     * @return 未读的且审批结束的单据
     */
    List<PaymentSheetPO> getApprovalUnreadByOperator(String operator);

    /**
     * 将单据设置为已读
     */
    void setSheetRead(String sheetId);
}
