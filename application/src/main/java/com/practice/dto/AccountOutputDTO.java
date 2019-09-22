package com.practice.dto;

import com.practice.entity.Account;
import com.practice.utils.convert.ConvertUtils;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AccountOutputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/2215:03
 */
@Data
public class AccountOutputDTO {
    private String accountNo;
    private String userName;
    private Date birthday;

    public Account convertTo() {
        throw new AssertionError("不支持正向转化方法");
    }

    public AccountOutputDTO convertBack(Account account) {
        ConvertUtils<AccountOutputDTO, Account> convertUtils = new ConvertUtils<AccountOutputDTO, Account>();
        convertUtils.setA(new AccountOutputDTO()).setB(account);
        return convertUtils.convertBack();
    }

}
