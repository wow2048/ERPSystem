package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessConditionVO {

    /**
     * 销售收入
     * */
    private int salesRevenue;

    /**
     * 商品报溢收入
     * */
    private int commodityOverflow;

    /**
     * 成本调价收入
     * */
    private int costChange;

    /**
     * 进货退货差价
     * */
    private int gap;

    /**
     * 代金券与实际收款差额收入
     * */
    private int voucher;

    /**
     * 收入折让
     * */
    private int discount;

    /**
     * 总收入
     * */
    private int totalRevenue;

    /**
     * 销售成本
     * */
    private int salesCost;

    /**
     * 商品报损支出
     * */
    private int commodityLossCost;

    /**
     * 商品赠出支出
     * */
    private int commodityGiveCost;

    /**
     * 人力成本
     * */
    private int laborCost;

    /**
     * 总支出
     * */
    private int totalCost;

    /**
     * 利润
     * */
    private int profit;

}

