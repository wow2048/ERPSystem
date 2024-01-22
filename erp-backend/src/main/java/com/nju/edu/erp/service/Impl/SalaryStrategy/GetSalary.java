package com.nju.edu.erp.service.Impl.SalaryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class GetSalary {
    private final Map<String, SalaryStrategy> strategyMap ;

    @Autowired
    public GetSalary(Map<String, SalaryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public Integer getTargetCommission(String salaryType,String name){
        SalaryStrategy targetStrategy = Optional.ofNullable(strategyMap.get(salaryType))
                .orElseThrow(()->new IllegalArgumentException("Invalid Strategy"));
        return targetStrategy.getCommission(name);
    }

    public double getTargetSalary(String salaryType,Integer id){
        SalaryStrategy targetStrategy = Optional.ofNullable(strategyMap.get(salaryType))
                .orElseThrow(()->new IllegalArgumentException("Invalid Strategy"));
        return targetStrategy.getSalary(id);
    }

}
