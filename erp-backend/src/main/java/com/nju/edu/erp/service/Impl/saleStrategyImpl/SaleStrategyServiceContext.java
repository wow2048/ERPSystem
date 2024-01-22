package com.nju.edu.erp.service.Impl.saleStrategyImpl;

import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.SaleStrategyService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SaleStrategyServiceContext {
    private final Map<String, SaleStrategyService> saleStrategyMap = new ConcurrentHashMap<>();

    public SaleStrategyServiceContext(Map<String, SaleStrategyService> strategyMap) {
        this.saleStrategyMap.clear();
        strategyMap.forEach((k, v)-> this.saleStrategyMap.put(k, v));
    }

    /**
     * 根据销售策略类型获取相关的销售策略服务实现
     * @param saleStrategyVO 销售策略相关信息
     * @return 具体的销售策略实现
     */
    public SaleStrategyService getResource(SaleStrategyVO saleStrategyVO){
        return saleStrategyMap.get(saleStrategyVO.getSaleStrategyType());
    }

    /**
     * 根据销售策略类型获取相关的销售策略服务实现
     * @param saleStrategyType 销售策略类型
     * @return 具体的销售策略实现
     */
    public SaleStrategyService getResource(String saleStrategyType){
        return saleStrategyMap.get(saleStrategyType);
    }
}
