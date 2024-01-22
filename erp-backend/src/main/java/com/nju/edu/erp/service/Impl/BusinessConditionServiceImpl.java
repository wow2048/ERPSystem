package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BusinessConditionDao;
import com.nju.edu.erp.model.vo.BusinessConditionVO;
import com.nju.edu.erp.service.BusinessConditionService;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BusinessConditionServiceImpl implements BusinessConditionService {

    private final BusinessConditionDao businessConditionDao;

    public BusinessConditionServiceImpl(BusinessConditionDao businessConditionDao) {
        this.businessConditionDao = businessConditionDao;
    }

    public BusinessConditionVO getBusinessCondition(String beginDateStr, String endDateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;
        // 收入类
        int salesRevenue = businessConditionDao.getSalesRevenue(beginDate, endDate); // 销售收入
        int commodityOverflow = 0; // 商品报溢收入
        int costChange = 0; // 成本调价收入
        int gap = businessConditionDao.getGap(beginDate, endDate); // 进货退货差价
        int voucher = businessConditionDao.getVoucher(beginDate, endDate); // 代金券与实际收款差额收入
        int discount = businessConditionDao.getDiscount(beginDate, endDate); // 收入折让
        int totalRevenue = salesRevenue + commodityOverflow + gap + voucher - discount; // 总收入
        // 支出类
        int salesCost = businessConditionDao.getSalesCost(beginDate, endDate); // 销售成本
        int commodityLossCost = 0; // 商品报损支出
        int commodityGiveCost = 0; // 商品赠出支持
        int laborCost = businessConditionDao.getLaborCost(beginDate, endDate);  // 人力成本
        int totalCost = salesCost + commodityLossCost + commodityGiveCost + laborCost; // 总支出
        int profit = totalRevenue - totalCost;  // 利润

        return new BusinessConditionVO(salesRevenue, commodityOverflow, costChange, gap, voucher, discount, totalRevenue,
                -salesCost, -commodityLossCost, -commodityGiveCost, -laborCost, -totalCost,
                profit);
    }
}
