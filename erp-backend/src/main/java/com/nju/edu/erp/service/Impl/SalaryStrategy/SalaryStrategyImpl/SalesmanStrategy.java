package com.nju.edu.erp.service.Impl.SalaryStrategy.SalaryStrategyImpl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.model.po.CheckInRecordPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.service.Impl.SalaryStrategy.SalaryStrategy;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesmanStrategy implements SalaryStrategy {
    private StaffDao staffDao;
    private StaffService staffService;

    @Autowired
    public SalesmanStrategy(StaffDao staffDao,StaffService staffService){
        this.staffDao = staffDao;
        this.staffService = staffService;
    }
    @Override
    public Integer getCommission(String name) {
        Integer res = staffDao.findCommission(name);
        if(res == null) return 0;

        return staffDao.findCommission(name);
    }

    @Override
    public double getSalary(Integer id){
        Integer checkinTimes = staffService.findCheckInLastMonthById(id);
        double checkinRate = checkinTimes/20.0;
        if(checkinRate>1) checkinRate = 1;
        StaffPO staff = staffService.findStaffById(id);
        String name = staff.getName();
        String position = staff.getType();
        Integer commission = getCommission(name);
        Integer positionSalary = staffService.findPositionSalary(position);
        Integer baseSalary = staffService.findBaseSalary(id);
        return checkinRate*(baseSalary+positionSalary)+commission;
    }

}
