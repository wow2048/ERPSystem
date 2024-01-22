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
public class SaleStrategyGiftPO {

    /**
     * 商品id
     */
    private String pid;

    /**
     * 关联的销售策略id
     */
    private Integer strategyId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 各项商品总价
     */
    private BigDecimal subTotal;

}
