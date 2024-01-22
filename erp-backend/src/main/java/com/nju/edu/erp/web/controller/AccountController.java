package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据关键词搜索账户
     * @param keys 关键词
     */
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @GetMapping("/searchAccount")
    public Response searchAccountByKeys(@RequestParam String keys) {
        return Response.buildSuccess(accountService.getAccountsByKeys(keys));
    }

    /**
     * 创建账户
     */
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping("/createAccount")
    public Response addAccount(@RequestBody AccountVO accountVO) {
        accountService.createAccount(accountVO);
        return Response.buildSuccess();
    }

    /**
     * 更新账户
     */
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping("/updateAccount")
    public Response updateAccount(@RequestBody AccountVO accountVO) {
        accountService.updateAccount(accountVO);
        return Response.buildSuccess();
    }

    /**
     * 根据id删除账户
     */
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @GetMapping("/deleteAccount")
    public Response deleteAccount(@RequestParam(value = "id") int id) {
        accountService.deleteAccountById(id);
        return Response.buildSuccess();
    }

    /**
     * 获取所有账户
     */
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @GetMapping("/getAllAccount")
    public Response getAllAccount() {
        return Response.buildSuccess(accountService.getAllAccount());
    }
}
