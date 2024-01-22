package com.nju.edu.erp.saleStrategyTest;

import com.nju.edu.erp.dao.SaleStrategyDao;
import com.nju.edu.erp.model.po.SaleStrategyCustomerPO;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.Impl.saleStrategyImpl.SaleStrategyCustomerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class SaleStrategyUnitTest {
    @InjectMocks
    SaleStrategyCustomerImpl saleStrategyCustomer;

    @Mock
    private SaleStrategyDao saleStrategyDao;

    @Test
    public void getAllSaleStrategyTest(){
        // 客户级别销售策略getAllSaleStrategy单元测试
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Date endDate = new Date();
        try {
            beginDate = dateFormat1.parse("2022-06-01");
            endDate = dateFormat1.parse("2022-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SaleStrategyCustomerPO saleStrategyCustomerPO1 = SaleStrategyCustomerPO.builder()
                .id(1)
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .level(4)
                .discount(BigDecimal.valueOf(0.5))
                .voucherAmountCustomer(BigDecimal.valueOf(200))
                .build();
        SaleStrategyCustomerPO saleStrategyCustomerPO2 = SaleStrategyCustomerPO.builder()
                .id(2)
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .level(3)
                .discount(BigDecimal.valueOf(0.5))
                .voucherAmountCustomer(BigDecimal.valueOf(200))
                .build();

        List<SaleStrategyCustomerPO> saleStrategyCustomerPOList = new ArrayList<>();
        saleStrategyCustomerPOList.add(saleStrategyCustomerPO1);
        saleStrategyCustomerPOList.add(saleStrategyCustomerPO2);
        Mockito.when(saleStrategyDao.findSaleStrategyCustomer()).thenReturn(saleStrategyCustomerPOList);

        SaleStrategyVO saleStrategyVO1 = SaleStrategyVO.builder()
                .id(1)
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .level(4)
                .discount(BigDecimal.valueOf(0.5))
                .voucherAmountCustomer(BigDecimal.valueOf(200))
                .giftsCustomer(new ArrayList<>())
                .build();
        SaleStrategyVO saleStrategyVO2 = SaleStrategyVO.builder()
                .id(2)
                .saleStrategyType("Customer")
                .beginTime(beginDate)
                .endTime(endDate)
                .level(3)
                .discount(BigDecimal.valueOf(0.5))
                .voucherAmountCustomer(BigDecimal.valueOf(200))
                .giftsCustomer(new ArrayList<>())
                .build();

        List<SaleStrategyVO> saleStrategyVOS = new ArrayList<>();
        saleStrategyVOS.add(saleStrategyVO1);
        saleStrategyVOS.add(saleStrategyVO2);
        Assert.assertEquals(saleStrategyVOS,saleStrategyCustomer.getSaleStrategy());
    }
}
