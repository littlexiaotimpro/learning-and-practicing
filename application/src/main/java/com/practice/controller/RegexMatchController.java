package com.practice.controller;

import com.practice.dto.PhoneDTO;
import com.practice.virtualDB.VirtualDB;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController(value = "/regex")
public class RegexMatchController {

    /**
     * 手机号匹配
     */
    @PostMapping(value = "/check/phone")
    public String checkIllegalPhone(@RequestBody PhoneDTO phoneDTO) {
        String phone = phoneDTO.getPhone();
        Pattern pattern = Pattern.compile("^1[3|5|8]\\d[\\s]?\\d{4}[\\s]?\\d{4}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            boolean anyMatch = VirtualDB.phones.stream()
                    .anyMatch(item -> Objects.equals(trimAll(item), trimAll(phone)));
            // 将输入的数据暂存
            VirtualDB.phones.add(phone);
            if (anyMatch) {
                return "此手机号已经被其他用户注册";
            } else {
                return "通过此手机号注册成功";
            }
        } else {
            // 匹配纯数字的非法手机号
            Pattern compile = Pattern.compile("[0|2-9][3,5]\\d[\\s]?\\d{4}[\\s]?\\d{4}$");
            Matcher matcher1 = compile.matcher(phone);
            if (!matcher1.matches()) {
                return "通知本手机号无法注册，提示为非法手机号";
            } else {
                return "此手机号码为中国大陆非法手机号码";
            }
        }
    }

    /**
     * 去除字符串中的所有的空字符
     */
    private String trimAll(String str){
        return str.replaceAll("\\s*", "");
    }

}
