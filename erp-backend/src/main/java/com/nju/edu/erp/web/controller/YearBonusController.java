package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.YearBonusVO;
import com.nju.edu.erp.service.YearBonusService;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/yearBonus")
public class YearBonusController {
    private final YearBonusService yearBonusService;

    public YearBonusController(YearBonusService yearBonusService){
        this.yearBonusService = yearBonusService;
    }

    /**
     * 添加年终奖
     */
    @Authorized(roles = {Role.ADMIN, Role.GM})
    @PostMapping("/addBonus")
    public Response addBonus(@RequestBody YearBonusVO yearBonusVO){
        yearBonusService.createBonus(yearBonusVO);
        return Response.buildSuccess();
    }

    /**
     * 查询年终奖列表
     */
    @Authorized(roles = {Role.ADMIN, Role.GM})
    @GetMapping("/getBonus")
    public Response getYearBonus(){
        return Response.buildSuccess(yearBonusService.getYearBonus());
    }

}
