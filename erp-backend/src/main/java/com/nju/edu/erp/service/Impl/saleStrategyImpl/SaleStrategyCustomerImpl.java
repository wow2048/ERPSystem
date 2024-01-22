package com.nju.edu.erp.service.Impl.saleStrategyImpl;

import com.nju.edu.erp.dao.SaleStrategyDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.SaleStrategyCustomerPO;
import com.nju.edu.erp.model.po.SaleStrategyGiftPO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyGiftVO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.SaleStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("Customer")
public class SaleStrategyCustomerImpl implements SaleStrategyService {

    SaleStrategyDao saleStrategyDao;

    @Autowired
    public SaleStrategyCustomerImpl(SaleStrategyDao saleStrategyDao){
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


        if(saleStrategyVO.getLevel() == null){
            throw new MyServiceException("F0002", "客户级别不能为空");
        }
        // 检查客户级别是否在合法范围内
        else if(saleStrategyVO.getLevel() < 1 || saleStrategyVO.getLevel() > 5){
            throw new MyServiceException("F0003", "客户级别要在1到5之间");
        }

        // 折让为空时，默认为1，即没有任何优惠
        if(saleStrategyVO.getDiscount() == null){
            saleStrategyVO.setDiscount(BigDecimal.ONE);
        }
        // 检查这让大小是否在合法范围内（0~1）
        else if(saleStrategyVO.getDiscount().compareTo(BigDecimal.ZERO) < 0
                || saleStrategyVO.getDiscount().compareTo(BigDecimal.ONE) > 0){
            throw new MyServiceException("F0004", "折让大小需在0到1之间");
        }

        // 代金券为空时，默认输入为0
        if(saleStrategyVO.getVoucherAmountCustomer() == null){
            saleStrategyVO.setVoucherAmountCustomer(BigDecimal.ZERO);
        }
        else if(saleStrategyVO.getVoucherAmountCustomer().compareTo(BigDecimal.ZERO) < 0){
            throw new MyServiceException("F0005", "代金券金额不能为负数");
        }

        List<SaleStrategyGiftVO> giftsTmp = saleStrategyVO.getGiftsCustomer();
        if(giftsTmp.size() == 1 && giftsTmp.get(0).getPid().equals("")){
            saleStrategyVO.setGiftsCustomer(null);
        }
        // 检查赠品列表中的赠品相关信息是否有非法输入
        if(saleStrategyVO.getGiftsCustomer() != null) {
            for (SaleStrategyGiftVO vo : saleStrategyVO.getGiftsCustomer()) {
                if (vo.getQuantity() <= 0) {
                    throw new MyServiceException("F0006", "商品数量必须为正数");
                }

                if (vo.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new MyServiceException("F0007", "商品单价必须为正数");
                }
            }
        }

        SaleStrategyCustomerPO saleStrategyCustomerPO = new SaleStrategyCustomerPO();
        BeanUtils.copyProperties(saleStrategyVO, saleStrategyCustomerPO);
        saleStrategyCustomerPO.setId(getId());

        // 构造赠品列表
        List<SaleStrategyGiftPO> gifts = new ArrayList<>();
        if(saleStrategyVO.getGiftsCustomer() != null) {
            for (SaleStrategyGiftVO vo : saleStrategyVO.getGiftsCustomer()) {
                SaleStrategyGiftPO po = new SaleStrategyGiftPO();
                BeanUtils.copyProperties(vo, po);

                po.setStrategyId(saleStrategyCustomerPO.getId());
                // 计算各项商品总价
                po.setSubTotal(po.getUnitPrice().multiply(BigDecimal.valueOf(po.getQuantity())));
                gifts.add(po);
            }
            saleStrategyDao.saveGifts(gifts);
        }


        saleStrategyDao.saveSaleStrategyCustomer(saleStrategyCustomerPO);
    }

    /**
     * 获取所有的销售策略
     * @return List<SaleStrategyVO></> 销售策略列表
     */
    @Override
    public List<SaleStrategyVO> getSaleStrategy(){
        List<SaleStrategyVO> saleStrategyVOS = new ArrayList<>();
        List<SaleStrategyCustomerPO> all = saleStrategyDao.findSaleStrategyCustomer();

        for(SaleStrategyCustomerPO saleStrategyCustomerPO : all) {
            SaleStrategyVO saleStrategyVO = new SaleStrategyVO();
            BeanUtils.copyProperties(saleStrategyCustomerPO, saleStrategyVO);

            // 构造赠品列表
            List<SaleStrategyGiftVO> giftVOS = new ArrayList<>();
            List<SaleStrategyGiftPO> giftPOS = saleStrategyDao.findGiftsByStrategyId(saleStrategyCustomerPO.getId());
            if(giftPOS != null) {
                for (SaleStrategyGiftPO giftPO : giftPOS) {
                    SaleStrategyGiftVO giftVO = new SaleStrategyGiftVO();
                    BeanUtils.copyProperties(giftPO, giftVO);
                    giftVOS.add(giftVO);
                }
                saleStrategyVO.setGiftsCustomer(giftVOS);
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
