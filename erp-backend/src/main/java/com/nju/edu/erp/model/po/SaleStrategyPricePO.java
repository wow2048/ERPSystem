package com.nju.edu.erp.model.po;

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
public class SaleStrategyPricePO {

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

    /**
     * 触发价格
     */
    private BigDecimal triggerPrice;

    /**
     * 代金券
     */
    private BigDecimal voucherAmountPrice;
}
