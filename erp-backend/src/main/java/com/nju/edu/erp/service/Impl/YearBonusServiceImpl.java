package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.SalarySheetDao;
import com.nju.edu.erp.dao.YearBonusDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.YearBonusPO;
import com.nju.edu.erp.model.vo.YearBonusVO;
import com.nju.edu.erp.service.FinanceSheetService;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.service.YearBonusService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YearBonusServiceImpl implements YearBonusService {

    private final YearBonusDao yearBonusDao;
    private final StaffService staffService;
    private SalarySheetDao salarySheetDao;

    public YearBonusServiceImpl(YearBonusDao yearBonusDao, StaffService staffService, SalarySheetDao salarySheetDao){
        this.staffService = staffService;
        this.yearBonusDao = yearBonusDao;
        this.salarySheetDao = salarySheetDao;
    }

    /**
     * 创建一项年终奖数据
     * @param yearBonusVO 年终奖
     */
    @Override
    public void createBonus(YearBonusVO yearBonusVO){

        if(yearBonusVO.getEmployeeID() == null){
            throw new MyServiceException("G0000", "员工编号不能为空");
        }

        // 年终奖为空，默认为0
        if(yearBonusVO.getBonus() == null){
            yearBonusVO.setBonus(BigDecimal.ZERO);
        }
        else if(yearBonusVO.getBonus().compareTo(BigDecimal.ZERO) < 0){
            throw new MyServiceException("G0001", "年终奖金额不能为负数");
        }

        // 若相应员工编号已有年终奖记录，则删除该过时的数据
        YearBonusPO tmp = yearBonusDao.getBonusById(yearBonusVO.getEmployeeID());
        if(tmp != null){
            yearBonusDao.deleteBonusById(yearBonusVO.getEmployeeID());
        }

        YearBonusPO yearBonusPO = new YearBonusPO();
        BeanUtils.copyProperties(yearBonusVO, yearBonusPO);

        yearBonusDao.createBonus(yearBonusPO);
    }

    /**
     * 查询年终奖
     * @return 员工年终奖列表
     */
    @Override
    public List<YearBonusVO> getYearBonus(){
        List<YearBonusVO> res = new ArrayList<>();
        List<YearBonusPO> bonus = yearBonusDao.findAllBonus();
        List<StaffPO> staff = staffService.getStaffsById();
        List<SalarySheetPO> salarySheets = salarySheetDao.findSheetByState(FinanceSheetState.SUCCESS);

        Map<Integer, YearBonusPO> map = new HashMap<>();
        for(YearBonusPO item: bonus){
            map.put(item.getEmployeeID(), item);
        }



        // 构建员工年终奖列表
        for(StaffPO po: staff){
            YearBonusVO vo = new YearBonusVO();
            vo.setEmployeeID(po.getId());
            vo.setName(po.getName());

            // vo.setTotalSalaryOfElevenMonths();
            BigDecimal salary = BigDecimal.ZERO;
            for(SalarySheetPO item: salarySheets){
                if(item.getStaffId().compareTo(po.getId()) == 0){
                    salary = salary.add(item.getActualSalary());
                }
            }
            vo.setTotalSalaryOfElevenMonths(salary);

            // 年终奖未设置时默认为0
            YearBonusPO item = map.get(po.getId());
            if(item != null){
                vo.setBonus(item.getBonus());
            }
            else{
                vo.setBonus(BigDecimal.ZERO);
            }

            res.add(vo);
        }

        return res;
    }

}
