package com.nju.edu.erp.model.vo.saleReturns;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
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
public class SaleReturnsSheetVO {

    /**
     * 销售退货单单据编号（格式为：XSTHD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 关联的销售单单据编号id
     */
    private String saleSheetId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 退货的总额合计
     */
    private BigDecimal totalAmount;
    /**
     * 单据状态
     */
    private SaleReturnsSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 进货单具体内容
     */
    List<SaleReturnsSheetContentVO> saleReturnsSheetContent;
}
