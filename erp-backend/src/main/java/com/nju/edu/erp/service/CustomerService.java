package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;

import java.util.List;

public interface CustomerService {
    /**
     * 增加客户
     * @param inputVO 输入信息
     */

    CustomerVO createCustomer(CustomerVO inputVO);

    /**
     * 根据id更新客户信息
     * @param customerPO 客户信息
     */
    void updateCustomer(CustomerPO customerPO);

    /**
     * 根据type查找对应类型的客户
     * @param type 客户类型
     * @return 客户列表
     */
    List<CustomerPO> getCustomersByType(CustomerType type);


    CustomerPO findCustomerById(Integer supplier);

    /**
     * 根据id删除对应客户
     * @param id 客户id
     */

    void deleteById(Integer id);
}
