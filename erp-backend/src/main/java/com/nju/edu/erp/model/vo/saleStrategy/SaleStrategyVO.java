package com.nju.edu.erp.model.vo.saleStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleStrategyVO {

     // 共同的部分

    /**
     * 销售策略id
     */
    private Integer id;

    /**
     * 销售策略类型
     */
    private String saleStrategyType;

    /**
     * 起始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    // 面向用户的部分

    /**
     * 客户级别
     */
    private Integer level;

    /**
     * 客户级别销售策略的赠品列表
     */
    private List<SaleStrategyGiftVO> giftsCustomer;

    /**
     * 折让
     */
    private BigDecimal discount;

    /**
     * 代金券
     */
    private BigDecimal voucherAmountCustomer;

    // 面向组合商品

    /**
     * 组合商品列表
     */
    private List<SaleStrategyGiftVO> specialProducts;

    /**
     * 组合商品的总价
     */
    private BigDecimal totalPrice;

    /**
     * 优惠价格
     */
    private BigDecimal specialPrice;

    // 面向不同总价

    /**
     * 触发价格
     */
    private BigDecimal triggerPrice;

    /**
     * 不同总价销售策略的赠品列表
     */
    private List<SaleStrategyGiftVO> giftsPrice;

    /**
     * 代金券
     */
    private BigDecimal voucherAmountPrice;

}
