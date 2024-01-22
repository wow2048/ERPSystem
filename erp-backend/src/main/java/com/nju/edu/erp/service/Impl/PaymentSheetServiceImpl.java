package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AccountDao;
import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.PayAccountPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.PayAccountVO;
import com.nju.edu.erp.model.vo.financeSheet.PaymentSheetVO;
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

@Service("paymentSheet")
public class PaymentSheetServiceImpl implements FinanceSheetService {

    PaymentSheetDao paymentSheetDao;
    CustomerService customerService;
    AccountDao accountDao;

    @Autowired
    PaymentSheetServiceImpl(PaymentSheetDao paymentSheetDao, CustomerService customerService, AccountDao accountDao){
        this.paymentSheetDao = paymentSheetDao;
        this.customerService = customerService;
        this.accountDao = accountDao;
    }

    /**
     * 创建付款单
     * @param vo 付款单
     */
    @Override
    public String makeSheet(FinanceSheetVO vo){
        PaymentSheetVO sheetVO = (PaymentSheetVO) vo;
        PaymentSheetPO sheetPO = new PaymentSheetPO();
        BeanUtils.copyProperties(sheetVO, sheetPO);

        sheetPO.setCreateTime(new Date());

        PaymentSheetPO latest = paymentSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "FKD");
        sheetPO.setId(id);

        sheetPO.setState(FinanceSheetState.PENDING);

        sheetPO.setRead(false);

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<PayAccountPO> payAccountPOList = new ArrayList<>();
        List<PayAccountVO> payAccountVOList = sheetVO.getPayAccountList();
        for (PayAccountVO payAccountVO : payAccountVOList){
            PayAccountPO payAccountPO = new PayAccountPO();
            BeanUtils.copyProperties(payAccountVO, payAccountPO);
            payAccountPO.setId(id);
            payAccountPOList.add(payAccountPO);

            if(payAccountPO.getPayMoney() == null || payAccountPO.getPayMoney().doubleValue() <= 0){
                throw new MyServiceException("E0002", "无效的付款金额");
            }

            totalAmount = totalAmount.add(payAccountPO.getPayMoney());
        }

        sheetPO.setTotalAmount(totalAmount);
        paymentSheetDao.saveSheet(sheetPO);
        paymentSheetDao.savePayAccounts(payAccountPOList);

        return id;
    }

    /**
     * 根据付款单状态获取销售单
     * @param state 付款单状态
     * @return 付款单列表
     */
    @Override
    public List<FinanceSheetVO> getSheetByState(FinanceSheetState state){
        List<FinanceSheetVO> res = new ArrayList<>();
        List<PaymentSheetPO> all;

        if (state == null){
            all = paymentSheetDao.findAllSheet();
        }else {
            all = paymentSheetDao.findSheetByState(state);
        }

        for (PaymentSheetPO po : all){
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
            res.add(vo);
        }

        return res;
    }

    /**
     * 审批付款单
     * @param sheetId 付款单id
     * @param state 付款单状态
     */
    @Override
    public void approval(String sheetId, FinanceSheetState state){
        //更新审批状态
        paymentSheetDao.updateSheetState(sheetId, state);

        if (state.equals(FinanceSheetState.SUCCESS)){
            PaymentSheetPO paymentSheetPO = paymentSheetDao.findSheetById(sheetId);

            //更改客户应收,即客户应收额度加上付款单的总额
            CustomerPO customer = customerService.findCustomerById(paymentSheetPO.getCustomerId());
            customer.setReceivable(customer.getReceivable().add(paymentSheetPO.getTotalAmount()));
            customerService.updateCustomer(customer);

            //修改对应账户
            List<PayAccountPO> payAccountPOList = paymentSheetDao.findPayAccountsBySheetId(paymentSheetPO.getId());
            for (PayAccountPO payAccountPO : payAccountPOList){
                AccountPO accountPO = accountDao.getAccountById(payAccountPO.getBankId());
                accountPO.setAmount(accountPO.getAmount().subtract(payAccountPO.getPayMoney()));
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
        List<PaymentSheetPO> all;

        all = paymentSheetDao.getApprovalUnreadByOperator(operator);

        for (PaymentSheetPO po : all){
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
            res.add(vo);

            paymentSheetDao.setSheetRead(po.getId());
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
        PaymentSheetPO po = paymentSheetDao.findSheetById(id);

        if (po == null){
            throw new MyServiceException("E0003", "无效的id");
        }

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

        return vo;
    }
}
