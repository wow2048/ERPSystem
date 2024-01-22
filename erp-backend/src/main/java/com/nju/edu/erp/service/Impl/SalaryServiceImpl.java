package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.service.Impl.SalaryStrategy.GetSalary;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService{
    private StaffService staffService;
    private GetSalary getSalary;

    @Autowired
    public SalaryServiceImpl(StaffService staffService,GetSalary getSalary){
        this.staffService = staffService;
        this.getSalary = getSalary;
    }

    public Integer findCommission(String name){
        String type;
        String staffType = staffService.findStaffTypeByName(name);
        if(staffType.equals("总经理")){type = "GMStrategy";}
        else if(staffType.equals("销售经理") || staffType.equals("进货销售人员")){
            type = "salesmanStrategy";
        }
        else{type = "commonStrategy";}
        return getSalary.getTargetCommission(type,name);
    }

    public double findLastMonthSalary(Integer id){
        StaffPO staff = staffService.findStaffById(id);
        String staffType = staff.getType();
        String type;
        if(staffType.equals("总经理")){type = "GMStrategy";}
        else if(staffType.equals("销售经理") || staffType.equals("进货销售人员")){
            type = "salesmanStrategy";
        }
        else{type = "commonStrategy";}
        return getSalary.getTargetSalary(type,id);
    }
}
