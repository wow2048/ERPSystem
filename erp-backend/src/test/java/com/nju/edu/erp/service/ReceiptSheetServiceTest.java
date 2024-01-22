package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.ReceiptSheetDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.po.ReceiptSheetPO;
import com.nju.edu.erp.model.vo.financeSheet.ReceiveAccountVO;
import com.nju.edu.erp.model.vo.financeSheet.ReceiptSheetVO;
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
public class ReceiptSheetServiceTest {

    @Autowired
    @Qualifier(value = "receiptSheet")
    FinanceSheetService receiptSheetService;

    @Autowired
    ReceiptSheetDao receiptSheetDao;

    @Test
    @Transactional
    @Rollback
    public void makeSheetTest(){
        double[] testNum1 = {19.26, 1.14, 20.12};
        double[] testNum2 = {8.17, 5.14, 50.99};
        for (int i = 0; i < 3; i++){
            ReceiptSheetPO latest = receiptSheetDao.getLatest();
            String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");

            ReceiveAccountVO receiveAccountVO1 = ReceiveAccountVO.builder()
                    .bankId(1)
                    .receiveMoney(BigDecimal.valueOf(testNum1[i]))
                    .remark("test1")
                    .build();

            ReceiveAccountVO receiveAccountVO2 = ReceiveAccountVO.builder()
                    .bankId(1)
                    .receiveMoney(BigDecimal.valueOf(testNum2[i]))
                    .remark("test2")
                    .build();

            List<ReceiveAccountVO> receiveAccountVOList = new ArrayList<>();
            receiveAccountVOList.add(receiveAccountVO1);
            receiveAccountVOList.add(receiveAccountVO2);

            ReceiptSheetVO receiptSheetVO = ReceiptSheetVO.builder()
                    .customerId(1)
                    .operator("caiwu")
                    .receiveAccountList(receiveAccountVOList)
                    .build();

            receiptSheetService.makeSheet(receiptSheetVO);

            ReceiptSheetVO actualReceiptSheetVO = (ReceiptSheetVO) receiptSheetService.getSheetById(id);

            ReceiptSheetVO expectedReceiptSheetVO = ReceiptSheetVO.builder()
                    .id(id)
                    .customerId(1)
                    .isRead(false)
                    .operator("caiwu")
                    .state(FinanceSheetState.PENDING)
                    .totalAmount(BigDecimal.valueOf((double) Math.round((testNum1[i] + testNum2[i]) * 100) / 100))
                    .createTime(actualReceiptSheetVO.getCreateTime())
                    .receiveAccountList(receiveAccountVOList)
                    .build();

            Assertions.assertEquals(expectedReceiptSheetVO.getId(), actualReceiptSheetVO.getId(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedReceiptSheetVO.getTotalAmount().doubleValue(), actualReceiptSheetVO.getTotalAmount().doubleValue(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedReceiptSheetVO.getState(), actualReceiptSheetVO.getState(), "test" + i +" is wrong");
            Assertions.assertEquals(expectedReceiptSheetVO.getReceiveAccountList(), actualReceiptSheetVO.getReceiveAccountList(), "test" + i +" is wrong");
        }
    }
}
