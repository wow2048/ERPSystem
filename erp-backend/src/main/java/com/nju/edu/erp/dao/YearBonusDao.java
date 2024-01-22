package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.YearBonusPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface YearBonusDao {

    /**
     * 创建一条年终奖记录
     * @param yearBonusPO 年终奖
     * @return 影响的行数
     */
    int createBonus(YearBonusPO yearBonusPO);

    /**
     * 查询所有的年终奖记录
     * @return List<YearBonusPO> 年终奖列表
     */
    List<YearBonusPO> findAllBonus();

    /**
     * 根据员工id删除年终奖记录
     * @param id 员工编号
     * @return 影响的行数
     */
    int deleteBonusById(Integer id);

    /**
     * 根据员工编号查询相关年终奖记录
     * @param id 员工编号
     * @return 年终奖记录
     */
    YearBonusPO getBonusById(Integer id);
}
