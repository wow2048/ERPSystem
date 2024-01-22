package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CheckInRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.nju.edu.erp.model.po.CheckInPO;

import java.util.Date;
import java.util.List;
@Repository
@Mapper
public interface CheckInDao {
    void addCheckIn(CheckInPO checkInPO);

    List<CheckInPO> findAllById(Integer supplier);

    CheckInPO findById(Integer supplier);

    List<CheckInPO> findAll();

    List<CheckInRecordPO> findAllCheckInThisMonth();

    List<CheckInRecordPO> findAllCheckInLastMonth();

    Integer findCheckInLastMonthById(Integer id);

    void deleteById(Integer id);


}
