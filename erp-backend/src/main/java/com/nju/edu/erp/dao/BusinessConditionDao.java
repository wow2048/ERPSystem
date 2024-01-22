package com.nju.edu.erp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
@Mapper
public interface BusinessConditionDao {

    public int getSalesRevenue(Date beginDate, Date endDate);

//    public int getCommodityOverflow(Date beginDate, Date endDate);
//
//    public int getCostChange(Date beginDate, Date endDate);

    public int getGap(Date beginDate, Date endDate);

    public int getDiscount(Date beginDate, Date endDate);

    public int getVoucher(Date beginDate, Date endDate);

    public int getSalesCost(Date beginDate, Date endDate);

//    public int getCommodityLossCost(Date beginDate, Date endDate);
//
//    public int getCommodityGiveCost(Date beginDate, Date endDate);

    public int getLaborCost(Date beginDate, Date endDate);

}
