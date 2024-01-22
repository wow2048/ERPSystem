package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AccountDao;
import com.nju.edu.erp.dao.SalarySheetDao;
import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.SalarySheetVO;
import com.nju.edu.erp.service.FinanceSheetService;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("salarySheet")
public class SalarySheetServiceImpl implements FinanceSheetService {

    SalarySheetDao salarySheetDao;
    AccountDao accountDao;
    StaffService staffService;
    SalaryService salaryService;

    @Autowired
    SalarySheetServiceImpl(SalarySheetDao salarySheetDao, AccountDao accountDao, StaffService staffService, SalaryService salaryService){
        this.salarySheetDao = salarySheetDao;
        this.accountDao = accountDao;
        this.staffService = staffService;
        this.salaryService = salaryService;
    }

    /**
     * 创建工资单
     * @param vo 工资单
     */
    @Override
    public String makeSheet(FinanceSheetVO vo){
        SalarySheetVO sheetVO = (SalarySheetVO) vo;
        SalarySheetPO sheetPO = new SalarySheetPO();
        BeanUtils.copyProperties(sheetVO, sheetPO);

        sheetPO.setCreateTime(new Date());

        sheetPO.setState(FinanceSheetState.PENDING);

        sheetPO.setRead(false);

        SalarySheetPO latest = salarySheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "GZD");
        sheetPO.setId(id);

        //表驱动实现
        double payableSalary = salaryService.findLastMonthSalary(sheetPO.getStaffId());
        sheetPO.setPayableSalary(new BigDecimal(payableSalary));
        double[] salaryRates = {0, 0.03, 0.1, 0.2, 0.25, 0.30, 0.35, 0.45};
        double[] salaryStandard = {0, 5000, 8000, 17000, 30000, 40000, 60000, 85000, Double.MAX_VALUE};
        double[] fastCalculateNum = {0, 0, 210, 1440, 2660, 4410, 7160, 15160};
        double tax = 0;
        //5000:税收起征点
        for (int i = 0; i < 8; i++){
            if (payableSalary >= salaryStandard[i] && payableSalary <= salaryStandard[i+1]){
                tax = (payableSalary - 5000) * salaryRates[i] - fastCalculateNum[i];
                sheetPO.setTax(new BigDecimal(tax));
                break;
            }
        }
        sheetPO.setActualSalary(new BigDecimal(payableSalary - tax));

        StaffPO staffPO = staffService.findStaffById(sheetPO.getStaffId());
        sheetPO.setStaffName(staffPO.getName());
        sheetPO.setStaffAccount(staffPO.getAccount());

        salarySheetDao.saveSheet(sheetPO);

        return id;
    }

    /**
     * 根据工资单状态获取销售单
     * @param state 工资单状态
     * @return 工资单列表
     */
    @Override
    public List<FinanceSheetVO> getSheetByState(FinanceSheetState state){
        List<FinanceSheetVO> res = new ArrayList<>();
        List<SalarySheetPO> all;

        if (state == null){
            all = salarySheetDao.findAllSheet();
        }else {
            all = salarySheetDao.findSheetByState(state);
        }

        for (SalarySheetPO po : all){
            SalarySheetVO vo = new SalarySheetVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
        }
        return res;
    }

    /**
     * 审批工资单
     * @param sheetId 工资单id
     * @param state 工资单状态
     */
    @Override
    public void approval(String sheetId, FinanceSheetState state){
        salarySheetDao.updateSheetState(sheetId, state);

        if (state.equals(FinanceSheetState.SUCCESS)) {
            SalarySheetPO salarySheetPO = salarySheetDao.findSheetById(sheetId);
            AccountPO accountPO = accountDao.getAccountById(salarySheetPO.getBankId());
            accountPO.setAmount(accountPO.getAmount().subtract(salarySheetPO.getPayableSalary()));
            accountDao.updateAmount(accountPO);
        }
    }

    /**
     * 获取未读的且审批结束的单据
     * @return 未读的且审批结束的单据
     */
    @Override
    public List<FinanceSheetVO> getSheetUnread(String operator){
        List<FinanceSheetVO> res = new ArrayList<>();
        List<SalarySheetPO> all;

        all = salarySheetDao.getApprovalUnreadByOperator(operator);

        for (SalarySheetPO po : all){
            SalarySheetVO vo = new SalarySheetVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
            salarySheetDao.setSheetRead(po.getId());
        }
        return res;
    }

    /**
     * 根据id返回单据
     * @param id 单据id
     * @return 单据
     */
    @Override
    public FinanceSheetVO getSheetById(String id){
        SalarySheetPO po = salarySheetDao.findSheetById(id);

        if (po == null){
            throw new MyServiceException("E0003", "无效的id");
        }

        SalarySheetVO vo = new SalarySheetVO();
        BeanUtils.copyProperties(po, vo);

        return vo;
    }
}
