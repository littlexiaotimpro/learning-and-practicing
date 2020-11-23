package com.practice.start.controller;

import com.practice.start.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    LogicService logicService;

    @RequestMapping(value = "/index")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/check")
    public String check(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " : " + password);
        logicService.modifyCache(username);
        return "logic";
    }

    @RequestMapping(value = "/cache")
    public void cache() {
        logicService.checkCache();
    }

}
