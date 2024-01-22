package com.nju.edu.erp.model.vo.saleStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleStrategyGiftVO {

    /**
     * 商品id
     */
    private String pid;

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
