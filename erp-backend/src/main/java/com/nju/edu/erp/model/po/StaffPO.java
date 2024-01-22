package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffPO {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别 (M/F)
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birth;
    /**
     * 手机
     */
    private String phone;
    /**
     * 工作岗位
     */
    private String type;
    /**
     * 基本工资
     */
    private BigDecimal salary;
    /**
     * 岗位工资
     */
    private BigDecimal jobSalary;
    /**
     * 岗位级别
     */
    private Integer jobLevel;
    /**
     * 薪资计算方式
     */
    private String moneyCalculate;
    /**
     * 薪资发放方式
     */
    private String moneyMethod;
    /**
     * 工资卡账户
     */
    private String account;
}
