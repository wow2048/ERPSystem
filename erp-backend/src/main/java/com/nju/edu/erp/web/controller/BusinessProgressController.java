package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.service.BusinessProgressService;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/finance")
public class BusinessProgressController {

    private BusinessProgressService businessProgressService;

    public BusinessProgressController(BusinessProgressService businessProgressService) {
        this.businessProgressService = businessProgressService;
    }

    @GetMapping(value = "/getSheet")
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    public Response getSheet(@RequestParam String type,@RequestParam String beginDateStr,
                             @RequestParam String endDateStr,@RequestParam String customer,
                             @RequestParam String salesman) {
        switch (type) {
            case "销售出货单":
                return Response.buildSuccess(businessProgressService.getSaleSheet(beginDateStr, endDateStr, customer, salesman));
            case "销售退货单":
                return Response.buildSuccess(businessProgressService.getSaleReturnsSheet(beginDateStr, endDateStr, customer, salesman));
            case "进货单" :
                return Response.buildSuccess(businessProgressService.getPurchaseSheet(beginDateStr, endDateStr, customer, salesman));
            case "进货退货单":
                return Response.buildSuccess(businessProgressService.getPurchaseReturnsSheet(beginDateStr, endDateStr, customer, salesman));
            case "工资单":
                return Response.buildSuccess(businessProgressService.getSalarySheet(beginDateStr, endDateStr, customer, salesman));
            case "付款单":
                return Response.buildSuccess(businessProgressService.getPaymentSheet(beginDateStr, endDateStr, customer, salesman));
            case "收款单":
                return Response.buildSuccess(businessProgressService.getReceiptSheet(beginDateStr, endDateStr, customer, salesman));
            default:
                return Response.buildSuccess(new ArrayList<>());
        }
    }
}
