# 详细设计文档

## 小组成员信息

| 姓名   | 学号      |
| ------ | --------- |
| 何富森 | 201250098 |
| 邵浚谦 | 201250099 |
| 陈佳耀 | 201250100 |
| 戎润祥 | 201250081 |

## 变更记录

| 修改人员 | 修改时间  | 修改详情                                                     | 版本号 |
| -------- | --------- | ------------------------------------------------------------ | ------ |
| 戎润祥   | 2022.7.9  | 创建erp系统软件详细设计文档                                  | v0.1   |
| 戎润祥   | 2022.7.9  | 添加结构视角中的Staffbl模块                                  | v0.2   |
| 陈佳耀   | 2022.7.9  | 添加结构视角中的financebl模块部分内容                        | v0.3   |
| 陈佳耀   | 2022.7.9  | 添加结构视角中的financebl模块部分业务逻辑层的动态模型        | v0.4   |
| 邵浚谦   | 2022.7.9  | 添加结构视角中的financebl模块中三种单据的结构、接口规范、动态模型 | v0.5   |
| 戎润祥   | 2022.7.9  | 添加staffbl中的业务逻辑层的动态模型                          | v0.6   |
| 何富森   | 2022.7.10 | 添加managementbl的逻辑层分解内容，增加了SaleStrategy的动态模型 | v0.7   |

## 目录

[TOC]

## 1. 引言

### 1.1 编制目的

本报告详细完成对erp系统的详细设计，达到指导详细设计和开发的目的，同时实现和测试人员及用户的沟通。

本报告面向开发人员、测试人员及最终用户而编写，是了解系统的导航。

### 1.2 词汇表

| 词汇名称 | 词汇含义 | 备注 |
| -------- | -------- | ---- |
|          |          |      |

### 1.3 参考资料

1. IEEE标准
2. 《软件工程于计算（卷二） 软件开发的技术基础》
3. erp系统需求规格说明文档
4. erp系统软件体系结构说明文档给

## 2. 产品概述

参考erp系统的用例文档和软件需求规格说明文档中对产品的概括描述。

## 3. 体系结构设计概述

参考erp系统的概要设计文档中对体系结构设计的概述。

## 4. 结构视角

### 业务逻辑层的分解

业务逻辑层的开发包参见软件体系结构文档

#### 4.1.1 Staffbl模块

##### （1）模块概述

Staffbl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求说明文档。

Staffbl模块的职责及接口参见软件系统结构描述文档。

##### （2）整体结构

根据提及结构的设计，我们将系统分为了前端展示层、Controller层、业务逻辑层、数据层。每一层为了增加灵活性，我们会增加接口。比如展示层和业务逻辑层之间，我们设置了StaffService、SalaryService接口。业务逻辑层和数据层之间设置了Mapper接口。StaffPO是作为员工信息的持久化对象而添加到设计模型中的，CheckInPO是作为员工打卡记录的持久化对象而添加到设计模型中的，CheckkInRecordPO是作为员工打卡次数的持久化对象而添加到设计模型中的。

**Staffbl模块的设计如下图**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/staffp201250081.png)

**各个类的职责**

| 模块             | 职责                                                     |
| ---------------- | -------------------------------------------------------- |
| StaffController  | 负责实现人力资源管理前后端的数据交互                     |
| StaffServiceImpl | 实现员工的增删改查功能                                   |
| StaffVO          | 员工增删改查功能的是实现载体，有员工id、姓名、岗位等信息 |
| StaffService     | 员工增删改查功能的实现载体                               |
| CheckInVO        | 员工打卡功能的实现载体                                   |
| CheckInRecordVO  | 返回员工打卡信息的实现载体                               |
| SalaryService    | 获取员工薪资策略、薪资信息的实现载体                     |

##### （3）模块内部类的接口规范

<table >
<tr>
  <th rowspan="3">StaffService.createStaff</th>
  <td>语法</td>
  <td>public StaffVO createStaff(StaffVO inputVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的inputVO符合规范。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>创建一个新的员工信息。</td>
<tr>
  <th rowspan="3">StaffService.updateStaff</th>
  <td>语法</td>
  <td>public void updateStaff(StaffPO staffPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的StaffPO符合规范。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>更新员工的信息。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findStaffById</th>
  <td>语法</td>
  <td>public StaffPO findStaffById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回相应id的员工信息。</td>
<tr>
 <tr>
  <th rowspan="3">StaffService.deleteById</th>
  <td>语法</td>
  <td>public void deleteById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>删除数据库中相应id的员工信息。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.getStaffById</th>
  <td>语法</td>
  <td>public List<StaffPO> getStaffById();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>按照员工id顺序返回所有员工信息。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.createCheck</th>
  <td>语法</td>
  <td>public CheckInVO createCheck(CheckInVO inputVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的inputVO符合规范。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>创建一个新的员工打卡记录。</td>
<tr>
  <th rowspan="3">StaffService.getCheckInById</th>
  <td>语法</td>
  <td>public List<CheckInPO> getCheckInById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的员工id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回员工的打卡记录。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.getAllLatest</th>
  <td>语法</td>
  <td>public List<CheckInPO> getAllLatest();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回所有员工的最近打卡记录。</td>
<tr>
 <tr>
  <th rowspan="3">StaffService.findIdLatest</th>
  <td>语法</td>
  <td>public CheckInPO findIdLatest(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回当前id的员工的最近打卡记录。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findAllCheckInThisMonth</th>
  <td>语法</td>
  <td>public List<CheckInRecordPO> findAllCheckInThisMonth();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回所有员工的本月打卡次数。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findAllCheckInLastMonth</th>
  <td>语法</td>
  <td>public List<CheckInRecordPO> findAllCheckInLastMonth();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回所有员工的上个月打卡次数。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findCheckInLastMonthById</th>
  <td>语法</td>
  <td>public Integer findCheckInLastMonthById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回当前id的员工的上个月打卡次数。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.deleteCheckInById</th>
  <td>语法</td>
  <td>public void deleteCheckInById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>删除当前id员工的所有打卡记录。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findStrategyByPosition</th>
  <td>语法</td>
  <td>public String findStrategyByPosition(String position);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的position在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回当前岗位的薪资策略。</td>
<tr>
  <th rowspan="3">StaffService.findPositionSalary</th>
  <td>语法</td>
  <td>public Integer findPositionSalary(String position);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的position在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回员工的岗位工资。</td>
<tr>
<tr>
  <th rowspan="3">StaffService.findBaseSalary</th>
  <td>语法</td>
  <td>public Integer findBaseSalary(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的员工id在数据库中存在。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回当前id员工的基本工资。</td>
<tr>
</table>
##### （4）业务逻辑层的动态模型

下图表明了在系统中，Staff.createStaff的相关对象之间的协作。

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/createStaff 201250081.png)

下图表明了在系统中，Staff.deleteStaff的相关对象之间的协作。

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/deleteById201250081.png)

下图表明了在系统中，Staff.updateStaff的相关对象之间的协作。

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/updateStaff 201250081.png)

下图表明了在系统中，Staff.addCheckin的相关对象之间的协作。

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/createCheckin201250081.png)

#### 4.1.2 financebl模块

##### （1）模块概述

financebl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求说明文档。

financebl模块的职责及接口参见软件系统结构描述文档。

##### （2）整体结构

根据提及结构的设计，我们将系统分为了前端展示层、Controller层、业务逻辑层、数据层。

我们将financebl模块分解成若干功能，以下是各功能的包图和类的职责

**AccountService**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-Account结构图.jpg)

**各个类的职责**

| 模块               | 职责                       |
| ------------------ | -------------------------- |
| AccountController  | 负责实现管理账户的数据交互 |
| AccountServiceImpl | 实现管理账户功能           |
| AccountService     | 管理账户的功能接口         |
| AccountPO          | 管理账户的实现载体         |
| AccountVO          | 管理账户的实现载体         |



**SalesDetailsService**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-SalesDetails.png)



**各个类的职责**

| 模块                    | 职责                             |
| ----------------------- | -------------------------------- |
| SalesDetailsController  | 负责实现查看销售明细表的数据交互 |
| SalesDetailsServiceImpl | 实现查看销售明细表功能           |
| SalesDetailsService     | 查看销售明细表的功能接口         |
| SalesDetailsPO          | 查看销售明细表的实现载体         |
| SalesDetailsVO          | 查看销售明细表的实现载体         |



**BusinessProgressService**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-BusinessProgress.png)

**各个类的职责**

| 模块                        | 职责                             |
| --------------------------- | -------------------------------- |
| BusinessProgressController  | 负责实现查看经营历程表的数据交互 |
| BusinessProgressServiceImpl | 实现查看经营历程表功能           |
| BusinessProgressService     | 查看经营历程表的功能接口         |
| SaleSheetVO                 | 销售单载体                       |
| SalesReturnsSheetVO         | 销售退货单载体                   |
| PurchaseSheetVO             | 进货单载体                       |
| PurchaseReturnSheetVO       | 进货退货单载体                   |
| SalarySheetVO               | 工资单载体                       |
| PaymentSheetVO              | 付款单载体                       |
| ReceiptSheetVO              | 收款单载体                       |



**BusinessConditionService**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-BusinessCondition.png)

**各个类的职责**

| 模块                         | 职责                             |
| ---------------------------- | -------------------------------- |
| BusinessConditionController  | 负责实现查看经营情况表的数据交互 |
| BusinessConditionServiceImpl | 实现查看经营情况表功能           |
| BusinessConditionService     | 查看经营情况表的功能接口         |
| SaleSheetVO                  | 查看经营情况表的实现载体         |

**FinanceSheetService**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-FinanceSheet.png)

**各个类的职责**

| 模块                    | 职责                                         |
| ----------------------- | -------------------------------------------- |
| PaymentSheetController  | 负责实现付款单的数据交互                     |
| ReceiptSheetController  | 负责实现收款单的数据交互                     |
| SalarySheetController   | 负责实现工资单的数据交互                     |
| FinanceSheetService     | 三种单据（付款单、收款单、工资单）的功能接口 |
| PaymentSheetServiceImpl | 负责实现付款单的具体功能                     |
| ReceiptSheetServiceImpl | 负责实现收款单的具体功能                     |
| SalarySheetServiceImpl  | 负责实现工资单的具体功能                     |
| FinanceSheetVO          | 三种单据实现载体的父类                       |
| PaymentSheetVO          | 付款单实现载体                               |
| ReceiptSheetVO          | 收款单实现载体                               |
| SalarySheetVO           | 工资单实现载体                               |
| PaymentSheePO           | 付款单数据载体                               |
| ReceiptSheetPO          | 收款单数据载体                               |
| SalarySheetPO           | 工资单数据载体                               |

##### （3）模块内部类的接口规范

**AccountService的接口规范**

<table >
<tr>
  <th rowspan="3">AccountService.createAccount</th>
  <td>语法</td>
  <td>public void createAccount(AccountVO accountVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户输入信息创建一个新的账户</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息生成账户</td>
</tr>
<tr>
  <th rowspan="3">AccountService.updateAccount</th>
  <td>语法</td>
  <td>public void updateAccount(AccountVO accountVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户输入信息更新一个已存在的账户</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统更新选定账户的信息</td>
</tr>    
<tr>
  <th rowspan="3">AccountService.deleteAccountById</th>
  <td>语法</td>
  <td>public void deleteAccountById(Integer accountID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>财务人员删除一个账户</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除选定账户的信息</td>
</tr>       
 <tr>
  <th rowspan="3">AccountService.getAccountsByKeys</th>
  <td>语法</td>
  <td>List&lt;AccountVO&gt; getAccountsByKeys(String keyWords);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取账户名称符合关键词的账户列表</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回符合条件的账户列表</td>
</tr> 
<tr>
  <th rowspan="3">AccountService.getAllAccount</th>
  <td>语法</td>
  <td>List&lt;AccountVO&gt; getAllAccount();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取所有账户的列表</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有账户的列表</td>
</tr>
</table>

**SalesDetailService的接口规范**


<table >
<tr>
  <th rowspan="3">SailsDetails.counting</th>
  <td>语法</td>
  <td>public List&lt;SalesDetailsVO&gt; saleDetailsCounting(String beginDateStr, String endDateStr, String productName, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得一段时间内商品的销售情况</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的商品销售情况的列表</td>
</tr>
</table>
**FianceSheetService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">FinanceSheetService.makeSheet</th>
  <td>语法</td>
  <td>public String makeSheet(FinanceSheetVO vo);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户输入信息创建一张新的单据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息生成单据，并返回单据id</td>
</tr>
<tr>
  <th rowspan="3">FinanceSheetService.getSheetByState</th>
  <td>语法</td>
  <td>public List&lt;FinanceSheetVO&gt; getSheetByState(FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据单据状态请求获取对应的单据信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回符合请求的单据状态的单据列表</td>
</tr>    
<tr>
  <th rowspan="3">FinanceSheetService.approval</th>
  <td>语法</td>
  <td>public void approval(String sheetId, FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>总经理审批时通过或拒绝单据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据审批结果修改单据状态并修改相应的账户、客户信息</td>
</tr>       
 <tr>
  <th rowspan="3">FinanceSheetService.getSheetUnread</th>
  <td>语法</td>
  <td>public List&lt;FinanceSheetVO&gt; getSheetUnread(String operator);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取未读的审批完成的单据的信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的单据列表并将这些单据设为已读</td>
</tr>
<tr>
  <th rowspan="3">FinanceSheetService.getSheetById</th>
  <td>语法</td>
  <td>public FinanceSheetVO getSheetById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据id请求获取单据信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回id为所请求的id的单据列表</td>
</tr>    
</table>

**BusinessProgressService的接口规范**


<table >
<tr>
  <th rowspan="3">BusinessProgress.getSaleSheet</th>
  <td>语法</td>
  <td>public List&lt;SaleSheetVO&gt; getSaleSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得销售单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的销售单列表</td>
</tr>
<tr>
  <th rowspan="3">BusinessProgress.getSaleReturnsSheet</th>
  <td>语法</td>
  <td>public List&lt;SaleReturnsSheetVO&gt; getSaleReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得销售退货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的销售退货单列表</td>
</tr>    
<tr>
  <th rowspan="3">BusinessProgress.getPurchaseSheet</th>
  <td>语法</td>
  <td>public List&lt;PurchaseSheetVO&gt; getPurchaseSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得进货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的进货表</td>
</tr>       
 <tr>
  <th rowspan="3">BusinessProgress.getPurchaseReturnsSheet</th>
  <td>语法</td>
  <td>public List&lt;PurchaseReturnsSheetVO&gt; getPurchaseReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得进货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的进货退货单列表</td>
</tr>
<tr>
  <th rowspan="3">BusinessProgress.getSalarySheet</th>
  <td>语法</td>
  <td>public List&lt;SalarySheetVO&gt; getSalarySheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得工资单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的工资列表</td>
</tr>    
 <tr>
  <th rowspan="3">BusinessProgress.getPaymentSheet</th>
  <td>语法</td>
  <td>public List&lt;PaymentSheetVO&gt; getPaymentSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的付款列表</td>
</tr>    
 <tr>
  <th rowspan="3">BusinessProgress.getReceiptSheet</th>
  <td>语法</td>
  <td>public List&lt;ReceiptSheetVO&gt; getReceiptSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的收款单列表</td>
</tr>
</table>   
**BusinessConditionService的接口规范**


<table >
<tr>
  <th rowspan="3">BusinessConditionService.getBusinessCondition</th>
  <td>语法</td>
  <td>public List&lt;BusinessConditionVO&gt; getBusinessCondition(String beginDateStr, String endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户根据条件获得一段时间内的经营收支状况</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回经营收支状况</td>
</tr>
</table>
##### （4）业务逻辑层的动态模型

**AccountService的动态模型**



**SalesDetailService的动态模型**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-SalesDetails顺序图.png)

**BusinessProgressService的动态模型**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-BusinessProgress顺序图.png)

**BusinessConditionService的动态模型**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250100-BusinessCondition顺序图.png)

**FinanceSheetService的动态模型**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-付款单顺序图.png)

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-收款单顺序图.png)

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-工资单顺序图.png)

#### 4.1.3 managementbl模块

##### （1）模块概述

managementbl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求说明文档。

managementbl模块的职责及接口参见软件系统结构描述文档。

##### （2）整体结构

根据提及结构的设计，我们将系统分为了前端展示层、Controller层、业务逻辑层、数据层。

我们将managementbl模块分解成YearBonusService、SaleStrategyService功能，以下是各功能的包图和类的职责

**YearBonusService的设计如下图**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-YearBonus结构图.jpg)

**各个类的职责**

| 模块                 | 职责                         |
| -------------------- | ---------------------------- |
| YearBonusController  | 负责实现制定年终奖的数据交互 |
| YearBonusServiceImpl | 实现制定年终奖功能           |
| YearBonusService     | 制定年终奖的功能接口         |
| YearBonustPO         | 制定年终奖的实现载体         |
| YearBonusVO          | 制定年终奖的实现载体         |

**SaleStrategyService的设计如下图**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-sale-strtegy结构图.jpg)

**各个类的职责**

| 模块                     | 职责                           |
| ------------------------ | ------------------------------ |
| SaleStrategyController   | 负责实现制定销售策略的数据交互 |
| SaleStrategyCustomerImpl | 实现制定客户级别销售策略功能   |
| SaleStrategyProductImpl  | 实现制定组合商品销售策略功能   |
| SaleStrategyPriceImpl    | 实现制定不同总价销售策略功能   |
| SaleStrategyService      | 制定年终奖的功能接口           |
| SaleStrategyCustomerPO   | 制定年终奖的实现载体           |
| SaleStrategyProductPO    | 制定年终奖的实现载体           |
| SaleStrategyPricePO      | 制定年终奖的实现载体           |
| SaleStrategyVO           | 制定年终奖的实现载体           |

##### （3）模块内部类的接口规范

<div style="text-align: center;"><b>YearBonusService</b></div>
<table >
<tr>
  <th rowspan="3">YearBonusServiceMapper.createBonus</th>
  <td>语法</td>
  <td>public int createBonus(YearBonusPO yearBonusPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息添加年终奖信息</td>
</tr>
<tr>
  <th rowspan="3">YearBonusServiceMapper.findAllBonus</th>
  <td>语法</td>
  <td>public List&lt;YearBonusPO&gt; findAllBonus();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库具有年终奖相关的数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有年终奖信息的列表</td>
</tr>
<tr>
  <th rowspan="3">YearBonusServiceMapper.deleteBonusById</th>
  <td>语法</td>
  <td>public int deleteBonusById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库有相应id的年终奖记录</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除该年终奖记录</td>
</tr>
<tr>
  <th rowspan="3">YearBonusServiceMapper.getBonusById</th>
  <td>语法</td>
  <td>public YearBonusPO getBonusById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库有相应id的年终奖记录</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回该id对应的年终奖信息</td>
</tr>        
</table>

<div style="text-align: center;"><b>SaleStrategyService</b></div>
<table >
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.saveGifts</th>
  <td>语法</td>
  <td>public void saveGifts(List&lt;SaleStrategyGiftPO&gt; gifts);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息保存销售策略中的有关商品列表</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.saveSaleStrategyCustomer</th>
  <td>语法</td>
  <td>public void saveSaleStrategyCustomer(SaleStrategyCustomerPO saleStrategyCustomerPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统保存客户级别销售策略</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.saveSaleStrategyProduct</th>
  <td>语法</td>
  <td>public void saveSaleStrategyProduct(SaleStrategyProductPO saleStrategyProductPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统保存组合商品的销售策略</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.saveSaleStrategyPrice</th>
  <td>语法</td>
  <td>public void saveSaleStrategyPrice(SaleStrategyPricePO saleStrategyPricePO);
</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统保存不同总价的销售策略</td>
</tr>    
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.findSaleStrategyCustomer</th>
  <td>语法</td>
  <td>public List&lt;SaleStrategyCustomerPO&gt; findSaleStrategyCustomer();
</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库有客户级别的销售策略数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入客户级别销售策略列表</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.findSaleStrategyProduct</th>
  <td>语法</td>
  <td>public List&lt;SaleStrategyProductPO&gt; findSaleStrategyProduct();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库具有组合商品销售策略的数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有组合商品销售策略的列表</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.findSaleStrategyPrice</th>
  <td>语法</td>
  <td>public List&lt;SaleStrategyPricePO&gt; findSaleStrategyPrice();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库具有不同总价销售策略的数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有不同总价销售策略的列表</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyServiceMapper.findGiftsByStrategyId</th>
  <td>语法</td>
  <td>public List&lt;SaleStrategyGiftPO&gt; findGiftsByStrategyId(Integer saleStrategyId);
</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库有相应id的销售策略</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回商品列表</td>
</tr> 
</table>
##### （4）业务逻辑层的动态模型

**SaleStrategyService的动态模型**

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-saleStrategy顺序图.jpg)

##  

