package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayAccountPO {
    /**
     * 付款单id
     */
    private String id;
    /**
     * 银行账户id
     */
    private Integer bankId;
    /**
     * 付款金额
     */
    private BigDecimal payMoney;
    /**
     * 备注
     */
    private String remark;
}
