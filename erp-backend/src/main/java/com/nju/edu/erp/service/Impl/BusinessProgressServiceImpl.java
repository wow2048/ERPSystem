package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.*;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.BusinessProgressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BusinessProgressServiceImpl implements BusinessProgressService {

    private final BusinessProgressDao businessProgressDao;
    private final SaleSheetDao saleSheetDao;
    private final SaleReturnsSheetDao saleReturnsSheetDao;
    private final PurchaseSheetDao purchaseSheetDao;
    private final PurchaseReturnsSheetDao purchaseReturnsSheetDao;
    private final SalarySheetDao salarySheetDao;
    private final PaymentSheetDao paymentSheetDao;
    private final ReceiptSheetDao receiptSheetDao;

    public BusinessProgressServiceImpl(BusinessProgressDao businessProgressDao,
                                       SaleSheetDao saleSheetDao,
                                       SaleReturnsSheetDao saleReturnsSheetDao,
                                       PurchaseSheetDao purchaseSheetDao,
                                       PurchaseReturnsSheetDao purchaseReturnsSheetDao,
                                       SalarySheetDao salarySheetDao,
                                       PaymentSheetDao paymentSheetDao,
                                       ReceiptSheetDao receiptSheetDao) {
        this.businessProgressDao = businessProgressDao;
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
        this.salarySheetDao = salarySheetDao;
        this.paymentSheetDao = paymentSheetDao;
        this.receiptSheetDao = receiptSheetDao;
    }


    @Override
    public List<SaleSheetVO> getSaleSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<SaleSheetPO> pos = businessProgressDao.getSaleSheet(beginDate, endDate, customer, salesman);
        List<SaleSheetVO> vos = new ArrayList<>();
        for(SaleSheetPO po : pos) {
            SaleSheetVO vo = new SaleSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleSheetContentVO> contentVOS = new ArrayList<>();
            List<SaleSheetContentPO> contentPOS = saleSheetDao.findContentBySheetId(po.getId());
            for(SaleSheetContentPO contentPO : contentPOS) {
                SaleSheetContentVO contentVO = new SaleSheetContentVO();
                BeanUtils.copyProperties(contentPO, contentVO);
                contentVOS.add(contentVO);
            }
            vo.setSaleSheetContent(contentVOS);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<SaleReturnsSheetVO> getSaleReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<SaleReturnsSheetPO> pos = businessProgressDao.getSaleReturnsSheet(beginDate, endDate, customer, salesman);
        List<SaleReturnsSheetVO> vos = new ArrayList<>();
        for(SaleReturnsSheetPO po : pos) {
            SaleReturnsSheetVO vo = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleReturnsSheetContentVO> contentVOS = new ArrayList<>();
            List<SaleReturnsSheetContentPO> contentPOS = saleReturnsSheetDao.findContentBySaleReturnsSheetId(po.getId());
            for(SaleReturnsSheetContentPO contentPO : contentPOS) {
                SaleReturnsSheetContentVO contentVO = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(contentPO, contentVO);
                contentVOS.add(contentVO);
            }
            vo.setSaleReturnsSheetContent(contentVOS);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<PurchaseSheetVO> getPurchaseSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<PurchaseSheetPO> pos = businessProgressDao.getPurchaseSheet(beginDate, endDate, customer, salesman);
        List<PurchaseSheetVO> vos = new ArrayList<>();
        for(PurchaseSheetPO po : pos) {
            PurchaseSheetVO vo = new PurchaseSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<PurchaseSheetContentVO> contentVOS = new ArrayList<>();
            List<PurchaseSheetContentPO> contentPOS = purchaseSheetDao.findContentByPurchaseSheetId(po.getId());
            for(PurchaseSheetContentPO contentPO : contentPOS) {
                PurchaseSheetContentVO contentVO = new PurchaseSheetContentVO();
                BeanUtils.copyProperties(contentPO, contentVO);
                contentVOS.add(contentVO);
            }
            vo.setPurchaseSheetContent(contentVOS);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<PurchaseReturnsSheetVO> getPurchaseReturnsSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<PurchaseReturnsSheetPO> pos = businessProgressDao.getPurchaseReturnsSheet(beginDate, endDate, customer, salesman);
        List<PurchaseReturnsSheetVO> vos = new ArrayList<>();
        for(PurchaseReturnsSheetPO po : pos) {
            PurchaseReturnsSheetVO vo = new PurchaseReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<PurchaseReturnsSheetContentVO> contentVOS = new ArrayList<>();
            List<PurchaseReturnsSheetContentPO> contentPOS = purchaseReturnsSheetDao.findContentByPurchaseReturnsSheetId(po.getId());
            for(PurchaseReturnsSheetContentPO contentPO : contentPOS) {
                PurchaseReturnsSheetContentVO contentVO = new PurchaseReturnsSheetContentVO();
                BeanUtils.copyProperties(contentPO, contentVO);
                contentVOS.add(contentVO);
            }
            vo.setPurchaseReturnsSheetContent(contentVOS);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<SalarySheetVO> getSalarySheet(String beginDateStr, String endDateStr, String customer, String salesman){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<SalarySheetPO> pos = businessProgressDao.getSalarySheet(beginDate, endDate, customer, salesman);
        List<SalarySheetVO> vos = new ArrayList<>();
        for(SalarySheetPO po : pos) {
            SalarySheetVO vo = new SalarySheetVO();
            BeanUtils.copyProperties(po, vo);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<PaymentSheetVO> getPaymentSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<PaymentSheetPO> pos = businessProgressDao.getPaymentSheet(beginDate, endDate, customer, salesman);
        List<PaymentSheetVO> vos = new ArrayList<>();
        for (PaymentSheetPO po : pos){
            PaymentSheetVO vo = new PaymentSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<PayAccountPO> payAccountPOList = paymentSheetDao.findPayAccountsBySheetId(po.getId());
            List<PayAccountVO> payAccountVOList = new ArrayList<>();
            for (PayAccountPO payAccountPO : payAccountPOList){
                PayAccountVO payAccountVO = new PayAccountVO();
                BeanUtils.copyProperties(payAccountPO, payAccountVO);
                payAccountVOList.add(payAccountVO);
            }
            vo.setPayAccountList(payAccountVOList);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<ReceiptSheetVO> getReceiptSheet(String beginDateStr, String endDateStr, String customer, String salesman) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null, endDate = null;
        try {
            beginDate = sf.parse(beginDateStr);
            endDate = sf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(beginDate.after(endDate)) return null;

        List<ReceiptSheetPO> pos = businessProgressDao.getReceiptSheet(beginDate, endDate, customer, salesman);
        List<ReceiptSheetVO> vos = new ArrayList<>();
        for (ReceiptSheetPO po : pos){
            ReceiptSheetVO vo = new ReceiptSheetVO();
            BeanUtils.copyProperties(po, vo);

            List<ReceiveAccountPO> receiveAccountPOList = receiptSheetDao.findReceiveAccountsBySheetId(po.getId());
            List<ReceiveAccountVO> receiveAccountVOList = new ArrayList<>();
            for (ReceiveAccountPO receiveAccountPO : receiveAccountPOList){
                ReceiveAccountVO receiveAccountVO = new ReceiveAccountVO();
                BeanUtils.copyProperties(receiveAccountPO, receiveAccountVO);
                receiveAccountVOList.add(receiveAccountVO);
            }

            vo.setReceiveAccountList(receiveAccountVOList);
            vos.add(vo);
        }
        return vos;
    }


}
