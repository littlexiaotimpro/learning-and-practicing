package com.practice.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @ClassName Account
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/2215:02
 */
@Accessors(chain = true)
@Data
public class Account extends User {
    @NonNull
    private String accountId;
    private String accountNo;
    private String password;
}
