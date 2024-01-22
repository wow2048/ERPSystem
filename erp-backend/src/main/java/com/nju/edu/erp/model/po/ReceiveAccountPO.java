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
public class ReceiveAccountPO {
    /**
     * 收款单id
     */
    private String id;
    /**
     * 银行账户id
     */
    private Integer bankId;
    /**
     * 转账金额
     */
    private BigDecimal receiveMoney;
    /**
     * 备注
     */
    private String remark;
}
