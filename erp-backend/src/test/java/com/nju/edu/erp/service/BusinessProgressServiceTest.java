package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class BusinessProgressServiceTest {

    @Autowired
    BusinessProgressService businessProgressService;

    @Test
    @Transactional
    @Rollback
    public void SaleSheetTest() {
        List<SaleSheetVO> vos =
                businessProgressService.getSaleSheet("2022-05-04 00:00:00",
                        "2022-06-01 00:00:00",
                        "lxs","xiaoshoujingli");
        Assertions.assertEquals(6, vos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void SaleReturnsSheetTest() {
        List<SaleReturnsSheetVO> vos =
                businessProgressService.getSaleReturnsSheet("2022-05-04 00:00:00",
                        "2022-07-28 00:00:00",
                        "lxs","xiaoshoujingli");
        Assertions.assertEquals(4, vos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void PurchaseSheetTest() {
        List<PurchaseSheetVO> vos =
                businessProgressService.getPurchaseSheet("2022-05-04 00:00:00",
                        "2022-07-28 00:00:00",
                        "yzh","xiaoshoujingli");
        Assertions.assertEquals(6, vos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void PurchaseReturnsSheetTest() {
        List<PurchaseReturnsSheetVO> vos =
                businessProgressService.getPurchaseReturnsSheet("2022-05-04 00:00:00",
                        "2022-07-28 00:00:00",
                        "yzh","xiaoshoujingli");
        Assertions.assertEquals(7, vos.size());
    }

}
