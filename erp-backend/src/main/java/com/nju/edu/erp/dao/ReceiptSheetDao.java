package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.po.ReceiveAccountPO;
import com.nju.edu.erp.model.po.ReceiptSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceiptSheetDao {
    /**
     * 存入一张收款单
     * @param ReceiptSheet 收款单
     */
    void saveSheet(ReceiptSheetPO ReceiptSheet);

    /**
     * 把收款单上的转账（转入账目）列表存入数据库
     * @param ReceiveAccountList 转账（转入账目）列表
     */
    int saveReceiveAccounts(List<ReceiveAccountPO> ReceiveAccountList);

    /**
     * 查找所有的收款单
     */
    List<ReceiptSheetPO> findAllSheet();

    /**
     * 查找指定id的收款单
     * @param id 收款单id
     * @return 收款单
     */
    ReceiptSheetPO findSheetById(String id);

    /**
     * 根据收款单id查找指定收款单下的转账列表
     * @param sheetId 收款单id
     */
    List<ReceiveAccountPO> findReceiveAccountsBySheetId(String sheetId);

    /**
     * 更新指定收款单的状态
     * @param sheetId 收款单id
     * @param state 新状态
     */
    void updateSheetState(String sheetId, FinanceSheetState state);

    /**
     * 获取最近一条收款单
     * @return 最近一条收款单
     */
    ReceiptSheetPO getLatest();

    /**
     * 查找指定state的收款单
     * @param state 指定的状态
     * return 收款单列表
     */
    List<ReceiptSheetPO> findSheetByState(FinanceSheetState state);

    /**
     * 获取某操作员未读的且审批结束的单据
     * @return 未读的且审批结束的单据
     */
    List<ReceiptSheetPO> getApprovalUnreadByOperator(String operator);

    /**
     * 将单据设置为已读
     */
    void setSheetRead(String sheetId);
}
