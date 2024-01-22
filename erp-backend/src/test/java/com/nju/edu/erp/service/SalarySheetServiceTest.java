package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.CheckInVO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.model.vo.financeSheet.SalarySheetVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class SalarySheetServiceTest {

    @Autowired
    @Qualifier(value = "salarySheet")
    FinanceSheetService salarySheetService;

    @Autowired
    StaffService staffService;

    @Test
    @Transactional
    @Rollback
    //测试不同工资最后的应付工资、税款、实际工资是否正确
    public void makeSheetTest(){
        SalarySheetVO salarySheetVO = SalarySheetVO.builder()
                .staffId(514)
                .bankId(1)
                .operator("caiwu")
                .build();
        StaffVO staff = StaffVO.builder()
                .id(514)
                .name("testPerson")
                .account("1919-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();

        staffService.createStaff(staff);

        //20天打了15天卡
        for (int i = 1; i <= 15; i++){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, i);
            cal.add(Calendar.MONTH, -1);
            CheckInVO checkInVO = CheckInVO.builder()
                    .id(514)
                    .checkRecord(cal.getTime())
                    .build();
            staffService.createCheck(checkInVO);
        }

        //库存管理人员岗位工资为3200，以下为不同区间的工资
        double[] salaryList = {1600, 4000, 8800, 24800, 40800, 56800, 80800, 116800};
        double[] payableSalaryList = {3600, 5400, 9000, 21000, 33000, 45000, 63000, 90000};
        double[] taxList = {0, 12, 190, 1760, 4340, 7590, 13140, 23090};
        for (int i = 0; i < 8; i++){
            double payableSalary, tax, actualSalary;

            staff.setSalary(BigDecimal.valueOf(salaryList[i]));
            StaffPO staffPO = new StaffPO();
            BeanUtils.copyProperties(staff, staffPO);
            staffService.updateStaff(staffPO);

            String id = salarySheetService.makeSheet(salarySheetVO);

            SalarySheetVO newVO = (SalarySheetVO) salarySheetService.getSheetById(id);
            payableSalary = newVO.getPayableSalary().doubleValue();
            tax = newVO.getTax().doubleValue();
            actualSalary = newVO.getActualSalary().doubleValue();

            Assertions.assertEquals(payableSalaryList[i], payableSalary);
            Assertions.assertEquals(taxList[i], tax);
            Assertions.assertEquals(payableSalaryList[i]-taxList[i], actualSalary);
        }
    }
}
