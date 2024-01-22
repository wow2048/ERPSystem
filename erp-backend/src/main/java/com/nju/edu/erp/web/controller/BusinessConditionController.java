package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.service.BusinessConditionService;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/finance")
public class BusinessConditionController {

    private final BusinessConditionService businessConditionService;

    public BusinessConditionController(BusinessConditionService businessConditionService) {
        this.businessConditionService = businessConditionService;
    }

    @GetMapping(value = "/getBusinessCondition")
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    public Response getBusinessCondition(String beginDateStr, String endDateStr) {
        return Response.buildSuccess(businessConditionService.getBusinessCondition(beginDateStr, endDateStr));
    }
}
