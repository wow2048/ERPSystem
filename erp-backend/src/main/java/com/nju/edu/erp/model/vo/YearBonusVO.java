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
public class YearBonusVO {

    /**
     * 员工编号
     */
    Integer employeeID;

    /**
     * 员工姓名
     */
    String name;

    /**
     * 员工前11个月工资总和
     */
    BigDecimal totalSalaryOfElevenMonths;

    /**
     * 年终奖
     */
    BigDecimal bonus;

}
