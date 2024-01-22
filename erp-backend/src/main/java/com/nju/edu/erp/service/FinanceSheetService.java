package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;

import java.util.List;

public interface FinanceSheetService {
    /**
     * 创建单据
     * @param vo 单据
     * @return id
     */
    String makeSheet(FinanceSheetVO vo);

    /**
     * 根据单据状态获取销售单
     * @param state 单据状态
     * @return 单据列表
     */
    List<FinanceSheetVO> getSheetByState(FinanceSheetState state);

    /**
     * 审批单据
     * @param sheetId 单据id
     * @param state 单据状态
     */
    void approval(String sheetId, FinanceSheetState state);

    /**
     * 获取未读的审批结果通知
     */
    List<FinanceSheetVO> getSheetUnread(String operator);


    /**
     * 根据id返回单据
     * @param id 单据id
     * @return 单据
     */
    FinanceSheetVO getSheetById(String id);
}
