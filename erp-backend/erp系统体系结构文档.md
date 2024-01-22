# erp系统软件体系结构描述文档

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
| 何富森   | 2022.4.29 | 创建erp系统软件体系结构描述文档                              | v0.1   |
| 陈佳耀   | 2022.4.29 | 添加组合视角部分中的运行时进程、物理部署，salebl模块CilentService的逻辑层接口规范和数据层接口规范，commoditybl模块UserServe,WarehouseServe的逻辑层接口规范和数据层接口规范 | v0.2   |
| 戎润祥   | 2022.4.30 | 增加逻辑视角中的包图和设计方案，创建逻辑视角中的包图和设计方案，设计commoditybl模块Category的逻辑层接口规范和数据层接口规范 | v0.3   |
| 邵浚谦   | 2022.4.30 | 增加接口视角中salebl模块的checkService和inquireService的逻辑层接口规范和数据层接口规范，编写了引言的编制目的、词汇表和参考资料以及产品概述，增加了信息视角的内容 | v0.4   |
| 何富森   | 2022.5.1  | 增加接口视角commodity模块的productService的逻辑层接口规范和数据层接口规范 | v0.5   |
| 陈佳耀   | 2022.7.8  | 增加financebl模块中SalesDetailsService、BusinessProgressService、BusinessCondition的逻辑层接口规范和数据层接口规范 | V1.0   |
| 戎润祥   | 2022.7.9  | 增加staffbl中的StaffService的逻辑层接口规范和数据层接口规范  | v1.1   |
| 邵浚谦   | 2022.7.9  | 增加financebl中FinanceService的辑层接口规范和数据层接口规范，添加了相应的信息视角 | v1.2   |
| 何富森   | 2022.7.9  | 增加managementbl中YearBonusService、SaleStrategyService以及financebl中AccountService的逻辑层接口和数据层接口规范，添加了相应的信息视角 | v1.3   |

## 目录

1.**引言**

​	1.1编制目的

​	1.2词汇表

​	1.3参考资料

2.**产品概述**

3.**逻辑视图**

4.**组合视图**

​	4.1开发包图

​	4.2运行时进程

​	4.3物理部署

5.**接口视角**

​	5.1模块职责

​	5.2用户界面层分解

​	5.3业务逻辑层分解

​	5.4数据层分解

6.**信息视角**

## 1. 引言

### 1.1 编制目的

本报告详细完成对灯具开关企业ERP系统的概要设计，达到指导软件的详细设计和具体开发的目的，同时让开发人员、测试人员以及用户都能得到软件的详细信息，从而实现多方的沟通。

本报告面向的主要对象为开发人员、测试人员及最终用户。

### 1.2 词汇表

| 词汇名称 |                         词汇含义                         |                 备注                 |
| :------: | :------------------------------------------------------: | :----------------------------------: |
|   ERP    | 企业资源计划，本文档特指为灯具开关企业开发的资源管理系统 |                  无                  |
|   B/S    |                    浏览器/服务器架构                     |                  无                  |
| 应收额度 |                  本公司给客户的信用额度                  | 客户欠公司的钱的总额不能超过应收额度 |
|   应收   |               客户还应付给公司但还未付的钱               |                  无                  |
|   应付   |                      公司欠客户的钱                      |                  无                  |
|  业务员  |                 与客户进行业务往来的员工                 |       客户可以拥有默认的业务员       |

### 1.3 参考资料

【1】.  IEEE相关标准;

【2】.  灯具开关企业ERP软件需求规格文档;

【3】.  软件工程与计算（卷二）：软件开发的技术基础 / 骆斌主编. ——北京：机械工业出版社, 2012.12;

## 2. 产品概述

## 3. 逻辑视图

ERP系统中，选择了分层体系结构凤凤，将系统分为3层（展示层、业务逻辑层、数据层）能够很好地示意整个高层抽象。展示层包含GUI页面的实现，业务逻辑层包含业务逻辑处理的实现，数据层负责数据的持久化和访问。分层体系结构的逻辑视角和逻辑设计方案如图1和图2所示。

| 功能     | 对应逻辑包                                                   |
| -------- | ------------------------------------------------------------ |
| 销售     | salesui, salesbl, salesdata<br>commodityui, commoditybl, commoditydata |
| 库存     | commodityui, commoditybl, commoditydata<br>salesui, salesbl, salesdata |
| 财务     | financeui, financebl, financedata<br>staffui, staffbl, staffdata<br>managementui, managementbl, managementdata |
| 人力管理 | staffManagementui, staffManagementbl, staffManagementdata<br>checkinui, checkinbl, checkindata<br>salaryStrategyui, salaryStrategybl, salaryStrategydata |
| 经营管理 | managementui, managementbl, managementdata<br>financeui, financebl, financedata<br>staffui, staffbl, staffdata |

![参照体系结构风格的包图表达逻辑视角](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250081体系结构包图.png)

图1 参照体系结构风格的包图表达逻辑视角

![软件体系结构逻辑设计方案](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250081软件体系结构逻辑设计方案.png)

图2 软件体系结构逻辑设计方案

## 4. 组合视图

### 4.1 开发包图

ERP系统的最终开发包图如表1所示。

表1 ERP系统的最终开发包设计

| 开发（物理）包        | 依赖的其他开发包                                          |
| --------------------- | --------------------------------------------------------- |
| mainui                | salesui, commodityui, staffui, financeui, managementui,vo |
| salesui               | salesblservice, 界面类库包, vo                            |
| salesblservice        |                                                           |
| salesbl               | salesblservice, salesdataservice, po                      |
| salesdataservice      | Java RMI, po                                              |
| salesdata             | databaseutility, po, salesdataservice                     |
| commoditysui          | commodityblservice, 界面类库包                            |
| commodityblservice    |                                                           |
| commoditybl           | commodityblservice, commoditydataservice, po, salesbl     |
| commoditydataservice  | Java RMI, po                                              |
| commoditydata         | Java RMI, po, databaseutility                             |
| financeui             | financeblservice, 界面类库包                              |
| financeblservice      |                                                           |
| financebl             | financeblservice, financedataservice, po, staffbl         |
| financedataservice    | Java RMI, po                                              |
| financedata           | Java RMI, po, databaseutility                             |
| staffui               | staffblservice, 界面类库包                                |
| staffblservice        |                                                           |
| staffbl               | staffblservice, staffdataservice, po                      |
| staffdataservice      | Java RMI, po                                              |
| staffdata             | Java RMI, po, databaseutility                             |
| managementui          | managementblservice, 界面类库包                           |
| managementblservice   |                                                           |
| managementbl          | managementblservice, managementdataservice, po, staffbl   |
| managementdataservice | Java RMI, po                                              |
| managementdata        | Java RMI, po, databaseutility                             |
| vo                    |                                                           |
| po                    |                                                           |
| utilitybl             |                                                           |
| 界面类库包            |                                                           |
| Java RMI              |                                                           |
| databaseutility       | JDBC                                                      |

ERP系统客户端开发包图如图3所示，服务器端开发包图如图4所示。

![ERP系统客户端开发包图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250081客户端.png)

图3 ERP系统客户端开发包图

![ERP系统服务器端开发包图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250081服务器端.png)

图4 ERP系统服务器端开发包图

### 4.2 运行时进程

在本系统，会有多个客户端用户通过专用浏览器使用本系统，在服务端则分为前端（基于Node.JS）、后端（基于Springboot）和数据库（基于MySQL）个进程。

![运行时进程图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-进程图.png)

### 4.3 物理部署

本系统采用B/S架构，用户仅需与专用浏览器进行页面交互，即可使用本系统。客户端构件放在客户端机器上，服务器端构建放在服务器端机器上。在服务端节点上则需部署`Node.js`（包含`npm`）、`JDK`和`MySQL`）。具体版本要求已在下图中展示，服务端的前后端的设计已为在容器中部署特别设计，可以实现从容器打包到容器部署的全过程。部署图如下。

![部署图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250099-部署图.png)

## 5. 接口视角

### 5.1 模块职责

客户端模块视图如图所示

![客户端模块视图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-客户端模块视图.png)

服务器端视图如图所示

![服务器端模块视图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-服务器端模块视图.jpg)

客户端各层的职责如表所示

| 层             | 职责                                         |
| -------------- | -------------------------------------------- |
| 启动模块       | 负责初始化网络通信机制，启动用户界面         |
| 展示层         | 展示界面                                     |
| 业务逻辑层     | 对于用户界面的输入进行响应并进行业务逻辑处理 |
| 客户端网络模块 | 利用 Java RMI 机制查找 RMI 服务              |

服务器端各层的职责

| 层               | 职责                                           |
| ---------------- | ---------------------------------------------- |
| 启动模块         | 负责初始化网络通信的机制，启动用户页面         |
| 数据层           | 负责数据持久化及数据访问接口                   |
| 服务器端网络模块 | 利用 Java RMI 机制启动 RMI 服务，注册 RMI 服务 |

每一层只是使用下方直接接触的层。层与层之间仅仅是通过接口的调用来完成的。这样的实现降低了层与层之间的耦合，层之间的调用如下表所示

| 接口                                                         | 服务调用方       | 服务提供方       |
| ------------------------------------------------------------ | ---------------- | ---------------- |
| salesservice<br>commodityservice<br>financeservice<br>staffservice<br>mangementservice | 客户端展示层     | 客户端业务逻辑层 |
| salesdataservice<br>commoditydataservice<br>financedataservice<br>staffdataservice<br>managementdataservice<br>datafactoryservice | 客户端业务逻辑层 | 服务器端数据层   |

### 5.2 展示层层分解

用户界面跳转如下图所示

![用户界面跳转图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250098-展示层界面跳转图.jpg)

#### 5.2.1 展示层模块的职责

用户界面模块的职责如下表

| 模块         | 职责                   |
| ------------ | ---------------------- |
| mainui       | 登录的主页面           |
| salesui      | 销售进货人员的操作页面 |
| commodityui  | 库存管理人员的操作页面 |
| financeui    | 财务人员的操作页面     |
| staffui      | 人力资源人员的操作页面 |
| managementui | 总经理的操作页面       |

#### 5.2.2 展示层模块的接口规范

mainui需要的服务接口

| 服务名     | 服务     |
| ---------- | -------- |
| User.login | 用户登陆 |

其他业务逻辑模块所需要的服务接口

| 服务名   | 服务                             |
| -------- | -------------------------------- |
| *Service | 相应界面有一个相应的业务逻辑接口 |

#### 5.2.3 展示层模块设计原理

图形化界面利用vue技术实现

### 5.3 业务逻辑层分解

#### 5.3.1 commoditybl模块

**CategoryService的接口规范**

<center><b>提供的服务（供接口）</b></center>

<table >
<tr>
  <th rowspan="3">CategoryService.createCategory</th>
  <td>语法</td>
  <td>public CategoryVO createCategory(Integer parentId, String name) ;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统在选定节点下创建名为name的分类</td>
<tr>
  <th rowspan="3">CategoryService.queryAllCategory</th>
  <td>语法</td>
  <td>public List<CategoryVO> queryAllCategory();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回一个包含所有分类的列表。</td>
 <tr>
  <th rowspan="3">CategoryService.updateCategory</th>
  <td>语法</td>
  <td>public CategoryVO updateCategory(Integer id, String name);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统对分类名称进行修改</td>
 <tr>
  <th rowspan="3">CategoryService.deleteCategory</th>
  <td>语法</td>
  <td>public void deleteCategory(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除选定分类</td>
 <tr>
</tr>
</table>
<center><b>需要的服务（供接口）</b></center>


| 服务名                                                       | 服务               |
| ------------------------------------------------------------ | ------------------ |
| CategoryServiceMapper.createCategory(CategoryPO categoryPO); | 数据库创建一个分类 |
| CategoryServiceMapper.findByCategoryId(Integer categoryId);  | 数据库查询相应分类 |
| CategoryServiceMapper.findAll();                             | 数据库查询所有分类 |
| CategoryServiceMapper.updateById(CategoryPO categoryPO);     | 数据库更新相应分类 |
| CategoryServiceMapper.deleteById(Integer id);                | 数据库删除选定分类 |

**ProductService的接口规范**

<center><b>提供的服务（供接口）</b></center>

<table >
<tr>
  <th rowspan="3">ProductService.createProduct</th>
  <td>语法</td>
  <td>public ProductInfoVO createProduct(CreateProductVO inputVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建一个新的商品</td>
<tr>
  <th rowspan="3">ProductService.queryAllProduct</th>
  <td>语法</td>
  <td>public List<ProductInfoVO> queryAllProduct();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回一个包含所有商品的列表。</td>
 <tr>
  <th rowspan="3">ProductService.updateCProduct</th>
  <td>语法</td>
  <td>public ProductInfoVO updateProduct(ProductInfoVO productInfoVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统对商品信息进行修改</td>
 <tr>
  <th rowspan="3">ProductService.deleteProduct</th>
  <td>语法</td>
  <td>public void deleteById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除选定商品</td>
 <tr>
</tr>
</table>
<center><b>需要的服务（供接口）</b></center>


| 服务名                                                   | 服务               |
| -------------------------------------------------------- | ------------------ |
| ProductServiceMapper.createProduct(ProductPO productPO); | 数据库创建一个商品 |
| ProductServiceMapper.findById(String id);                | 数据库查询相应商品 |
| ProductServiceMapper.findAll();                          | 数据库查询所有商品 |
| ProductServiceMapper.updateById(ProductPO productPO);    | 数据库更新相应商品 |
| ProductServiceMapper.deleteById(String id);              | 数据库删除选定商品 |

**UserService的接口规范**

无

**WarehouseService的接口规范**

<center><b>提供的服务（供接口）</b></center>

<table >
<tr>
  <th rowspan="3">WarehouseService.show</th>
  <td>语法</td>
  <td>public WarehouseVO WarehouseServeShow(time start, time end);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统输出时间范围为start到end的库存信息。</td>
<tr>
  <th rowspan="3">WarehouseService.check</th>
  <td>语法</td>
  <td>public WarehouseVO WarehouseServiceCheck();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统输出当天的库存快照，并提供Excel。</td>
 <tr>
  <th rowspan="3">WarehouseService.addGiftList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceAddGiftList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建库存赠送单。</td>
 <tr>
  <th rowspan="3">WarehouseService.addOverflowList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceAddOverflowList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建库存报溢单。</td>
 <tr>
  <th rowspan="3">WarehouseService.addLossList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceAddLossList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建库存报损单。</td>
<tr>
<tr>
  <th rowspan="3">WarehouseService.addAlarmList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceAddAlarmList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建库存报警单。</td>
</tr>    
<tr>
  <th rowspan="3">WarehouseService.addInboundList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceeAddIntboundList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建入库单。</td>
</tr>    
<tr>
  <th rowspan="3">WarehouseService.addOutboundList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceAddOutboundList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建出库单。</td>
</tr>   
</table>
<center><b>需要的服务（供接口）</b></center>

| 服务名                                 | 服务                       |
| -------------------------------------- | -------------------------- |
| WarehouseServiceMapper.addGiftList     | 数据库创建一个库存赠送单。 |
| WarehouseServiceMapper.addOverflowList | 数据库创建一个库存报溢单。 |
| WarehouseServiceMapper.addLossList     | 数据库创建一个库存报损单。 |
| WarehouseServiceMapper.addAlarmList    | 数据库创建一个库存报警单。 |
| WarehouseServiceMapper.addInboundList  | 数据库创建一个入库单。     |
| WarehouseServiceMapper.addOutboundList | 数据库创建一个出库单。     |
| CategoryService.show                   | 系统输出商品分类信息       |
| produceService.show                    | 系统输出商品信息           |

#### 5.3.2 salebl模块

**ClientService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>

<table >
<tr>
  <th rowspan="3">CilentService.login</th>
  <td>语法</td>
  <td>public void CilentServiceLogin(String userId, String password);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>销售进货销售人员或总经理输入正确的账号和密码。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>用户进入操作页面。</td>
<tr>
  <th rowspan="3">ClientService.show</th>
  <td>语法</td>
  <td>public ClientVO ClientServiceShow(String cilentId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回客户信息。</td>
 <tr>
  <th rowspan="3">ClientService.create</th>
  <td>语法</td>
  <td>public String ClientServiceCreate();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>创建一个新用户，返回新客户的id。</td>
 <tr>
  <th rowspan="3">ClientService.update</th>
  <td>语法</td>
  <td>public void ClientServiceUpdate(String cilentId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统修改客户信息。</td>
 <tr>
  <th rowspan="3">ClientServiceManage.delete</th>
  <td>语法</td>
  <td>public boolean ClientServiceDelete(String cilentId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>删除ID为cilentId的客户的信息。</td>
<tr>
</table>
<div style="text-align: center;"><b>需要的服务（供接口）</b></div>

| 服务名                                                       | 服务                           |
| ------------------------------------------------------------ | ------------------------------ |
| staffbl.login(String userId, String password)                | 销售进货销售人员或总经理登入   |
| ClientServiceMapper.findByUserId(String cilentId);           | 根据用户id获得客户信息         |
| ClientServiceMapper.create();                                | 创建一个新用户，返回新客户的id |
| ClientServiceMapper.update(String cilentId, inputMessage toDo); | 修改ID为cilentId的客户的信息   |
| ClientServiceMapper.delete(String cilentId)                  | 删除ID为cilentId的客户的信息   |

**SaleService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>

<table >
<tr>
  <th rowspan="3">SaleService.addSale</th>
  <td>语法</td>
  <td>public void addSale(SaleFormVO saleFormVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的SaleFormVO符合规范。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>创建一个新的销售单。</td>
<tr>
  <th rowspan="3">SaleService.addSaleReturn</th>
  <td>语法</td>
  <td>void addSaleReturn(SaleReturnFormVO saleReturnFormVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>输入的SaleReturnFormVO符合规范。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>创建一个新的销售退货单。</td>
<tr>
</table>
<div style="text-align: center;"><b>需要的服务（供接口）</b></div>

| 服务名                           | 服务                         |
| -------------------------------- | ---------------------------- |
| SaleMapper.insertSaleSheet       | 向数据库插入一张销售单       |
| SaleMapper.insertSaleReturnSheet | 向数据库纯插入一张销售退货单 |

**CheckService模块的接口规范**

<center><b>提供的服务（供接口）</b></center>

<table >
<tr>
  <th rowspan="3">Check.showOrder</th>
  <td>语法</td>
  <td>public OrderInfoVO showOrder(String UserID, String OrderID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>审批者收到待审批的单据并进入该单据的查看页面</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回单据的信息</td>
</tr>
    <th rowspan="3">Check.pass</th>
  <td>语法</td>
  <td>public boolean pass(Stirng UserID, String OrderID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>审批者选择通过当前待审批的单据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>根据审批者的身份执行相应操作</td>
</tr>
  <th rowspan="3">Check.reject</th>
  <td>语法</td>
  <td>public boolean reject(Sring UserID, String OrderID)；</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>审批者选择拒绝当前待审批的单据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将审批未通过的信息发送给提交单据的销售人员</td>
</table>

<center><b>需要的服务（供接口）</b></center>

| 服务名                                       | 服务                     |
| -------------------------------------------- | ------------------------ |
| CheckMapper.selectByOrderID(String OrderID); | 按单据ID获取单据信息     |
| CheckMapper.selectByUserID(String UserID);   | 按使用者id查找使用者身份 |

**InquireService模块的接口规范**

<center><b>提供的服务（供接口）</b></center>

<table >
<tr>
  <th rowspan="3">Inquire.getStaffList</th>
  <td>语法</td>
  <td>public String[] getStaffList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求选择销售人员</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回销售人员姓名的列表</td>
</tr>
    <th rowspan="3">Inquire.getTimeList</th>
  <td>语法</td>
  <td>public String[] getTimeList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求选择起始或结束时间点</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回时间点的列表</td>
</tr>
    <th rowspan="3">Inquire.getGreatClient</th>
  <td>语法</td>
  <td>public String getGreatClient(String ClientID, String startTime, String endTime);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求查询</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回该时间段内所选销售人员成交金额最大的客户姓名及ID</td>
</tr>
  <th rowspan="3">Inquire.getClientInfo</th>
  <td>语法</td>
  <td>public ClientInfoVO getClientInfo(String ClientID)；</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户选择查看查询得到的客户的详细信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回客户详细信息</td>
</tr>
  <th rowspan="3">Inquire.getOrderInfo</th>
  <td>语法</td>
  <td>public OrderInfoVO[] getOrderInfo(String ClientID)；</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户选择查看查询得到的客户相应订单的详细信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回客户相应订单的详细信息</td>
</table>

<center><b>需要的服务（供接口）</b></center>

| 服务名                                                       | 服务                                                 |
| ------------------------------------------------------------ | ---------------------------------------------------- |
| InquireMapper.selectStaff();                                 | 获取销售人员姓名及ID                                 |
| InquireMapper.selectClient(String ClientID, String startTime, String endTime); | 按销售人员ID和起始、结束时间点查找交易金额最大的客户 |
| InquireMapper.selectClientInfoByClientID(String ClientID)    | 按客户id查找客户信息                                 |
| InquireMapper.selectOrderInfoByClientID(String ClientID)     | 按客户id查找订单信息                                 |

#### 5.3.3 financebl模块

**AccountService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
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
<center><b>需要的服务（供接口）</b></center>

| 服务名                                            | 服务               |
| ------------------------------------------------- | ------------------ |
| AccountMapper.createAccount(AccountPO accountPO); | 创建一个账户       |
| AccountMapper.updateAccount(AccountPO accountPO); | 更新一个账户       |
| AccountMapper.deleteAccountById(Integer id);      | 根据id删除一个账户 |
| AccountMapper.getAccountsByKeys(String keys);     | 根据关键词搜索账户 |
| AccountMapper.getAllAccount();                    | 查找所有账户       |
| AccountMapper.getAccountById(Integer id);         | 根据id查找一个账户 |

**SalesDetailService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">SailsDetails.counting</th>
  <td>语法</td>
  <td>public List&lt;SalesDetailsVO&gt; saleDetailsCounting(String beginDateStr, String endDateStr, String productName, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的商品销售情况的列表</td>
</tr>
</table>
<center><b>需要的服务（供接口）</b></center>

| 服务名                                                       | 服务                             |
| ------------------------------------------------------------ | -------------------------------- |
| SalesDetailsMapper.findSalesDetails(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得商品的销售情况的列表 |

**BusinessProgressService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">BusinessProgress.getSaleSheet</th>
  <td>语法</td>
  <td>public List&lt;SaleSheetVO&gt; getSaleSheet(String beginDateStr, String endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
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
  <td>无</td>
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
  <td>无</td>
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
  <td>无</td>
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
  <td>无</td>
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
  <td>无</td>
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
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回满足条件的收款单列表</td>
</tr>
</table>   
<center><b>需要的服务（供接口）</b></center>

| 服务名                                                       | 服务                       |
| ------------------------------------------------------------ | -------------------------- |
| BusinessProgressMapper.getSaleSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得销售单列表     |
| BusinessProgressMapper.getSalesReturnsSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得销售退货单列表 |
| BusinessProgressMapper.getPurchaseSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得进货单列表     |
| BusinessProgressMapper.getPurchaseReturnsSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得进货退货单列表 |
| BusinessProgressMapper.getSalarySheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得工资单列表     |
| BusinessProgressMapper.getPaymentSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得付款单列表     |
| BusinessProgressMapper.getReceiptSheet(Date beginDate, Date endDate, String productName, String customer, String salesman); | 根据条件获得收款单列表     |

**BusinessConditionService的接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
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
<center><b>需要的服务（供接口）</b></center>

| 服务名                                                       | 服务                                     |
| ------------------------------------------------------------ | ---------------------------------------- |
| BusinessConditionMapper.getSalesRevenue(Date beginDate, Date endDate); | 获得一段时间内的销售收入                 |
| BusinessConditionMapper.getGap(Date beginDate, Date endDate); | 获得一段时间内的进货退货差价             |
| BusinessConditionMapper.getVoucher(Date beginDate, Date endDate); | 获得一段时间内的代金券与实际收款差额收入 |
| BusinessConditionMapper.getDiscount(Date beginDate, Date endDate); | 获得一段时间内的收入折让                 |
| BusinessConditionMapper.getSalesCost(Date beginDate, Date endDate); | 获得一段时间内的销售成本                 |
| BusinessConditionMapper.getLaborCost(Date beginDate, Date endDate); | 获得一段时间内的人力成本                 |





**FinanceSheetService的接口规范**

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

<center><b>需要的服务（供接口）</b></center>

| 服务名                                                       | 服务                                 |
| ------------------------------------------------------------ | ------------------------------------ |
| PaymentSheetMapper.saveSheet(PaymentSheetPO paymentSheet);   | 数据库创建一张付款单                 |
| PaymentSheetMapper.savePayAccounts(List&lt;PayAccountPO&gt; payAccountList) | 数据库创建一个含多项付款项的付款列表 |
| PaymentSheetMapper.findAllSheet()                            | 数据库查找所有的付款单               |
| PaymentSheetMapper.findSheetById(String id)                  | 数据库根据id查找付款单               |
| PaymentSheetMapper.findPayAccountsBySheetId(String sheetId)  | 数据库根据id查找付款列表             |
| PaymentSheetMapper.updateSheetState(String sheetId, FinanceSheetState state) | 数据库根据id更新付款单状态           |
| PaymentSheetMapper.getLatest()                               | 数据库获取最新的一张付款单           |
| PaymentSheetMapper.findSheetByState(FinanceSheetState state) | 数据库根据状态查找付款单             |
| PaymentSheetMapper.getApprovalUnreadByOperator(String operator) | 数据库根据操作员查找付款单           |
| PaymentSheetMapper.setSheetRead(String sheetId)              | 数据库根据id将单据设为已读           |

| 服务名                                                       | 服务                                 |
| ------------------------------------------------------------ | ------------------------------------ |
| ReceiptSheetMapper.saveSheet(ReceiptSheetPO receiptSheet);   | 数据库创建一张收款单                 |
| ReceiptSheetMapper.saveReceiveAccounts(List&lt;ReceiveAccountPO&gt; receiveAccountList) | 数据库创建一个含多项收款项的收款列表 |
| ReceiptSheetMapper.findAllSheet()                            | 数据库查找所有的收款单               |
| ReceiptSheetMapper.findSheetById(String id)                  | 数据库根据id查找收款单               |
| ReceiptSheetMapper.findReceiveAccountsBySheetId(String sheetId) | 数据库根据id查找收款列表             |
| ReceiptSheetMapper.updateSheetState(String sheetId, FinanceSheetState state) | 数据库根据id更新收款单状态           |
| ReceiptSheetMapper.getLatest()                               | 数据库获取最新的一张收款单           |
| ReceiptSheetMapper.findSheetByState(FinanceSheetState state) | 数据库根据状态查找收款单             |
| ReceiptSheetMapper.getApprovalUnreadByOperator(String operator) | 数据库根据操作员查找收款单           |
| ReceiptSheetMapper.setSheetRead(String sheetId)              | 数据库根据id将单据设为已读           |

| 服务名                                                       | 服务                       |
| ------------------------------------------------------------ | -------------------------- |
| SalarySheetMapper.saveSheet(SalarySheetPO salarySheet);      | 数据库创建一张收款单       |
| SalarySheetMapper.findAllSheet()                             | 数据库查找所有的收款单     |
| SalarySheetMapper.findSheetById(String id)                   | 数据库根据id查找收款单     |
| SalarySheetMapper.updateSheetState(String sheetId, FinanceSheetState state) | 数据库根据id更新收款单状态 |
| SalarySheetMapper.getLatest()                                | 数据库获取最新的一张收款单 |
| SalarySheetMapper.findSheetByState(FinanceSheetState state)  | 数据库根据状态查找收款单   |
| SalarySheetMapper.getApprovalUnreadByOperator(String operator) | 数据库根据操作员查找收款单 |
| SalarySheetMapper.setSheetRead(String sheetId)               | 数据库根据id将单据设为已读 |

#### 5.3.4 Staffbl模块

**StaffService逻辑层接口规范 **

<div style="text-align: center;"><b>提供的服务（需接口）</b></div>

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
<div style="text-align: center;"><b>需要的服务（供接口）</b></div>

| 服务名                  | 服务                           |
| ----------------------- | ------------------------------ |
| StaffMapper.createStaff | 向数据库插入一个员工信息       |
| StaffMapper.updateOne   | 更新数据库中的员工信息         |
| StaffMapper.findOneById | 根据员工id查找员工信息         |
| StaffMapper.findAllById | 按照员工id顺序返回所有员工信息 |
| StaffMapper.deleteById  | 删除数据库中相应id的员工信息   |
| CheckInMapper.addCheckIn               | 向数据库插入一个员工打卡记录     |
| CheckInMapper.findAllById              | 根据id返回当前员工的所有打卡记录 |
| CheckInMapper.findAll                  | 返回所有员工的打卡次数           |
| CheckInMapper.findById                 | 返回当前id的员工的打卡次数       |
| CheckInMapper.findAllCheckInLastMonth  | 返回所有员工的上个月打卡次数     |
| CheckInMapper.findAllCheckInThisMonth  | 返回所有员工的本月 打卡次数      |
| CheckInMapper.findCheckInLastMonthById | 返回当前id的员工的上个月打卡次数 |
| CheckInMapper.deleteById               | 删除当前id的员工的打卡记录       |
| StaffMapper.findStragegyByPositon | 返回当前岗位的薪资策略     |
| StaffMapper.findPositionSalary    | 返回当前岗位的岗位工资     |
| StaffMapper.findBaseSalaryById    | 返回当前id的员工的基本工资 |

#### 5.3.5 managementbl模块

**YearBonusService逻辑层接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">YearBonusService.createBonus</th>
  <td>语法</td>
  <td>public void createBonus(YearBonusVO yearBonusVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户为选定员工添加年终奖</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息添加年终奖信息</td>
</tr>
<tr>
  <th rowspan="3">YearBonusService.getYearBonus</th>
  <td>语法</td>
  <td>public List&lt;YearBonusVO&gt; getYearBonus();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取所有员工年终奖信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有年终奖信息的列表</td>
</tr>    
</table>
<center><b>需要的服务（供接口）</b></center>

| 服务名                          | 服务                           |
| ------------------------------- | ------------------------------ |
| YearBonusMapper.createBonus     | 创建一条年终奖记录             |
| YearBonusMapper.findAllBonus    | 查询所有的年终奖记录           |
| YearBonusMapper.deleteBonusById | 根据员工id删除年终奖记录       |
| YearBonusMapper.getBonusById    | 根据员工编号查询相关年终奖记录 |
| StaffService.getStaffsById      | 获取所有员工的信息             |

**SaleStrategyService逻辑层接口**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">SaleStrategyService.createSaleStrategy</th>
  <td>语法</td>
  <td>public void createSaleStrategy(SaleStrategyVO saleStrategyVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户输入信息创建一个新的销售策略</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息生成新的销售策略</td>
</tr>
<tr>
  <th rowspan="3">SaleStrategyService.getSaleStrategy</th>
  <td>语法</td>
  <td>public List&lt;SaleStrategyVO&gt; getSaleStrategy();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取所有销售策略的列表</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有销售策略的列表</td>
</tr>    
</table>
<center><b>需要的服务（供接口）</b></center>

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">YearBonusService.createBonus</th>
  <td>语法</td>
  <td>public void createBonus(YearBonusVO yearBonusVO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户为选定员工添加年终奖</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据输入信息添加年终奖信息</td>
</tr>
<tr>
  <th rowspan="3">YearBonusService.getYearBonus</th>
  <td>语法</td>
  <td>public List&lt;YearBonusVO&gt; getYearBonus();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>用户请求获取所有员工年终奖信息</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有年终奖信息的列表</td>
</tr>    
</table>
<center><b>需要的服务（供接口）</b></center>

| 服务名                                      | 服务                             |
| ------------------------------------------- | -------------------------------- |
| SaleStrategyMapper.saveGifts                | 保存销售策略中的有关商品列表     |
| SaleStrategyMapper.saveSaleStrategyCustomer | 保存客户级别销售策略             |
| SaleStrategyMapper.saveSaleStrategyProduct  | 保存组合商品的销售策略           |
| SaleStrategyMapper.saveSaleStrategyPrice    | 保存不同总价的销售策略           |
| SaleStrategyMapper.findSaleStrategyCustomer | 获取所有客户级别的销售策略       |
| SaleStrategyMapper.findSaleStrategyProduct  | 获取所有组合商品的销售策略       |
| SaleStrategyMapper.findSaleStrategyPrice    | 获取所有不同总价的销售策略       |
| SaleStrategyMapper.findGiftsByStrategyId    | 根据销售策略id获取相应的商品列表 |

### 5.4 数据层分解

#### 5.4.1 commoditybl模块

**CategoryService数据层接口规范**

<table >
<tr>
  <th rowspan="3">CategoryServeMapper.createCategory</th>
  <td>语法</td>
  <td>public int createCategory(CategoryPO categoryPO);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>user具有创建分类的权限。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统创建一个新分类保存在数据库，返回分类的Id。</td>
    </tr>
<tr>
  <th rowspan="3">CategoryServeMapper.findByCategoryId</th>
  <td>语法</td>
  <td>public CategoryPO findByCategoryId(Integer categoryId);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在CategoryId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统根据CategoryId在数据库中查询分类并返回分类信息。</td>
</tr>
<tr>
  <th rowspan="3">CategoryServeMapper.findAll</th>
  <td>语法</td>
  <td>public List<CategoryPO> findAll();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统返回所有分类信息组成的列表。</td>
</tr>
<tr>
  <th rowspan="3">CategoryServeMapper.deleteById</th>
  <td>语法</td>
  <td>public int deleteById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在CategoryId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统删除分类，并返回是否删除成功。</td>
</tr>
<tr>
  <th rowspan="3">CategoryServeMapper.updateById</th>
  <td>语法</td>
  <td>public int updateById(CategoryPO categoryPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在CategoryId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统更新分类，并返回是否更新成功。</td>
</tr>
</table>
**ProductService数据层接口规范**

<table >
<tr>
  <th rowspan="3">ProductServeMapper.createProduct</th>
  <td>语法</td>
  <td>public int createProduct(ProductPO productPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>user具有创建商品的权限。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统创建一个新商品保存在数据库，返回分类的Id。</td>
    </tr>
<tr>
  <th rowspan="3">ProductServeMapper.findById</th>
  <td>语法</td>
  <td>public ProductPO findById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在ProductId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统根据ProductId在数据库中查询商品并返回商品信息。</td>
</tr>
<tr>
  <th rowspan="3">ProductServeMapper.findAll</th>
  <td>语法</td>
  <td>public List<ProductPO> findAll();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统返回所有商品信息组成的列表。</td>
</tr>
<tr>
  <th rowspan="3">ProductServeMapper.deleteById</th>
  <td>语法</td>
  <td>public int deleteById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在ProductId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统删除商品，并返回是否删除成功。</td>
</tr>
<tr>
  <th rowspan="3">ProductServeMapper.updateById</th>
  <td>语法</td>
  <td>public int updateById(ProductPO productPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在ProductId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统更新商品，并返回是否更新成功。</td>
</tr>
</table>

**UserService数据层接口规范**

<table >
<tr>
  <th rowspan="3">UserServe.createUser</th>
  <td>语法</td>
  <td>public String UserServeCreateUser(User user);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>user具有创建用户的权限。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统创建一个新用户保存在数据库，返回用户的账号userId。</td>
    </tr>
<tr>
  <th rowspan="3">UserServe.findByUserIdAndPassword</th>
  <td>语法</td>
  <td>public String findByUserIdAndPassword(String UserId, String password);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在UserId且UserId与password匹配。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统根据UserId,password在数据库中查询用户并返回用户信息。</td>
</tr>
<tr>
  <th rowspan="3">UserServe.findByUserId</th>
  <td>语法</td>
  <td>public String findByUserId(String UserId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在UserId。</td>
</tr>
 <tr>
  <td>后置条件</td>
  <td>系统根据UserIde在数据库中查询用户信息并返回。</td>
</tr>
</table>

**WarehouseService数据层接口规范**

<table >
<tr>
  <th rowspan="3">WarehouseServiceMapper.addGiftList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddGiftList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个库存赠送单。</td>
</tr>
<tr>
  <th rowspan="3">WarehouseServiceMapper.addOverflowList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddOverflowList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个库存报溢单。</td>
</tr>
<tr>
  <th rowspan="3">WarehouseServiceMapper.addLossList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddLossList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个库存报损单。</td>
</tr>
 <tr>
  <th rowspan="3">WarehouseServiceMapper.addAlarmList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddAlarmList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个系统报警单。</td>
</tr>
  <tr>
  <th rowspan="3">WarehouseServiceMapper.addInboundList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddInboundList();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个入库单。</td>
</tr>
  <tr>
  <th rowspan="3">WarehouseServiceMapper.addOutboundList</th>
  <td>语法</td>
  <td>public boolean WarehouseServiceMapperAddOutboundList();</td>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库创建一个出库单。</td>
</tr>
</table>

#### 5.4.2 salebl模块

**ClientService数据层接口规范**

<table >
<tr>
  <th rowspan="3">ClientServiceMapper.findByCilentId</th>
  <td>语法</td>
  <td>public userDataBase findCustomerByUserId(String cilentId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中该客户信息。</td>
</tr>
<tr>
  <th rowspan="3">ClientServiceMapper.update</th>
  <td>语法</td>
  <td>public boolean ClientServiceUpdate(String cilentId, inputMessage toDo);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据操作员相关输入修改数据库中的客户信息。</td>
</tr>
<tr>
  <th rowspan="3">ClientServiceMapper.create</th>
  <td>语法</td>
  <td>public String ClientServiceCreate();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建一个新客户, 保存在数据库中，返回该客户的用户id。</td>
</tr>
    <tr>
  <th rowspan="3">ClientServiceMapper.delete</th>
  <td>语法</td>
  <td>public boolean ClientServiceDelete(String cilentId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为cilentId的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库删除ID为cilentId的客户的所有信息。</td>
</tr>
</table>
**SaleService数据层接口规范**

<table>
<tr>
  <th rowspan="3">SaleMapper.insertSaleSheet</th>
  <td>语法</td>
  <td>public int insertSaleSheet（SaleSheetPO toSave)；</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>向数据库插入一张销售单。</td>
</tr>
<tr>
  <th rowspan="3">SaleMapper.insertSaleReturnSheet</th>
  <td>语法</td>
  <td>public int insertSaleReturnSheet（SaleReturnSheetPO toSave)；;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>向数据库插入一张销售退货单。</td>
</tr>
</table>
**CheckService数据层接口规范**

<table >
<tr>
  <th rowspan="3">CheckMapper.selectByOrderID</th>
  <td>语法</td>
  <td>CheckMapper.selectByOrderID(String OrderID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在同样ID的Order</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回对应单据的详细信息</td>
</tr>
<tr>
  <th rowspan="3">CheckMapper.selectByUserID</th>
  <td>语法</td>
  <td>PostMapper.selectByUserID(String UserID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在同样ID的User</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回审批者的信息，包括身份、姓名</td>
</tr>
<table >
<tr>
  <th rowspan="3">CheckMapper.selectByOrderID</th>
  <td>语法</td>
  <td>CheckMapper.selectByOrderID(String OrderID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在同样ID的Order</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回对应单据的详细信息</td>
</tr>
<tr>
  <th rowspan="3">CheckMapper.selectByUserID</th>
  <td>语法</td>
  <td>PostMapper.selectByUserID(String UserID);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在同样ID的User</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>返回审批者的信息，包括身份、姓名</td>
</tr>
#### 5.4.3 financebl模块

**AccountService数据层接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
<table >
<tr>
  <th rowspan="3">AccountServiceMapper.createAccount</th>
  <td>语法</td>
  <td>public int createAccount(AccountPO accountPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据信息生成账户</td>
</tr>
<tr>
  <th rowspan="3">AccountServiceMapper.updateAccount</th>
  <td>语法</td>
  <td>public int updateAccount(AccountPO accountPO)</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在相应id的账户</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统更新选定账户的信息</td>
</tr>    
<tr>
  <th rowspan="3">AccountServiceMapper.deleteAccountById</th>
  <td>语法</td>
  <td>public int deleteAccountById(Integer id)</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在相应id的账户</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除选定账户的信息</td>
</tr>       
 <tr>
  <th rowspan="3">AccountServiceMapper.getAccountsByKeys</th>
  <td>语法</td>
  <td>List&lt;AccountPO&gt; getAccountsByKeys(String keys);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中有符合关键字条件的账户数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回符合条件的账户列表</td>
</tr> 
<tr>
  <th rowspan="3">AccountServiceMapper.getAllAccount</th>
  <td>语法</td>
  <td>List&lt;AccountPO&gt; getAllAccount();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库有账户信息数据</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有账户的列表</td>
</tr>
</table>

**SalesDetailsService数据层接口规范**

<table >
<tr>
  <th rowspan="3">SalesDetailsMapper.findSalesDetails</th>
  <td>语法</td>
  <td>public List&lt;SalesDetailsPO&gt; findSalesDetails(Date beginDate, Date endDate, String productName, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的销售情况。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的销售情况列表。</td>
</tr>
</table>
**BusinessProgressService数据层接口规范**

<table >
<tr>
  <th rowspan="3">BusinessProgressMapper.getSaleSheet</th>
  <td>语法</td>
  <td>public List&lt;SaleSheetPO&gt; getSaleSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的销售单。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的销售单列表</td>
</tr>
<tr>
  <th rowspan="3">BusinessProgressMapper.getSaleReturnsSheet</th>
  <td>语法</td>
  <td>public List&lt;SaleReturnsSheetPO&gt; getSaleReturnsSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的销售退货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的销售退货单列表</td>
</tr>    
<tr>
  <th rowspan="3">BusinessProgressMapper.getPurchaseSheet</th>
  <td>语法</td>
  <td>public List&lt;PurchaseSheetPO&gt; getPurchaseSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的进货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的进货表</td>
</tr>       
 <tr>
  <th rowspan="3">BusinessProgressMapper.getPurchaseReturnsSheet</th>
  <td>语法</td>
  <td>public List&lt;PurchaseReturnsSheetPO&gt; getPurchaseReturnsSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的进货单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的进货退货单列表</td>
</tr>
<tr>
  <th rowspan="3">BusinessProgressMapper.getSalarySheet</th>
  <td>语法</td>
  <td>public List&lt;SalarySheetPO&gt; getSalarySheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的工资单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的工资列表</td>
</tr>    
 <tr>
  <th rowspan="3">BusinessProgressMapper.getPaymentSheet</th>
  <td>语法</td>
  <td>public List&lt;PaymentSheetPO&gt; getPaymentSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的付款列表</td>
</tr>    
 <tr>
  <th rowspan="3">BusinessProgressMapper.getReceiptSheet</th>
  <td>语法</td>
  <td>public List&lt;ReceiptSheetPO&gt; getReceiptSheet(Date beginDateStr, Date endDateStr, String customer, String salesman);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在满足筛选条件的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足条件的收款单列表</td>
</tr>
</table>   

**BusinessConditionService数据层接口规范**

<table >
<tr>
  <th rowspan="3">BusinessConditionMapper.getSalesRevenue</th>
  <td>语法</td>
  <td>public int getSalesRevenue(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的销售输入</td>
</tr>
<tr>
  <th rowspan="3">BusinessConditionMapper.getGap</th>
  <td>语法</td>
  <td>public int getGap(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的进货退货差价</td>
</tr>    
<tr>
  <th rowspan="3">BusinessConditionMapper.getVoucher</th>
  <td>语法</td>
  <td>public int getVoucher(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的代金券与实际收款差额收入</td>
</tr>       
 <tr>
  <th rowspan="3">BusinessConditionMapper.getDiscount</th>
  <td>语法</td>
  <td>public int getDiscount(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的收入折让</td>
</tr>
<tr>
  <th rowspan="3">BusinessConditionMapper.getSalesCost</th>
  <td>语法</td>
  <td>public int getSalesCost(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的销售成本</td>
</tr>    
 <tr>
  <th rowspan="3">BusinessConditionMapper.getLaborCost</th>
  <td>语法</td>
  <td>public int getLaborCost(Date beginDateStr, Date endDateStr);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中满足在该时间段的人力成本</td>
</tr>    


**FinanceService数据层接口规范**

<table >
<tr>
  <th rowspan="3">PaymentSheetMapper.saveSheet</th>
  <td>语法</td>
  <td>public void saveSheet(PaymentSheetPO paymentSheet);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库不存在相同id的付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建一张付款单</td>
</tr>
<tr>
  <th rowspan="3">PaymentSheetMapper.savePayAccounts</th>
  <td>语法</td>
  <td>public int savePayAccounts(List&lt;PayAccountPO&gt; payAccountList);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建多项付款项</td>
</tr>    
<tr>
  <th rowspan="3">PaymentSheetMapper.findAllSheet</th>
  <td>语法</td>
  <td>public List&lt;PaymentSheetPO&gt; PaymentSheetMapper.findAllSheet();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中所有的付款单</td>
</tr>       
 <tr>
  <th rowspan="3">PaymentSheetMapper.findSheetById</th>
  <td>语法</td>
  <td>public PaymentSheetPO findSheetById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中id符合条件的付款单</td>
</tr>
<tr>
  <th rowspan="3">PaymentSheetMapper.findPayAccountsBySheetId</th>
  <td>语法</td>
  <td>public List&lt;PayAccountPO&gt; findPayAccountsBySheetId(String sheetId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的付款项</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中id符合条件的付款列表</td>
</tr>    
 <tr>
  <th rowspan="3">PaymentSheetMapper.updateSheetState</th>
  <td>语法</td>
  <td>public void updateSheetState(String sheetId, FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的付款单的状态变更为相应的状态</td>
</tr>
 <tr>
  <th rowspan="3">PaymentSheetMapper.getLatest</th>
  <td>语法</td>
  <td>public PaymentSheetPO getLatest();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回本日最新的一张付款单</td>
</tr>
 <tr>
  <th rowspan="3">PaymentSheetMapper.findSheetByState</th>
  <td>语法</td>
  <td>public List&lt;PaymentSheetPO&gt; findSheetByState(FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所需状态的付款单列表</td>
</tr>
 <tr>
  <th rowspan="3">PaymentSheetMapper.getApprovalUnreadByOperator</th>
  <td>语法</td>
  <td>public List&lt;PaymentSheetPO&gt; getApprovalUnreadByOperator(String operator);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统获取操作员符合条件的未读的审批完成的单据列表</td>
</tr>
 <tr>
  <th rowspan="3">PaymentSheetMapper.setSheetRead</th>
  <td>语法</td>
  <td>public void setSheetRead(String sheetId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的付款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的单据的状态变更为相应的状态</td>
</tr>





<table >
<tr>
  <th rowspan="3">ReceiptSheetMapper.saveSheet</th>
  <td>语法</td>
  <td>public void saveSheet(ReceiptSheetPO receiptSheet);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库不存在相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建一张收款单</td>
</tr>
<tr>
  <th rowspan="3">ReceiptSheetMapper.saveReceiveAccounts</th>
  <td>语法</td>
  <td>public int saveReceiveAccounts(List&lt;ReceiveAccountPO&gt; receiveAccountList);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建多项收款项</td>
</tr>    
<tr>
  <th rowspan="3">ReceiptSheetMapper.findAllSheet</th>
  <td>语法</td>
  <td>public List&lt;ReceiptSheetPO&gt; ReceiptSheetMapper.findAllSheet();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中所有的收款单</td>
</tr>       
 <tr>
  <th rowspan="3">ReceiptSheetMapper.findSheetById</th>
  <td>语法</td>
  <td>public ReceiptSheetPO findSheetById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中id符合条件的收款单</td>
</tr>
<tr>
  <th rowspan="3">ReceiptSheetMapper.findReceiveAccountsBySheetId</th>
  <td>语法</td>
  <td>public List&lt;ReceiveAccountPO&gt; findReceiveAccountsBySheetId(String sheetId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款项</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中id符合条件的收款列表</td>
</tr>    
 <tr>
  <th rowspan="3">ReceiptSheetMapper.updateSheetState</th>
  <td>语法</td>
  <td>public void updateSheetState(String sheetId, FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的收款单的状态变更为相应的状态</td>
</tr>
 <tr>
  <th rowspan="3">ReceiptSheetMapper.getLatest</th>
  <td>语法</td>
  <td>public ReceiptSheetPO getLatest();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回本日最新的一张收款单</td>
</tr>
 <tr>
  <th rowspan="3">ReceiptSheetMapper.findSheetByState</th>
  <td>语法</td>
  <td>public List&lt;ReceiptSheetPO&gt; findSheetByState(FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所需状态的收款单列表</td>
</tr>
 <tr>
  <th rowspan="3">ReceiptSheetMapper.getApprovalUnreadByOperator</th>
  <td>语法</td>
  <td>public List&lt;ReceiptSheetPO&gt; getApprovalUnreadByOperator(String operator);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统获取操作员符合条件的未读的审批完成的单据列表</td>
</tr>
 <tr>
  <th rowspan="3">ReceiptSheetMapper.setSheetRead</th>
  <td>语法</td>
  <td>public void setSheetRead(String sheetId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的单据的状态变更为相应的状态</td>
</tr>



<table >
<tr>
  <th rowspan="3">SalarySheetMapper.saveSheet</th>
  <td>语法</td>
  <td>public void saveSheet(SalarySheetPO salarySheet);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库不存在相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统创建一张收款单</td>
</tr>    
<tr>
  <th rowspan="3">SalarySheetMapper.findAllSheet</th>
  <td>语法</td>
  <td>public List&lt;SalarySheetPO&gt; SalarySheetMapper.findAllSheet();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中所有的收款单</td>
</tr>       
 <tr>
  <th rowspan="3">SalarySheetMapper.findSheetById</th>
  <td>语法</td>
  <td>public SalarySheetPO findSheetById(String id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中id符合条件的收款单</td>
</tr>
 <tr>
  <th rowspan="3">SalarySheetMapper.updateSheetState</th>
  <td>语法</td>
  <td>public void updateSheetState(String sheetId, FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的收款单的状态变更为相应的状态</td>
</tr>
 <tr>
  <th rowspan="3">SalarySheetMapper.getLatest</th>
  <td>语法</td>
  <td>public SalarySheetPO getLatest();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回本日最新的一张收款单</td>
</tr>
 <tr>
  <th rowspan="3">SalarySheetMapper.findSheetByState</th>
  <td>语法</td>
  <td>public List&lt;SalarySheetPO&gt; findSheetByState(FinanceSheetState state);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所需状态的收款单列表</td>
</tr>
 <tr>
  <th rowspan="3">SalarySheetMapper.getApprovalUnreadByOperator</th>
  <td>语法</td>
  <td>public List&lt;SalarySheetPO&gt; getApprovalUnreadByOperator(String operator);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统获取操作员符合条件的未读的审批完成的单据列表</td>
</tr>
 <tr>
  <th rowspan="3">SalarySheetMapper.setSheetRead</th>
  <td>语法</td>
  <td>public void setSheetRead(String sheetId);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库存在有相同id的收款单</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统将相应id的单据的状态变更为相应的状态</td>
</tr>





#### 5.4.4 staffbl模块

**StaffService数据层接口规范**

<table >
<tr>
  <th rowspan="3">StaffMapper.createStaff</th>
  <td>语法</td>
  <td>void createStaff(StaffPO staffPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中不存在id为staffPO.id的信息。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统在数据库中创建员工信息。</td>
</tr>
<tr>
  <th rowspan="3">StaffMapper.updateOne</th>
  <td>语法</td>
  <td>void updateOne(StaffPO staffPO);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为staffPO.id的员工。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统根据操作员相关输入修改数据库中的员工信息。</td>
</tr>
<tr>
  <th rowspan="3">StaffMapper.findOneById</th>
  <td>语法</td>
  <td>StaffPO findOneById(Integer id);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在ID为id的员工信息。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回编号为id的员工的信息。</td>
</tr>
    <tr>
  <th rowspan="3">StaffMapper.findAllById</th>
  <td>语法</td>
  <td>List<StaffPO> findAllById();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>数据库按id顺序返回所有用户的信息。</td>
</tr>
<tr>
  <th rowspan="3">StaffMapper.deleteById</th>
  <td>语法</td>
  <td>void deleteById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在编号为id的用户信息。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除数据库中编号为id的用户信息。</td>
</tr>
</table>
<table >
<tr>
  <th rowspan="3">CheckInMapper.addCheckIn</th>
  <td>语法</td>
  <td>void addCheckIn(CheckInPO checkInPO);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为checkInPO.id的员工。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统为该用户在数据库中保存一条打卡信息。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findAllById</th>
  <td>语法</td>
  <td>List<CheckInPO> findAllById(Integer id);;</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为id的员工。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回该员工的所有打卡记录。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findAll</th>
  <td>语法</td>
  <td>List<CheckInPO> findAll();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有用户的打卡次数。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findById</th>
  <td>语法</td>
  <td>CheckInPO findById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为id的客户。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回当前id员工的打卡次数。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findAllCheckInLastMonth</th>
  <td>语法</td>
  <td>List&lt;CheckInRecordPO&gt; findAllCheckInThisMonth();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有员工的上个月打卡次数。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findAllCheckInThisMonth</th>
  <td>语法</td>
  <td>List&lt;CheckInRecordPO&gt; findAllCheckInLastMonth();</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>无。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回所有员工的这个月打卡次数。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.findCheckInLastMonthById</th>
  <td>语法</td>
  <td>Integer findCheckInLastMonthById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为id的用户打卡记录。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统当前id的用户的上个月打卡记录。</td>
</tr>
<tr>
  <th rowspan="3">CheckInMapper.deleteById</th>
  <td>语法</td>
  <td>void deleteById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在ID为id的用户打卡记录。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统删除当前id的用户的所有打卡记录。</td>
</tr>
</table>
<table >
<tr>
  <th rowspan="3">StaffMapper.findStragegyByPositon</th>
  <td>语法</td>
  <td>String findStrategyByPosition(String position);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在岗位为position的记录。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回数据库中该岗位的薪资策略。</td>
</tr>
<tr>
  <th rowspan="3">StaffMapper.findPositionSalary</th>
  <td>语法</td>
  <td>Integer findPositionSalary(String position);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>在数据库中存在岗位为position的记录。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回当前岗位的岗位工资。</td>
</tr>
<tr>
  <th rowspan="3">StaffMapper.findPositionSalary</th>
  <td>语法</td>
  <td>Integer findBaseSalaryById(Integer id);</td>
</tr>
<tr>
  <td>前置条件</td>
  <td>数据库中存在ID为id的记录。</td>
</tr>
<tr>
  <td>后置条件</td>
  <td>系统返回当前id的员工的基本工资。</td>
</tr>
</table>
#### 5.4.5 managementbl模块

**YearBonusService的数据层接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
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

**SaleStrategyService的数据层接口规范**

<div style="text-align: center;"><b>提供的服务（供接口）</b></div>
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

## 6. 信息视角

系统的PO类就是对应的相关的实体类。

- `Client包`是保存客户数据的包，包含编号、分类、级别、姓名、电话、地址、邮编、电子邮箱、应收额度、应收、应付、默认业务员。

- `SaleSheet包`是保存销售单据数据的包，包含：

  **单据信息**：

  单据编号、客户、业务员、操作员、仓库、折让前总额、折让、使用代金卷金额、折让后总额、备注、创建时间、审批状态

  **出货商品清单信息**：

  出货商品编号、商品名称、商品型号、商品数量、商品单价、商品金额、商品备注

- `SaleReturnSheet包`是保存销售单据数据的包，包含：

  **单据信息**：

  单据编号、客户、业务员、操作员、仓库、折让前总额、折让、使用代金卷金额、折让后总额、备注、创建时间、审批状态

  **出货商品清单信息**：

  退货商品编号、商品名称、商品型号、商品数量、商品单价、商品金额、商品备注

- `Order包`是保存单据表信息的包，包含单据编号、单据类型、操作员、客户、审批状态

- `Category包`是保存分类数据的包、包含分类id、分类名、父分类id、叶节点状态、商品数量、下一个商品index

- `Product包`是保存库存商品的包，包含商品id、分类id、商品型号、商品数量、进价、零售价、最近进价、最近零售价

- `User包`是保存库存管理人员信息的包，包含用户id、姓名、密码、身份。

- `WarehouseInputSheet包`是保存入库单信息的包，包含：

  **入库单基本信息**：

  id、批次、操作员、操作时间

  **入库单详细信息**：

  入库商品列表id，入库单编号、商品id、商品数量、单价、出厂日期、备注

- `WarehouseOutputSheet包`是保存出库单信息的包，包含：

  **出库单基本信息**：

  id、批次、操作员、操作时间

  **出库单详细信息**：

  出库商品列表id，出库单编号、商品id、商品数量、单价、出厂日期、备注

- `Warehouse包`是保存库存表信息的包，包含：库存id、商品编号、数量、进价、批次、出厂日期



### lab7部分

- `FianceSheet包`是保存财务相关单据的包，包含：

  **付款单基本信息：**

  单据id，客户id，操作员，总额汇总，单据状态，创建时间，是否已读

  **付款列表基本信息：**

  单据id，银行账户id，付款金额，备注

  **收款单基本信息：**

  单据id，客户id，操作员，总额汇总，单据状态，创建时间，是否已读

  **付款列表基本信息：**

  单据id，银行账户id，收款金额，备注

  **工资单基本信息：**

  单据id，员工id，员工姓名，银行账户id，员工工资卡银行账户，应发工资，扣除税款，实发工资，操作员，单据状态，创建时间，是否已读
  
- `SaleStrategy包`是保存销售策略相关数据的包，包含：

​		**客户级别的销售策略基本信息**

​		销售策略id，销售策略类型、起始时间、结束时间、客户级别、折让、代金券

​		**组合商品的销售策略基本信息**

​		销售策略id，销售策略类型、起始时间、结束时间、组合商品总价、优惠价格

​		**不同总价的销售策略基本信息**

​		销售策略id，销售策略类型、起始时间、结束时间、触发总价、代金券

​		**赠品基本信息**

​		商品id，关联的销售策略id，商品名称、数量、单价、单项商品总价

+ `YearBonus包`是保存年终奖相关数据的包，包含：员工id，年终奖金额

+ `Account包`是保存账户相关数据的包，包含：账户编号、账户名称、余额

  

- `SalesDetails包`是保存销售明细数据的包，包含：时间、商品名、商品型号、商品数量、单价、总金额、状态、备注

  

