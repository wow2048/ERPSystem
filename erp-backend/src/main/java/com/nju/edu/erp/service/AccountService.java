package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.AccountVO;

import java.util.List;

public interface AccountService {

    /**
     * 创建一个账户
     * @param accountVO 账户信息
     */
    void createAccount(AccountVO accountVO);

    /**
     * 更新一个账户
     * @param accountVO 账户信息
     */
    void updateAccount(AccountVO accountVO);

    /**
     * 根据账户id，删除一个账户
     * @param accountID 账户id
     */
    void deleteAccountById(Integer accountID);

    /**
     * 根据关键词查询账户
     * @param keyWords 查询的关键词
     * @return List<AccountVO></>
     */
    List<AccountVO> getAccountsByKeys(String keyWords);

    /**
     * 获取所有的账户
     * @return List<AccountVO></>
     */
    List<AccountVO> getAllAccount();
}
