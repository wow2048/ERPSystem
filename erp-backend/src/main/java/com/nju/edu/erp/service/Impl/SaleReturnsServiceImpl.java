package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {

    SaleReturnsSheetDao saleReturnsSheetDao;

    SaleSheetDao saleSheetDao;

    ProductService productService;

    ProductDao productDao;

    CustomerService customerService;

    WarehouseService warehouseService;

    WarehouseDao warehouseDao;

    WarehouseOutputSheetDao warehouseOutputSheetDao;



    @Autowired
    public SaleReturnsServiceImpl(SaleReturnsSheetDao saleReturnsSheetDao, SaleSheetDao saleSheetDao,
                                  ProductService productService, CustomerService customerService,
                                  WarehouseService warehouseService, ProductDao productDao,
                                  WarehouseDao warehouseDao, WarehouseOutputSheetDao warehouseOutputSheetDao) {
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.saleSheetDao = saleSheetDao;
        this.productService = productService;
        this.productDao = productDao;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
        this.warehouseDao =  warehouseDao;
        this.warehouseOutputSheetDao = warehouseOutputSheetDao;
    }

    /**
     * 制定销售退货单
     *
     * @param saleReturnsSheetVO 销售退货单
     */
    @Override
    @Transactional
    public void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO) {
        SaleReturnsSheetPO saleReturnsSheetPO = new SaleReturnsSheetPO();
        // 确定销售退货单的一些基本信息
        BeanUtils.copyProperties(saleReturnsSheetVO, saleReturnsSheetPO);
        saleReturnsSheetPO.setOperator(userVO.getName());
        saleReturnsSheetPO.setCreateTime(new Date());
        SaleReturnsSheetPO latest = saleReturnsSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSTHD");
        saleReturnsSheetPO.setId(id);
        saleReturnsSheetPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        SaleSheetPO saleSheet = saleSheetDao.findSheetById(saleReturnsSheetPO.getSaleSheetId());
        List<SaleSheetContentPO> saleSheetContent = saleSheetDao.findContentBySheetId(saleReturnsSheetPO.getSaleSheetId());
        // 确定销售退货单的具体商品内容，计算退款金额
        Map<String, SaleSheetContentPO> map = new HashMap<>();
        for(SaleSheetContentPO item : saleSheetContent) {
            map.put(item.getPid(), item);
        }
        List<SaleReturnsSheetContentPO> sContentPOList = new ArrayList<>();
        for(SaleReturnsSheetContentVO content : saleReturnsSheetVO.getSaleReturnsSheetContent()) {
            SaleReturnsSheetContentPO sContentPO = new SaleReturnsSheetContentPO();
            BeanUtils.copyProperties(content,sContentPO);
            sContentPO.setSaleReturnsSheetId(id);
            SaleSheetContentPO item = map.get(sContentPO.getPid());
            sContentPO.setUnitPrice(item.getUnitPrice());

            BigDecimal unitPrice = sContentPO.getUnitPrice();
            sContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(sContentPO.getQuantity())));
            sContentPOList.add(sContentPO);
            totalAmount = totalAmount.add(sContentPO.getTotalPrice());
        }
        saleReturnsSheetDao.saveBatch(sContentPOList);
        // 依据要求的算法计算退款金额
        BigDecimal discount = saleSheet.getDiscount();
        totalAmount = totalAmount.multiply(discount);
        BigDecimal voucherAmount = saleSheet.getVoucherAmount();
        BigDecimal voucherTmp = voucherAmount.multiply(totalAmount.divide(saleSheet.getFinalAmount(), 2, RoundingMode.HALF_UP));
        totalAmount = totalAmount.subtract(voucherTmp);
        saleReturnsSheetPO.setTotalAmount(totalAmount);

        saleReturnsSheetDao.save(saleReturnsSheetPO);
    }

    /**
     * 根据状态获取销售退货单[不包括content信息](state == null 则获取所有进货退货单)
     *
     * @param state 销售退货单状态
     * @return 销售退货单
     */
    @Override
    public List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state) {
        List<SaleReturnsSheetVO> res = new ArrayList<>();
        List<SaleReturnsSheetPO> all;
        if(state == null) {
            all = saleReturnsSheetDao.findAll();
        } else {
            all = saleReturnsSheetDao.findAllByState(state);
        }
        for(SaleReturnsSheetPO po: all) {
            SaleReturnsSheetVO vo = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleReturnsSheetContentPO> alll = saleReturnsSheetDao.findContentBySaleReturnsSheetId(po.getId());
            List<SaleReturnsSheetContentVO> vos = new ArrayList<>();
            for (SaleReturnsSheetContentPO p : alll) {
                SaleReturnsSheetContentVO v = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setSaleReturnsSheetContent(vos);
            res.add(vo);
        }
        return res;
    }

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单id
     * @param state           销售退货单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleReturnsSheetId, SaleReturnsSheetState state) {
        // TODO
        SaleReturnsSheetPO saleReturnsSheet = saleReturnsSheetDao.findOneById(saleReturnsSheetId);
        if(state.equals(SaleReturnsSheetState.FAILURE)) {
            if(saleReturnsSheet.getState() == SaleReturnsSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = saleReturnsSheetDao.updateState(saleReturnsSheetId, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleReturnsSheetState prevState;
            if(state.equals(SaleReturnsSheetState.SUCCESS)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_2;
            } else if(state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleReturnsSheetDao.updateStateV2(saleReturnsSheetId, prevState, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
            if(state.equals(SaleReturnsSheetState.SUCCESS)) {
                // TODO 审批完成, 修改一系列状态
                // 销售退货单id->销售单id->出库单id->出库单具体内容
                String warehouseOutputSheetId = saleReturnsSheetDao.findWarehouseOutputSheetId(saleReturnsSheetId);
                List<WarehouseOutputSheetContentPO> warehouseOutputSheetContentPOS = warehouseOutputSheetDao.getAllContentById(warehouseOutputSheetId);
                Map<String, WarehouseOutputSheetContentPO> map = new HashMap<>();
                for(WarehouseOutputSheetContentPO item : warehouseOutputSheetContentPOS) {
                    map.put(item.getPid(), item);
                }

                List<SaleReturnsSheetContentPO> contents = saleReturnsSheetDao.findContentBySaleReturnsSheetId(saleReturnsSheetId);
                for (SaleReturnsSheetContentPO content: contents) {
                    // 更新库存信息
                    // 批次id+pid -> 定位到库存的一个条目
                    String pid = content.getPid();
                    WarehouseOutputSheetContentPO item = map.get(pid);
                    Integer batchId = item.getBatchId();
                    WarehousePO warehousePO = warehouseDao.findOneByPidAndBatchId(pid, batchId);
                    if(warehousePO == null) throw new RuntimeException("单据发生错误！请联系管理员！");

                    // 库存加上quantity
                    Integer quantity = content.getQuantity();
                    warehousePO.setQuantity(quantity);
                    // TODO:warehouseDao.addQuantity(warehousePO);
                    warehouseDao.deductQuantity(warehousePO);

                    // 更新商品信息
                    ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                    productInfoVO.setQuantity(productInfoVO.getQuantity()+quantity);
                    productService.updateProduct(productInfoVO);
                }

                // 更新客户信息
                // 找到相应客户
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleReturnsSheet.getSaleSheetId());
                Integer supplier = saleSheetPO.getSupplier();
                CustomerPO customer = customerService.findCustomerById(supplier);

                // 更新应付金额
                BigDecimal payableToDeduct = saleReturnsSheet.getTotalAmount();
                customer.setPayable(customer.getPayable().subtract(payableToDeduct));
                customerService.updateCustomer(customer);
            }
        }
    }
}
