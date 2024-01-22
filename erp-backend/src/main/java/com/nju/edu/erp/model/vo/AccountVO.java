package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVO {

    /**
     * 账户id
     */
    private Integer id;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 账户余额
     */
    private BigDecimal amount;
}
