package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AccountDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    /**
     * 根据AccountVO的信息创建一个账户
     * @param accountVO 账户信息
     */
    @Override
    public void createAccount(AccountVO accountVO){
        // account中id为null时，系统默认自动分配id；不为null时，检查id是否被占用
        if(accountVO.getId() != null){
            AccountPO tmp = accountDao.getAccountById(accountVO.getId());
            if(tmp != null){
                throw new MyServiceException("E0000", "该账户id已被使用");
            }
        }

        if(accountVO.getName() == null){ // 检查名称是否为空
            throw new MyServiceException("E0003", "账户名称不能为空");
        }


        if(accountVO.getAmount() == null){ // 检查account余额是否为空
            throw new MyServiceException("E0001", "账户余额不能为空");
        }
        else if(accountVO.getAmount().compareTo(BigDecimal.ZERO) < 0){ // 检查余额是否为负数
            throw new MyServiceException("E0002", "账户余额应该为非负数");
        }

        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(accountVO, accountPO);

        accountDao.createAccount(accountPO);
    }

    /**
     * 根据accountVO更新一账户的信息
     * @param accountVO 账户信息
     */
    @Override
    public void updateAccount(AccountVO accountVO){
        // account中id不能为空
        if(accountVO.getId() == null){
            throw new MyServiceException("E0004", "该账户id不能为空");
        }

        if(accountVO.getName() == null){
            throw new MyServiceException("E0003", "账户名称不能为空");
        }


        if(accountVO.getAmount() == null){ // 检查account余额是否为空
            throw new MyServiceException("E0001", "账户余额不能为空");
        }
        else if(accountVO.getAmount().compareTo(BigDecimal.ZERO) < 0){ // 余额是否为负数
            throw new MyServiceException("E0002", "账户余额应该为非负数");
        }

        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(accountVO, accountPO);

        accountDao.updateAccount(accountPO);
    }

    /**
     * 根据账户id删除相应账户
     * @param id 账户id
     */
    @Override
    public void deleteAccountById(Integer id){
        AccountPO tmp = accountDao.getAccountById(id);
        if(tmp == null){
            throw new MyServiceException("E0005", "该账户不存在");
        }

        accountDao.deleteAccountById(id);
    }

    /**
     * 根据关键词查找账户名称与之相匹配的账户
     * @param keys 关键词
     * @return 符合条件的账户列表
     */
    @Override
    public List<AccountVO> getAccountsByKeys(String keys){
        List<AccountVO> res = new ArrayList<>();
        List<AccountPO> all = accountDao.getAccountsByKeys(keys);

        for(AccountPO po: all){
            AccountVO vo = new AccountVO();
            BeanUtils.copyProperties(po, vo);

            res.add(vo);
        }

        return res;
    }

    /**
     * 获取所有账户
     * @return 包含所有账户的列表
     */
    @Override
    public List<AccountVO> getAllAccount(){
        List<AccountVO> res = new ArrayList<>();
        List<AccountPO> all = accountDao.getAllAccount();

        for(AccountPO po: all){
            AccountVO vo = new AccountVO();
            BeanUtils.copyProperties(po, vo);

            res.add(vo);
        }

        return res;
    }
}
