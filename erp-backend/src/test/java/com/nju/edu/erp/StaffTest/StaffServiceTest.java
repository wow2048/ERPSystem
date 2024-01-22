package com.nju.edu.erp.StaffTest;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.service.Impl.StaffServiceImpl;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {
    @InjectMocks
    StaffServiceImpl staffService1;

    @Mock
    StaffDao staffDao1;

    @Test
    public void findStaffByIdTest(){
        StaffPO staffPO1 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
        Mockito.when(staffDao1.findOneById(111)).thenReturn(staffPO1);

        StaffPO staffPO2 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
        Assert.assertEquals(staffPO2,staffService1.findStaffById(staffPO2.getId()));
    }

    @Test
    public void getAllStaffTest(){
        StaffPO staffPO1 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
        StaffPO staffPO2 = StaffPO.builder()
                .id(222)
                .name("test2")
                .account("0000-0000")
                .gender("男")
                .type("进货销售人员")
                .phone("1")
                .build();
        List<StaffPO> staffPOList = new ArrayList<>();
        staffPOList.add(staffPO1);
        staffPOList.add(staffPO2);
        Mockito.when(staffDao1.findAllById()).thenReturn(staffPOList);

        StaffPO staffPO3 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
        StaffPO staffPO4 = StaffPO.builder()
                .id(222)
                .name("test2")
                .account("0000-0000")
                .gender("男")
                .type("进货销售人员")
                .phone("1")
                .build();
        List<StaffPO> staffList = new ArrayList<>();
        staffList.add(staffPO3);
        staffList.add(staffPO4);
        Assert.assertEquals(staffList,staffService1.getStaffsById());
    }
}
