package com.nju.edu.erp.service.Impl.SalaryStrategy;

import org.springframework.stereotype.Component;

@Component
public interface SalaryStrategy {
    Integer getCommission(String name);

    double getSalary(Integer id);


}