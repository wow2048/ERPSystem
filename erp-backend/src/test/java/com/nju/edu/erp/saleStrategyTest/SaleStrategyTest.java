package com.nju.edu.erp.saleStrategyTest;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyGiftVO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.Impl.saleStrategyImpl.SaleStrategyServiceContext;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.service.SaleStrategyService;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SaleStrategyTest {

    SaleStrategyService saleStrategyService;

    @Autowired
    SaleStrategyDao saleStrategyDao;

    @Autowired
    SaleStrategyServiceContext saleStrategyServiceContext;


    @Test
    @Transactional
    @Rollback(value = true)
    public void createSaleStrategyCustomer() {
        // 客户级别销售策略
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Date endDate = new Date();
        try {
            beginDate = dateFormat1.parse("2022-06-01");
            endDate = dateFormat1.parse("2022-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<SaleStrategyGiftVO> giftsCustomer = new ArrayList<>();
        giftsCustomer.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400000")
                .name("TestProduct0")
                .quantity(50)
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        giftsCustomer.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400001")
                .name("TestProduct1")
                .quantity(60)
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleStrategyVO saleStrategyVO = SaleStrategyVO.builder()
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .level(3)
                .discount(BigDecimal.valueOf(0.5))
                .voucherAmountCustomer(BigDecimal.valueOf(200))
                .giftsCustomer(giftsCustomer)
                .build();
        Integer id = saleStrategyDao.findSaleStrategyCustomer().size()
                        +saleStrategyDao.findSaleStrategyPrice().size()
                            +saleStrategyDao.findSaleStrategyProduct().size()+1;

        saleStrategyService = saleStrategyServiceContext.getResource("Customer");
        saleStrategyService.createSaleStrategy(saleStrategyVO);

        List<SaleStrategyCustomerPO> pos = saleStrategyDao.findSaleStrategyCustomer();
        SaleStrategyCustomerPO latestStrategy = pos.get(pos.size()-1);

        Assertions.assertEquals(id, latestStrategy.getId());
        Assertions.assertEquals(beginDate, latestStrategy.getBeginTime());
        Assertions.assertEquals(endDate, latestStrategy.getEndTime());
        Assertions.assertEquals(3, latestStrategy.getLevel());
        Assertions.assertEquals(0, latestStrategy.getDiscount().compareTo(BigDecimal.valueOf(0.5)));
        Assertions.assertEquals(0, latestStrategy.getVoucherAmountCustomer().compareTo(BigDecimal.valueOf(200.00)));

        List<SaleStrategyGiftPO> gifts = saleStrategyDao.findGiftsByStrategyId(latestStrategy.getId());
        Assertions.assertEquals(2, gifts.size());

        Assertions.assertEquals("0000000000400000", gifts.get(0).getPid());
        Assertions.assertEquals(0, gifts.get(0).getSubTotal().compareTo(BigDecimal.valueOf(160000.00)));
        Assertions.assertEquals("0000000000400001", gifts.get(1).getPid());
        Assertions.assertEquals(0, gifts.get(1).getSubTotal().compareTo(BigDecimal.valueOf(252000.00)));


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createSaleStrategyProduct() {
        // 组合商品销售策略
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Date endDate = new Date();
        try {
            beginDate = dateFormat1.parse("2022-06-01");
            endDate = dateFormat1.parse("2022-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<SaleStrategyGiftVO> specialProducts = new ArrayList<>();
        specialProducts.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400000")
                .name("TestProduct0")
                .quantity(50)
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        specialProducts.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400001")
                .name("TestProduct1")
                .quantity(60)
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleStrategyVO saleStrategyVO = SaleStrategyVO.builder()
                .saleStrategyType("Product")
                .beginTime(beginDate)
                .endTime(endDate)
                .specialPrice(BigDecimal.valueOf(260000.00))
                .specialProducts(specialProducts)
                .build();
        Integer id = saleStrategyDao.findSaleStrategyCustomer().size()
                +saleStrategyDao.findSaleStrategyPrice().size()
                +saleStrategyDao.findSaleStrategyProduct().size()+1;

        saleStrategyService = saleStrategyServiceContext.getResource("Product");
        saleStrategyService.createSaleStrategy(saleStrategyVO);

        List<SaleStrategyProductPO> pos = saleStrategyDao.findSaleStrategyProduct();
        SaleStrategyProductPO latestStrategy = pos.get(pos.size()-1);

        Assertions.assertEquals(id, latestStrategy.getId());
        Assertions.assertEquals(beginDate, latestStrategy.getBeginTime());
        Assertions.assertEquals(endDate, latestStrategy.getEndTime());
        Assertions.assertEquals(0, latestStrategy.getSpecialPrice().compareTo(BigDecimal.valueOf(260000.00)));

        List<SaleStrategyGiftPO> sProducts = saleStrategyDao.findGiftsByStrategyId(latestStrategy.getId());
        Assertions.assertEquals(2, sProducts.size());

        Assertions.assertEquals("0000000000400000", sProducts.get(0).getPid());
        Assertions.assertEquals(0, sProducts.get(0).getSubTotal().compareTo(BigDecimal.valueOf(160000.00)));
        Assertions.assertEquals("0000000000400001", sProducts.get(1).getPid());
        Assertions.assertEquals(0, sProducts.get(1).getSubTotal().compareTo(BigDecimal.valueOf(252000.00)));

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createSaleStrategyPrice() {
        // 不同总价销售策略
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Date endDate = new Date();
        try {
            beginDate = dateFormat1.parse("2022-06-01");
            endDate = dateFormat1.parse("2022-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<SaleStrategyGiftVO> giftsPrice = new ArrayList<>();
        giftsPrice.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400000")
                .name("TestProduct0")
                .quantity(50)
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        giftsPrice.add(SaleStrategyGiftVO.builder()
                .pid("0000000000400001")
                .name("TestProduct1")
                .quantity(60)
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleStrategyVO saleStrategyVO = SaleStrategyVO.builder()
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .triggerPrice(BigDecimal.valueOf(3000))
                .voucherAmountPrice(BigDecimal.valueOf(200))
                .giftsPrice(giftsPrice)
                .build();
        Integer id = saleStrategyDao.findSaleStrategyCustomer().size()
                +saleStrategyDao.findSaleStrategyPrice().size()
                +saleStrategyDao.findSaleStrategyProduct().size()+1;

        saleStrategyService = saleStrategyServiceContext.getResource("Price");
        saleStrategyService.createSaleStrategy(saleStrategyVO);

        List<SaleStrategyPricePO> pos = saleStrategyDao.findSaleStrategyPrice();
        SaleStrategyPricePO latestStrategy = pos.get(pos.size()-1);

        Assertions.assertEquals(id, latestStrategy.getId());
        Assertions.assertEquals(beginDate, latestStrategy.getBeginTime());
        Assertions.assertEquals(endDate, latestStrategy.getEndTime());
        Assertions.assertEquals(0, latestStrategy.getTriggerPrice().compareTo(BigDecimal.valueOf(3000)));
        Assertions.assertEquals(0, latestStrategy.getVoucherAmountPrice().compareTo(BigDecimal.valueOf(200.00)));

        List<SaleStrategyGiftPO> gifts = saleStrategyDao.findGiftsByStrategyId(latestStrategy.getId());
        Assertions.assertEquals(2, gifts.size());

        Assertions.assertEquals("0000000000400000", gifts.get(0).getPid());
        Assertions.assertEquals(0, gifts.get(0).getSubTotal().compareTo(BigDecimal.valueOf(160000.00)));
        Assertions.assertEquals("0000000000400001", gifts.get(1).getPid());
        Assertions.assertEquals(0, gifts.get(1).getSubTotal().compareTo(BigDecimal.valueOf(252000.00)));

    }

}
