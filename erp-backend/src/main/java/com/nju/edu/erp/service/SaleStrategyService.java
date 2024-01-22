package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SaleStrategyService {

    /**
     * 创建一个销售策略
     * @param saleStrategyVO 销售策略相关信息
     */
    void createSaleStrategy(SaleStrategyVO saleStrategyVO);

    /**
     * 获取所有的销售策略
     * @return List<SaleStrategyVO></> 销售策略列表
     */
    List<SaleStrategyVO> getSaleStrategy();

}
