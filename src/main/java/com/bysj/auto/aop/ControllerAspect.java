package com.bysj.auto.aop;

import com.alibaba.fastjson.JSON;
import com.bysj.auto.util.HttpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @author: huyongsheng
 * @description: Controller日志切面
 * @program: camera
 * @creat: 2018-08-28 09:49
 **/
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(public * com.bysj.auto.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        Logger log = Logger.getLogger(joinPoint.getSignature().getDeclaringType().getName());
        log.info(">>>===============================================");
        log.info("METHOD : " + joinPoint.getSignature().getName());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String ip = HttpUtil.getIpAddr(request);
            log.info("FROM : " + ip);
            log.info("URL : " + request.getRequestURL().toString());
            log.info("Params : " + JSON.toJSONString(request.getParameterMap()));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfter(JoinPoint joinPoint, Object ret) {
        Logger log = Logger.getLogger(joinPoint.getSignature().getDeclaringType().getName());
//        log.info("Thread : " + Thread.currentThread().getStepName());
        if (!joinPoint.getSignature().getName().contains("List")) {
            log.info("response : " + JSON.toJSONString((ret)));
        } else {
            log.info("response is too long to log");
        }
        log.info("<<<===============================================");
    }

    @AfterThrowing("log()")
    public void AfterThrowing(JoinPoint joinPoint) {
        Logger log = Logger.getLogger(joinPoint.getSignature().getDeclaringType().getName());
        log.info("=================================================================>>>");
        log.info("方法 ：" + joinPoint.getSignature().getName());
        log.info("error");
    }


}
