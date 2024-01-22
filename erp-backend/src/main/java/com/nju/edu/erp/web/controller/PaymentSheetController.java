package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.FinanceSheetState;
import com.nju.edu.erp.model.vo.financeSheet.FinanceSheetVO;
import com.nju.edu.erp.model.vo.financeSheet.PaymentSheetVO;
import com.nju.edu.erp.service.FinanceSheetService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/payment-sheet")
public class PaymentSheetController {

    private final FinanceSheetService financeSheetService;

    @Autowired
    public PaymentSheetController(@Qualifier("paymentSheet") FinanceSheetService financeSheetService) {
        this.financeSheetService = financeSheetService;
    }

    /**
     *
     * @param paymentSheetVO 要新增的付款单
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/sheet-make")
    public Response makeSheet(@RequestBody PaymentSheetVO paymentSheetVO){
        financeSheetService.makeSheet(paymentSheetVO);
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
        List<PaymentSheetVO> paymentSheetList = new ArrayList<>();
        for (FinanceSheetVO sheet : sheetList){
            paymentSheetList.add((PaymentSheetVO) sheet);
        }
        return Response.buildSuccess((paymentSheetList));
    }

    /**
     * 审批
     */

    @GetMapping(value = "/approval")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response approval(@RequestParam("sheetId") String sheetId,
                             @RequestParam("state") FinanceSheetState state)  {
        System.out.println("1321561464195616541986418941914161adsadsdadasf");
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
}
