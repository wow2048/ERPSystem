package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SalesDetailsPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface SalesDetailsDao {

    /**
     * 查看销售明细表
     * 筛选条件有：时间区间，商品名，客户，业务员，仓库
     */
    List<SalesDetailsPO> findSalesDetails(Date beginDate, Date endDate, String productName, String customer, String salesman);
}
