package com.nju.edu.erp.service;

public interface SalaryService {
    /**
     * 查找员工上个月的工资
     * @param id 员工id
     * @return 员工上个月的工资
     */
    double findLastMonthSalary(Integer id);

    /**
     *获取员工的提成
     * @param name 员工的姓名
     * @return 员工的提成
     */
    Integer findCommission(String name);
}
