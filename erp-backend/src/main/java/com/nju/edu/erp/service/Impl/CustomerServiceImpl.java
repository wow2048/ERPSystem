package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 增加客户
     *
     * @param inputVO 输入信息
     */
    @Override
    public CustomerVO createCustomer(CustomerVO inputVO){
        CustomerPO customer = customerDao.findOneById(inputVO.getId());
        if (customer != null){
            throw new MyServiceException("D0000", "客户已存在");
        }

        //不填写的情况下默认为0
        if (inputVO.getLineOfCredit() == null){
            inputVO.setLineOfCredit(new BigDecimal(0));
        }
        if (inputVO.getReceivable() == null){
            inputVO.setReceivable(new BigDecimal(0));
        }
        if (inputVO.getPayable() == null){
            inputVO.setPayable(new BigDecimal(0));
        }

        //客户欠本公司的钱的总额不能超过应收额度,此时抛出异常
        if (inputVO.getLineOfCredit().compareTo(inputVO.getReceivable()) < 0){
            throw new MyServiceException("D0001", "客户欠本公司的钱的总额不能超过应收额度");
        }
        CustomerPO customerPO = new CustomerPO();
        BeanUtils.copyProperties(inputVO, customerPO);
        customerDao.createCustomer(customerPO);
        return inputVO;
    }

    /**
     * 根据id更新客户信息
     *
     * @param customerPO 客户信息
     */
    @Override
    public void updateCustomer(CustomerPO customerPO) {

        customerDao.updateOne(customerPO);
    }

    /**
     * 根据type查找对应类型的客户
     *
     * @param type 客户类型
     * @return 客户列表
     */
    @Override
    public List<CustomerPO> getCustomersByType(CustomerType type) {

        return customerDao.findAllByType(type);
    }

    @Override
    public CustomerPO findCustomerById(Integer supplier) {
        return customerDao.findOneById(supplier);
    }

    /**
     * 根据id删除对应客户
     *
     * @param id 客户id
     */
    @Override
    public void deleteById(Integer id){
        customerDao.deleteById(id);
    }
}
