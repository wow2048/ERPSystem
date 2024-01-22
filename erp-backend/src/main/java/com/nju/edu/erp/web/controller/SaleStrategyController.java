package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.saleStrategy.SaleStrategyVO;
import com.nju.edu.erp.service.SaleStrategyService;
import com.nju.edu.erp.service.Impl.saleStrategyImpl.SaleStrategyServiceContext;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/saleStrategy")
public class SaleStrategyController {

    @Autowired
    private SaleStrategyServiceContext saleStrategyServiceContext;

    /**
     * 新增一个销售策略
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @PostMapping(value = "/createStrategy")
    public Response createStrategy(@RequestBody SaleStrategyVO saleStrategyVO){
        SaleStrategyService saleServiceImpl = saleStrategyServiceContext.getResource(saleStrategyVO);
        saleServiceImpl.createSaleStrategy(saleStrategyVO);
        return Response.buildSuccess();
    }

    /**
     * 获取已有的销售策略
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/getStrategy")
    public Response getStrategyByType(@RequestParam("strategyType") String strategyType){
        SaleStrategyService saleServiceImpl = saleStrategyServiceContext.getResource(strategyType);
        return Response.buildSuccess(saleServiceImpl.getSaleStrategy());
    }

}
