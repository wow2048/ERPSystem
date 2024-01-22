package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.service.SalesDetailsService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/finance")
public class SalesDetailsController {

    private final SalesDetailsService salesDetailsService;

    @Autowired
    public SalesDetailsController(SalesDetailsService salesDetailsService) {
        this.salesDetailsService = salesDetailsService;
    }


    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @GetMapping(value = "/salesDetails")
    public Response getSalesDetails(@RequestParam String beginDateStr,@RequestParam String endDateStr,@RequestParam String productName,@RequestParam String customer,@RequestParam String salesman) {
        return Response.buildSuccess(salesDetailsService.saleDetailsCounting(beginDateStr, endDateStr,productName, customer, salesman));
    }
}
