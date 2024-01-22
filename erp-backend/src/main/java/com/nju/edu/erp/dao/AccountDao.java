package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {

    /**
     * 创建一个账户
     * @param accountPO 账户信息
     * @return 影响的行数
     */
    int createAccount(AccountPO accountPO);

    /**
     * 更新一个账户
     * @param accountPO 账户信息
     * @return 影响的行数
     */
    int updateAccount(AccountPO accountPO);

    /**
     * 根据id删除一个账户
     * @param id 账户编号
     * @return 影响的行数
     */
    int deleteAccountById(Integer id);

    /**
     * 根据关键词搜索账户
     * @param keys 关键词
     * @return List<AccountPO> 账户列表
     */
    List<AccountPO> getAccountsByKeys(String keys);

    /**
     * 根据id查找一个账户
     * @param id 账户编号
     * @return 符合条件的账户
     */
    AccountPO getAccountById(Integer id);

    /**
     * 查找所有账户
     * @return 所有账户的列表
     */
    List<AccountPO> getAllAccount();

    void updateAmount(AccountPO accountPO);
}
