package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AccountDao;
import com.nju.edu.erp.dao.ReceiptSheetDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.financeSheet.*;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.FinanceSheetService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("receiptSheet")
public class ReceiptSheetServiceImpl implements FinanceSheetService {

    ReceiptSheetDao receiptSheetDao;
    CustomerService customerService;
    AccountDao accountDao;

    @Autowired
    ReceiptSheetServiceImpl(ReceiptSheetDao receiptSheetDao, CustomerService customerService, AccountDao accountDao){
        this.receiptSheetDao = receiptSheetDao;
        this.customerService = customerService;
        this.accountDao = accountDao;
    }

    /**
     * 创建收款单
     * @param vo 收款单
     */
    @Override
    public String makeSheet(FinanceSheetVO vo){
        ReceiptSheetVO sheetVO = (ReceiptSheetVO) vo;
        ReceiptSheetPO sheetPO = new ReceiptSheetPO();
        BeanUtils.copyProperties(sheetVO, sheetPO);

        sheetPO.setCreateTime(new Date());

        ReceiptSheetPO latest = receiptSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
        sheetPO.setId(id);

        sheetPO.setState(FinanceSheetState.PENDING);

        sheetPO.setRead(false);

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<ReceiveAccountPO> receiveAccountPOList = new ArrayList<>();
        List<ReceiveAccountVO> receiveAccountVOList = sheetVO.getReceiveAccountList();
        for (ReceiveAccountVO receiveAccountVO : receiveAccountVOList){
            ReceiveAccountPO receiveAccountPO = new ReceiveAccountPO();
            BeanUtils.copyProperties(receiveAccountVO, receiveAccountPO);
            receiveAccountPO.setId(id);
            receiveAccountPOList.add(receiveAccountPO);

            if(receiveAccountPO.getReceiveMoney() == null || receiveAccountPO.getReceiveMoney().doubleValue() <= 0){
                throw new MyServiceException("E0001", "无效的收款金额");
            }

            totalAmount = totalAmount.add(receiveAccountPO.getReceiveMoney());
        }

        sheetPO.setTotalAmount(totalAmount);
        receiptSheetDao.saveSheet(sheetPO);
        receiptSheetDao.saveReceiveAccounts(receiveAccountPOList);

        return id;
    }

    /**
     * 根据收款单状态获取销售单
     * @param state 收款单状态
     * @return 收款单列表
     */
    @Override
    public List<FinanceSheetVO> getSheetByState(FinanceSheetState state){
        List<FinanceSheetVO> res = new ArrayList<>();
        List<ReceiptSheetPO> all;

        if (state == null){
            all = receiptSheetDao.findAllSheet();
        }else {
            all = receiptSheetDao.findSheetByState(state);
        }

        for (ReceiptSheetPO po : all){
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
            res.add(vo);
        }

        return res;
    }

    /**
     * 审批收款单
     * @param sheetId 收款单id
     * @param state 收款单状态
     */
    @Override
    public void approval(String sheetId, FinanceSheetState state){
        //更新审批状态
        receiptSheetDao.updateSheetState(sheetId, state);

        if (state.equals(FinanceSheetState.SUCCESS)){
            ReceiptSheetPO receiptSheetPO = receiptSheetDao.findSheetById(sheetId);

            //更改客户应付,即客户应付额度加上收款单的总额
            CustomerPO customer = customerService.findCustomerById(receiptSheetPO.getCustomerId());
            customer.setPayable(customer.getPayable().add(receiptSheetPO.getTotalAmount()));
            customerService.updateCustomer(customer);

            //修改对应账户
            List<ReceiveAccountPO> receiveAccountPOList = receiptSheetDao.findReceiveAccountsBySheetId(receiptSheetPO.getId());
            for (ReceiveAccountPO receiveAccountPO : receiveAccountPOList){
                AccountPO accountPO = accountDao.getAccountById(receiveAccountPO.getBankId());
                accountPO.setAmount(accountPO.getAmount().add(receiveAccountPO.getReceiveMoney()));
                accountDao.updateAmount(accountPO);
            }
        }
    }

    /**
     * 获取未读的且审批结束的单据
     * @return 未读的且审批结束的单据
     */
    @Override
    public List<FinanceSheetVO> getSheetUnread(String operator){
        List<FinanceSheetVO> res = new ArrayList<>();
        List<ReceiptSheetPO> all;

        all = receiptSheetDao.getApprovalUnreadByOperator(operator);

        for (ReceiptSheetPO po : all){
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
            res.add(vo);

            receiptSheetDao.setSheetRead(po.getId());
        }
        return res;
    }

    /**
     * 根据id返回单据
     * @param id 单据id
     * @return 单据
     */
    @Override
    public FinanceSheetVO getSheetById(String id){
        ReceiptSheetPO po = receiptSheetDao.findSheetById(id);

        if (po == null){
            throw new MyServiceException("E0003", "无效的id");
        }

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

        return vo;
    }
}
