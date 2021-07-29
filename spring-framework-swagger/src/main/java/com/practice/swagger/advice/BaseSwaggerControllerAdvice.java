package com.practice.swagger.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class BaseSwaggerControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(BaseSwaggerControllerAdvice.class);

    @Pointcut(value = "execution(* com.practice.swagger.controller.SwaggerController.*(..))")
    public void pointCut() {
    }

    /**
     * @param joinPoint 切入点
     * @param res       切入方法的返回值（Around后置处理的返回结果）
     */
    @AfterReturning(value = "pointCut()", returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res) {
        final Object[] args = joinPoint.getArgs();
        final HttpServletRequest request = (HttpServletRequest) args[0];
        final String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        logger.info("{} -> {}", res.toString(), uriString);
        logger.info("{} -> {}", res.toString(), request.getPathInfo());
        logger.info("{} -> {}", res.toString(), request.getContextPath());
        logger.info("{} -> {}", res.toString(), request.getServletPath());
        logger.info("{} -> {}", res.toString(), request.getHttpServletMapping());
        final Map<String, Object> map = (Map<String, Object>) res;
        map.put("uri", uriString);
        map.put("getPathInfo", request.getPathInfo());
        map.put("getContextPath", request.getContextPath());
        map.put("getServletPath", request.getServletPath());
        final HttpServletMapping httpServletMapping = request.getHttpServletMapping();
        Map<String, Object> sub = new HashMap<>();
        map.put("getHttpServletMapping", sub);
        sub.put("getMappingMatch", httpServletMapping.getMappingMatch());
        sub.put("getPattern", httpServletMapping.getPattern());
        sub.put("getMatchValue", httpServletMapping.getMatchValue());
        sub.put("getServletName", httpServletMapping.getServletName());
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Map<String, Object> map = new HashMap<>();
        try {
            final Object proceed = joinPoint.proceed();
            map.put(proceed.toString(), proceed);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return map;
    }

}
