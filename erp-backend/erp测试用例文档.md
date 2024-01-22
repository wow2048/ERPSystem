# erp测试用例文档

### 变更记录

| 负责人 | 日期     | 修改部分                               | 版本号 |
| ------ | -------- | -------------------------------------- | ------ |
| 何富森 | 2022.7.9 | 添加销售策略单元测试用例和集成测试用例 | v1.0   |
| 陈佳耀 | 2022.7.9 | 添加查看销售明细表的测试用例           | v1.1   |
| 戎润祥 | 2022.7.9 | 添加查看员工信息的测试用例             | v1.2   |
## 目录
[TOC]

## 1. 单元测试

**单元测试编号：1**

测试方法：getAllSaleStrategyTest

测试描述：对客户级别销售策略实现类进行getAllSaleStrategy单元测试

数据输入/数据准备：如下面代码所示

```Java
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
```

预期输出：如以下代码所示

```Java
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
```

<b>单元测试编号：2</b>

测试方法：findStaffById

测试描述：对根据id获取员工信息进行测试

数据输入/数据准备：如下面代码所示

```java
StaffPO staffPO1 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
```

预期输出：如下代码所示

```java
StaffPO staffPO2 = StaffPO.builder()
                .id(111)
                .name("test")
                .account("0000-0000")
                .gender("男")
                .type("库存管理人员")
                .phone("0")
                .build();
        Assert.assertEquals(staffPO2,staffService1.findStaffById(staffPO2.getId()));
```

<b>单元测试编号：3</b>

测试方法：getAllStaff

测试描述：对获取所有员工的方法进行测试

数据输入/数据准备：如下面代码所示

```java
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
```

预期输出：如下面代码所示

```java
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
```

**单元测试编号：4**

测试方法：saleDetailsCounting

测试描述：对获取销售明细表的方法进行测试

数据输入/数据准备：如下面代码所示

```Java
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
```

预期输出：如以下代码所示

```Java
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
```

## 2. 集成测试

**集成测试编号：1**

测试方法：createSaleStrategyCustomer、createSaleStrategyProduct、createSaleStrategyPrice

测试描述：对销售策略的制定销售策略进行集成测试

数据输入/数据准备：如下面代码所示

```Java
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
```

预期输出：如以下代码所示

```Jaav
// 客户级别销售策略预期输出		
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
// 组合商品销售策略预期输出
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

// 不同总价销售策略预期输出		
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
```

**集成测试编号：2**

测试方法：createCheck、findCheckinLastMonthById

测试描述：对销售策略的制定销售策略进行集成测试

数据输入/数据准备：如下面代码所示

```java
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
```

预期输出：如下面代码所示

```java
Assertions.assertEquals(Integer.valueOf(2), staffService1.findCheckInLastMonthById(111));
Assertions.assertEquals(Integer.valueOf(1), staffService1.findCheckInLastMonthById(222));
```

**集成测试编号：3**

测试方法：SalarySheetServiceImpl.makeSheet、SalarySheetServiceImpl.getSheetById、StaffService.createStaff、StaffService.updateStaff、StaffService.createCheck

测试描述：对工资单的制定进行集成测试

数据输入/数据准备：

覆盖了表驱动所有的区间

具体如下所示

```
SalarySheetVO salarySheetVO = SalarySheetVO.builder()
            .staffId(514)
            .bankId(1)
            .operator("caiwu")
            .build();
    StaffVO staff = StaffVO.builder()
            .id(514)
            .name("testPerson")
            .account("1919-0000")
            .gender("男")
            .type("库存管理人员")
            .phone("0")
            .build();

    staffService.createStaff(staff);

    //20天打了15天卡
    for (int i = 1; i <= 15; i++){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, i);
        cal.add(Calendar.MONTH, -1);
        CheckInVO checkInVO = CheckInVO.builder()
                .id(514)
                .checkRecord(cal.getTime())
                .build();
        staffService.createCheck(checkInVO);
    }

    //库存管理人员岗位工资为3200，以下为不同区间的工资
    double[] salaryList = {1600, 4000, 8800, 24800, 40800, 56800, 80800, 116800};
    double[] payableSalaryList = {3600, 5400, 9000, 21000, 33000, 45000, 63000, 90000};
    double[] taxList = {0, 12, 190, 1760, 4340, 7590, 13140, 23090};
    for (int i = 0; i < 8; i++){
        double payableSalary, tax, actualSalary;

        staff.setSalary(BigDecimal.valueOf(salaryList[i]));
        StaffPO staffPO = new StaffPO();
        BeanUtils.copyProperties(staff, staffPO);
        staffService.updateStaff(staffPO);

        String id = salarySheetService.makeSheet(salarySheetVO);

        SalarySheetVO newVO = (SalarySheetVO) salarySheetService.getSheetById(id);
        payableSalary = newVO.getPayableSalary().doubleValue();
        tax = newVO.getTax().doubleValue();
        actualSalary = newVO.getActualSalary().doubleValue();
    }
}
```

预期输入：如下所示

        Assertions.assertEquals(payableSalaryList[i], payableSalary);
        Assertions.assertEquals(taxList[i], tax);
        Assertions.assertEquals(payableSalaryList[i]-taxList[i], actualSalary);

**集成测试编号：4**

测试方法：PaymentSheetServiceImpl.makeSheet、PaymentSheetDao.getLatest、PaymentSheetServImpl.getSheetById

测试描述：对付款单的制定进行集成测试

数据输入/数据准备：如下图所示

```
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

    }
}
```

预期输入：如下所示

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
**集成测试编号：5**

测试方法：ReceiptSheetServiceImpl.makeSheet、ReceiptSheetDao.getLatest、ReceiptSheetServImpl.getSheetById

测试描述：对收款单的制定进行集成测试

数据输入/数据准备：如下图所示

```
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

    }
}
```

预期输入：如下所示

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
## 3. 系统测试