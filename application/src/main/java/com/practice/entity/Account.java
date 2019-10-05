package com.practice.entity;

import com.practice.annotation.Password;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @ClassName Account
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/2215:02
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class Account extends User {
    @NonNull
    private String accountId;
    private String accountNo;
    @Password(length = 6)
    private String password;
}
