package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.financeSheet.PayAccountVO;
import com.nju.edu.erp.model.vo.financeSheet.PaymentSheetVO;
import com.nju.edu.erp.utils.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PaymentSheetServiceTest {

    @Autowired
    @Qualifier(value = "paymentSheet")
    FinanceSheetService paymentSheetService;

    @Autowired
    PaymentSheetDao paymentSheetDao;

    @Test
    @Transactional
    @Rollback
    public void makeSheetTest(){
        double[] testNum1 = {19.26, 1.14, 20.12};
        double[] testNum2 = {8.17, 5.14, 50.99};
        for (int i = 0; i < 3; i++){
            PaymentSheetPO latest = paymentSheetDao.getLatest();
            String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "FKD");

            PayAccountVO payAccountVO1 = PayAccountVO.builder()
                    .bankId(1)
                    .payMoney(BigDecimal.valueOf(testNum1[i]))
                    .remark("test1")
                    .build();

            PayAccountVO payAccountVO2 = PayAccountVO.builder()
                    .bankId(1)
                    .payMoney(BigDecimal.valueOf(testNum2[i]))
                    .remark("test2")
                    .build();

            List<PayAccountVO> payAccountVOList = new ArrayList<>();
            payAccountVOList.add(payAccountVO1);
            payAccountVOList.add(payAccountVO2);

            PaymentSheetVO paymentSheetVO = PaymentSheetVO.builder()
                    .customerId(1)
                    .operator("caiwu")
                    .payAccountList(payAccountVOList)
                    .build();

            paymentSheetService.makeSheet(paymentSheetVO);

            PaymentSheetVO actualPaymentSheetVO = (PaymentSheetVO) paymentSheetService.getSheetById(id);

            PaymentSheetVO expectedPaymentSheetVO = PaymentSheetVO.builder()
                    .id(id)
                    .customerId(1)
                    .isRead(false)
                    .operator("caiwu")
                    .state(FinanceSheetState.PENDING)
                    .totalAmount(BigDecimal.valueOf((double) Math.round((testNum1[i] + testNum2[i]) * 100) / 100))
                    .createTime(actualPaymentSheetVO.getCreateTime())
                    .payAccountList(payAccountVOList)
                    .build();

            Assertions.assertEquals(expectedPaymentSheetVO.getId(), actualPaymentSheetVO.getId(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedPaymentSheetVO.getTotalAmount().doubleValue(), actualPaymentSheetVO.getTotalAmount().doubleValue(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedPaymentSheetVO.getState(), actualPaymentSheetVO.getState(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedPaymentSheetVO.getPayAccountList(), actualPaymentSheetVO.getPayAccountList(), "test" + i +" is wrong");
        }
    }
}
