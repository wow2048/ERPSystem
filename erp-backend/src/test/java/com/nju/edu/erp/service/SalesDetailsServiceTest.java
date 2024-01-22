package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.SalesDetailsVO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class SalesDetailsServiceTest {

    @Autowired
    SalesDetailsService salesDetailsService;

    @Test
    @Transactional
    @Rollback(value = true)
    public void SalesDetailsTest() {
        SalesDetailsVO salesDetailsVO = SalesDetailsVO.builder()
                .time("2022-05-23")
                .productName("戴尔电脑")
                .productType("戴尔(DELL)Vostro笔记本电脑5410 123色 戴尔成就3500Vostro1625D")
                .quantity(100)
                .unitPrice(BigDecimal.valueOf(5000))
                .totalPrice(BigDecimal.valueOf(500000))
                .state("审批失败")
                .remark("销售").build();
        List<SalesDetailsVO> salesDetailsList = new ArrayList<>();
        salesDetailsList.add(salesDetailsVO);

        List<SalesDetailsVO> vos =
                salesDetailsService.saleDetailsCounting("2022-05-23 00:00:00",
                        "2022-05-24 00:00:00",
                        "戴尔电脑",
                        "lxs", "xiaoshoujingli");
        for(SalesDetailsVO vo : vos) {
            vo.setUnitPrice(BigDecimal.valueOf(vo.getUnitPrice().intValue()));
            vo.setTotalPrice(BigDecimal.valueOf(vo.getTotalPrice().intValue()));
        }
        Assert.assertEquals(salesDetailsList,vos);
    }

}
