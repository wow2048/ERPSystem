package com.nju.edu.erp.service.Impl.saleStrategyImpl;

import com.nju.edu.erp.dao.SaleStrategyDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.SaleStrategyGiftPO;
import com.nju.edu.erp.model.po.SaleStrategyProductPO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyGiftVO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.SaleStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("Product")
public class SaleStrategyProductImpl implements SaleStrategyService {

    SaleStrategyDao saleStrategyDao;

    @Autowired
    public SaleStrategyProductImpl(SaleStrategyDao saleStrategyDao){
        this.saleStrategyDao = saleStrategyDao;
    }

    /**
     * 创建一个销售策略
     * @param saleStrategyVO 销售策略相关信息
     */
    @Override
    public void createSaleStrategy(SaleStrategyVO saleStrategyVO){

        if(saleStrategyVO.getBeginTime() == null){
            throw new MyServiceException("F0000", "销售策略起始时间不能为空");
        }

        if(saleStrategyVO.getEndTime() == null){
            throw new MyServiceException("F0001", "销售策略结束时间不能为空");
        }

        if(saleStrategyVO.getSpecialPrice() == null){
            throw new MyServiceException("F0008", "优惠价格不能为空");
        }
        else if(saleStrategyVO.getSpecialPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new MyServiceException("F0009", "优惠价格必须为正数");
        }

        List<SaleStrategyGiftVO> giftsTmp = saleStrategyVO.getSpecialProducts();
        if(giftsTmp.size() == 1 && giftsTmp.get(0).getPid().equals("")){
            throw new MyServiceException("F0010", "组合商品列表不能为空");
        }

        // 检查组合商品列表中的商品相关信息是否有非法输入
        for(SaleStrategyGiftVO vo: saleStrategyVO.getSpecialProducts()){
            if(vo.getQuantity() <= 0){
                throw new MyServiceException("F0006", "商品数量必须为正数");
            }

            if(vo.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0){
                throw new MyServiceException("F0007", "商品单价必须为正数");
            }
        }

        SaleStrategyProductPO saleStrategyProductPO = new SaleStrategyProductPO();
        // 确定组合商品销售策略的相关基本信息
        BeanUtils.copyProperties(saleStrategyVO, saleStrategyProductPO);
        saleStrategyProductPO.setId(getId());

        BigDecimal total = BigDecimal.ZERO;
        List<SaleStrategyGiftPO> products = new ArrayList<>();
        if(saleStrategyVO.getSpecialProducts() != null) {
            for (SaleStrategyGiftVO vo : saleStrategyVO.getSpecialProducts()) {
                SaleStrategyGiftPO po = new SaleStrategyGiftPO();
                BeanUtils.copyProperties(vo, po);
                po.setStrategyId(saleStrategyProductPO.getId());

                //计算各项商品总价和所有商品总价
                po.setSubTotal(po.getUnitPrice().multiply(BigDecimal.valueOf(po.getQuantity())));
                total = total.add(po.getSubTotal());
                products.add(po);
            }
            saleStrategyProductPO.setTotalPrice(total);
            saleStrategyDao.saveGifts(products);
        }


        saleStrategyDao.saveSaleStrategyProduct(saleStrategyProductPO);
    }

    /**
     * 获取所有的销售策略
     * @return List<SaleStrategyVO></> 销售策略列表
     */
    @Override
    public List<SaleStrategyVO> getSaleStrategy(){
        List<SaleStrategyVO> saleStrategyVOS = new ArrayList<>();
        List<SaleStrategyProductPO> all = saleStrategyDao.findSaleStrategyProduct();

        for(SaleStrategyProductPO saleStrategyProductPO : all) {
            SaleStrategyVO saleStrategyVO = new SaleStrategyVO();
            BeanUtils.copyProperties(saleStrategyProductPO, saleStrategyVO);

            // 构造赠品列表
            List<SaleStrategyGiftVO> productVOS = new ArrayList<>();
            List<SaleStrategyGiftPO> productPOS = saleStrategyDao.findGiftsByStrategyId(saleStrategyProductPO.getId());
            for(SaleStrategyGiftPO productPO : productPOS) {
                SaleStrategyGiftVO productVO = new SaleStrategyGiftVO();
                BeanUtils.copyProperties(productPO, productVO);
                productVOS.add(productVO);
            }
            saleStrategyVO.setSpecialProducts(productVOS);

            saleStrategyVOS.add(saleStrategyVO);
        }
        return saleStrategyVOS;
    }

    /**
     * 构建自增id
     * @return id
     */
    private int getId(){
        return saleStrategyDao.findSaleStrategyCustomer().size()
                + saleStrategyDao.findSaleStrategyProduct().size()
                + saleStrategyDao.findSaleStrategyPrice().size()
                + 1;
    }
}
