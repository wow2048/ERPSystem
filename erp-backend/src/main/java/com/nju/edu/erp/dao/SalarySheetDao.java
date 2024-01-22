package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.po.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalarySheetDao {
    /**
     * 存入一张工资单
     * @param SalarySheet 工资单
     */
    void saveSheet(SalarySheetPO SalarySheet);

    /**
     * 查找所有的工资单
     */
    List<SalarySheetPO> findAllSheet();

    /**
     * 查找指定id的工资单
     * @param id 工资单id
     * @return 工资单
     */
    SalarySheetPO findSheetById(String id);

    /**
     * 更新指定工资单的状态
     * @param sheetId 工资单id
     * @param state 新状态
     */
    void updateSheetState(String sheetId, FinanceSheetState state);

    /**
     * 获取最近一条工资单
     * @return 最近一条工资单
     */
    SalarySheetPO getLatest();

    /**
     * 查找指定state的工资单
     * @param state 指定的状态
     * return 工资单列表
     */
    List<SalarySheetPO> findSheetByState(FinanceSheetState state);

    /**
     * 获取某操作员未读的且审批结束的单据
     * @return 未读的且审批结束的单据
     */
    List<SalarySheetPO> getApprovalUnreadByOperator(String operator);

    /**
     * 将单据设置为已读
     */
    void setSheetRead(String sheetId);
}
