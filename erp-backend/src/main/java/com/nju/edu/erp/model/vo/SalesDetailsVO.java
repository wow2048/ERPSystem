package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDetailsVO {

    /**
     *时间（精确到天）
     */
    private String time;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品型号
     */
    private String productType;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 状态
     * */
    private String state;

    /**
     * 备注
     */
    private String remark;

}
