package com.practice.start.interceptor;

import com.practice.start.common.LocalCache;
import com.practice.start.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class SelfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         * 在正常访问接口前进行拦截，对缓存中的数据进行处理
         * 用于用户登录后并访问相应api时，能够从缓存中直接获取用户信息
         * 正常登录的用户会将登录状态保存在cookie或session中，需要从中获取对应信息
         */
        LocalCache.clear();
        String username = request.getParameter("username");
        System.out.println("自定义拦截器执行!");
        User user = new User();
        user.setUsername(username);
        LocalCache.setUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
