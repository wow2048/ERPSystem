package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.StaffType;
import com.nju.edu.erp.model.po.StaffPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StaffDao {
    void createStaff(StaffPO staffPO);

    int updateOne(StaffPO staffPO);

    StaffPO findOneById(Integer supplier);

//    List<StaffPO> findAllByType(String staffType);

    void deleteById(Integer id);

    List<StaffPO> findAllById();

    String findStrategyByPosition(String position);

    Integer findPositionSalary(String position);

    Integer findCommission(String salesman);

    String findTypeByName(String name);

    Integer findBaseSalaryById(Integer id);
}
