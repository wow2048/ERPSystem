package com.nju.edu.erp.service.Impl.saleStrategyImpl;

import com.nju.edu.erp.dao.SaleStrategyDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.SaleStrategyGiftPO;
import com.nju.edu.erp.model.po.SaleStrategyPricePO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyGiftVO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.SaleStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("Price")
public class SaleStrategyPriceImpl implements SaleStrategyService {

    SaleStrategyDao saleStrategyDao;

    @Autowired
    public SaleStrategyPriceImpl(SaleStrategyDao saleStrategyDao){
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

        // 代金券为空时，默认输入为0
        if(saleStrategyVO.getVoucherAmountPrice() == null){
            saleStrategyVO.setVoucherAmountPrice(BigDecimal.ZERO);
        }
        else if(saleStrategyVO.getVoucherAmountPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new MyServiceException("F0005", "代金券金额不能为负数");
        }

        List<SaleStrategyGiftVO> giftsTmp = saleStrategyVO.getGiftsPrice();
        if(giftsTmp.size() == 1 && giftsTmp.get(0).getPid().equals("")){
            saleStrategyVO.setGiftsPrice(null);
        }
        // 检查赠品列表中的赠品相关信息是否有非法输入
        if(saleStrategyVO.getGiftsPrice() != null) {
            for (SaleStrategyGiftVO vo : saleStrategyVO.getGiftsPrice()) {
                if (vo.getQuantity() <= 0) {
                    throw new MyServiceException("F0006", "商品数量必须为正数");
                }

                if (vo.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new MyServiceException("F0007", "商品单价必须为正数");
                }
            }
        }

        SaleStrategyPricePO saleStrategyPricePO = new SaleStrategyPricePO();
        // 确定面向总价的销售策略的相关基本信息
        BeanUtils.copyProperties(saleStrategyVO, saleStrategyPricePO);
        saleStrategyPricePO.setId(getId());

        // 构造赠品列表
        List<SaleStrategyGiftPO> gifts = new ArrayList<>();
        if(saleStrategyVO.getGiftsPrice() != null) {
            for (SaleStrategyGiftVO vo : saleStrategyVO.getGiftsPrice()) {
                SaleStrategyGiftPO po = new SaleStrategyGiftPO();
                BeanUtils.copyProperties(vo, po);
                po.setStrategyId(saleStrategyVO.getId());

                po.setStrategyId(saleStrategyPricePO.getId());
                po.setSubTotal(po.getUnitPrice().multiply(BigDecimal.valueOf(po.getQuantity())));
                gifts.add(po);
            }
            saleStrategyDao.saveGifts(gifts);
        }


        saleStrategyDao.saveSaleStrategyPrice(saleStrategyPricePO);

    }

    /**
     * 获取所有的销售策略
     * @return List<SaleStrategyVO></> 销售策略列表
     */
    @Override
    public List<SaleStrategyVO> getSaleStrategy(){
        List<SaleStrategyVO> saleStrategyVOS = new ArrayList<>();
        List<SaleStrategyPricePO> all = saleStrategyDao.findSaleStrategyPrice();

        for(SaleStrategyPricePO saleStrategyPricePO : all) {
            SaleStrategyVO saleStrategyVO = new SaleStrategyVO();
            BeanUtils.copyProperties(saleStrategyPricePO, saleStrategyVO);

            // 构造赠品列表
            List<SaleStrategyGiftVO> giftVOS = new ArrayList<>();
            List<SaleStrategyGiftPO> giftPOS = saleStrategyDao.findGiftsByStrategyId(saleStrategyPricePO.getId());
            if(giftPOS != null) {
                for (SaleStrategyGiftPO giftPO : giftPOS) {
                    SaleStrategyGiftVO giftVO = new SaleStrategyGiftVO();
                    BeanUtils.copyProperties(giftPO, giftVO);
                    giftVOS.add(giftVO);
                }
                saleStrategyVO.setGiftsPrice(giftVOS);
            }

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
