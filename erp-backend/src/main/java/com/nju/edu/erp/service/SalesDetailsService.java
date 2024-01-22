package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.SalesDetailsVO;


import java.util.List;

public interface SalesDetailsService {

    /**
     * 查看销售明细表
     * 筛选条件有：时间区间，商品名，客户，业务员
     * 显示符合上述条件的所有商
     * 品销售记录，以列表形式显示，
     * 列表中包含如下信息：时间（精确到天），商品名，型号，数量，单价，总额。需要支持导出数据
     */
    List<SalesDetailsVO> saleDetailsCounting(String beginDateStr,String endDateStr,String productName,String customer,String salesman);

}
