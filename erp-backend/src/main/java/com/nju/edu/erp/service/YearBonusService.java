package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.YearBonusVO;

import java.util.List;

public interface YearBonusService {

    /**
     * 创建一项年终奖数据
     * @param yearBonusVO 年终奖
     */
    void createBonus(YearBonusVO yearBonusVO);

    /**
     * 查询年终奖
     * @return 员工年终奖列表
     */
    List<YearBonusVO> getYearBonus();

}
