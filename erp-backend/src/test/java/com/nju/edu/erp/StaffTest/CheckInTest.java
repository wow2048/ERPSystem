package com.nju.edu.erp.StaffTest;


import com.nju.edu.erp.model.vo.CheckInVO;
import com.nju.edu.erp.service.Impl.StaffServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class CheckInTest {

    @Autowired
    StaffServiceImpl staffService1;

    @Test
    @Transactional
    @Rollback(value = true)
    public void findCheckInLastMonthByIdTest(){
        CheckInVO checkin1 = CheckInVO.builder()
                .id(111)
                .checkRecord(new Date(2022-1900, Calendar.JUNE,3))
                .build();
        CheckInVO checkin2 = CheckInVO.builder()
                .id(111)
                .checkRecord(new Date(2022-1900, Calendar.JUNE,4))
                .build();
        CheckInVO checkin3 = CheckInVO.builder()
                .id(222)
                .checkRecord(new Date(2022-1900, Calendar.JUNE,4))
                .build();

        staffService1.createCheck(checkin1);
        staffService1.createCheck(checkin2);
        staffService1.createCheck(checkin3);

        Assertions.assertEquals(Integer.valueOf(2), staffService1.findCheckInLastMonthById(111));
        Assertions.assertEquals(Integer.valueOf(1), staffService1.findCheckInLastMonthById(222));

    }
}
