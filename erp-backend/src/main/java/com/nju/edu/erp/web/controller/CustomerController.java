package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findByType")
    public Response findByType(@RequestParam CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

    @PostMapping("/createCustomer")
    public Response addCustomer(@RequestBody CustomerVO customerVO) {
        return Response.buildSuccess(customerService.createCustomer(customerVO));
    }

    @PostMapping("/updateOne")
    public Response updateCustomer(@RequestBody CustomerVO customerVO) {
        //将传入的VO转为PO
        CustomerPO customerPO = new CustomerPO();
        BeanUtils.copyProperties(customerVO, customerPO);
        customerService.updateCustomer(customerPO);
        return Response.buildSuccess();
    }

    @GetMapping("/deleteById")
    public Response deleteById(@RequestParam(value = "id") int id) {
        customerService.deleteById(id);
        return Response.buildSuccess();
    }
}
