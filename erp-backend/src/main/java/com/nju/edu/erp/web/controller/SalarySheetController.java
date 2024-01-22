package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.SalarySheetVO;
import com.nju.edu.erp.service.FinanceSheetService;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/salary-sheet")
public class SalarySheetController {

    private final FinanceSheetService financeSheetService;
    private final SalaryService salaryService;

    @Autowired
    public SalarySheetController(@Qualifier(value = "salarySheet") FinanceSheetService financeSheetService, SalaryService salaryService) {
        this.financeSheetService = financeSheetService;
        this.salaryService = salaryService;
    }

    /**
     *
     * @param salarySheetVO 要新增的工资单
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/sheet-make")
    public Response makeSheet(@RequestBody SalarySheetVO salarySheetVO){
        financeSheetService.makeSheet(salarySheetVO);
        return Response.buildSuccess();
    }

    /**
     * 根据状态查看单据
     * @param state 状态
     * @return 单据
     */

    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) FinanceSheetState state){
        List<FinanceSheetVO> sheetList = financeSheetService.getSheetByState(state);
        List<SalarySheetVO> salarySheetList = new ArrayList<>();
        for (FinanceSheetVO sheet : sheetList){
            salarySheetList.add((SalarySheetVO) sheet);
        }
        return Response.buildSuccess((salarySheetList));
    }

    /**
     * 审批
     */

    @GetMapping(value = "/approval")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response approval(@RequestParam("sheetId") String sheetId,
                             @RequestParam("state") FinanceSheetState state)  {
        financeSheetService.approval(sheetId, state);
        return Response.buildSuccess();
    }

    /**
     * 获取当前操作员未读的审批结果通知
     */

    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping(value = "/unread-get")
    public Response getUnreadApproval(String operator)  {
        return Response.buildSuccess(financeSheetService.getSheetUnread(operator));
    }

    /**
     * 获取员工对应的应付工资
     */

    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping(value = "/payableSalary-get")
    public Response getPayableSalary(@RequestParam("id") Integer id)  {
        return Response.buildSuccess(salaryService.findLastMonthSalary(id));
    }
}
