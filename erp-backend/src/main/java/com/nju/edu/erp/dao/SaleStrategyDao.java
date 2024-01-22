package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SaleStrategyCustomerPO;
import com.nju.edu.erp.model.po.SaleStrategyGiftPO;
import com.nju.edu.erp.model.po.SaleStrategyPricePO;
import com.nju.edu.erp.model.po.SaleStrategyProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleStrategyDao {

    /**
     * 保存销售策略中的有关商品列表
     * @param gifts 商品列表
     */
    void saveGifts(List<SaleStrategyGiftPO> gifts);

    /**
     * 保存客户级别销售策略
     * @param saleStrategyCustomerPO 客户级别销售策略
     */
    void saveSaleStrategyCustomer(SaleStrategyCustomerPO saleStrategyCustomerPO);

    /**
     * 保存组合商品的销售策略
     * @param saleStrategyProductPO 组合商品的销售策略
     */
    void saveSaleStrategyProduct(SaleStrategyProductPO saleStrategyProductPO);

    /**
     * 保存不同总价的销售策略
     * @param saleStrategyPricePO 不同总价的销售策略
     */
    void saveSaleStrategyPrice(SaleStrategyPricePO saleStrategyPricePO);

    /**
     * 获取所有客户级别的销售策略
     * @return 销售策略列表
     */
    List<SaleStrategyCustomerPO> findSaleStrategyCustomer();

    /**
     * 获取所有组合商品的销售策略
     * @return 销售策略列表
     */
    List<SaleStrategyProductPO> findSaleStrategyProduct();

    /**
     * 获取所有不同总价的销售策略
     * @return 销售策略列表
     */
    List<SaleStrategyPricePO> findSaleStrategyPrice();

    /**
     * 根据销售策略id获取相应的商品列表
     * @param saleStrategyId 销售策略id
     * @return 商品列表
     */
    List<SaleStrategyGiftPO> findGiftsByStrategyId(Integer saleStrategyId);

}
