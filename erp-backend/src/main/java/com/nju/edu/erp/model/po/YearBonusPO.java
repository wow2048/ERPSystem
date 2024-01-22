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
public class YearBonusPO {

    /**
     * 员工编号
     */
    Integer employeeID;

    /**
     * 年终奖
     */
    BigDecimal bonus;

}
