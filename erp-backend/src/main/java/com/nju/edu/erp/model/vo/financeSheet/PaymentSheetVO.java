package com.nju.edu.erp.model.vo.financeSheet;

import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetVO extends FinanceSheetVO {
    /**
     * 付款单单据编号（格式为：FKD-yyyyMMdd-xxxxx)
     */
    private String id;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 总额汇总
     */
    private BigDecimal totalAmount;
    /**
     * 付款列表
     */
    private List<PayAccountVO> payAccountList;
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
