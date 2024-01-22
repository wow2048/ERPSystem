package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetPO {
    /**
     * 工资单单据编号（格式为：GZD-yyyyMMdd-xxxxx)
     */
    private String id;
    /**
     * 员工id
     */
    private Integer staffId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 公司银行账户id
     */
    private int bankId;
    /**
     * 员工工资卡银行账户；
     */
    private String staffAccount;
    /**
     * 应发工资
     */
    private BigDecimal payableSalary;
    /**
     * 扣除税款
     */
    private BigDecimal tax;
    /**
     * 实发金额
     */
    private BigDecimal actualSalary;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 单据状态
     */
    private FinanceSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 单据是否已读
     */
    private boolean isRead;
}
