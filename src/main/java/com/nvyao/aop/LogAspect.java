package com.nvyao.aop;

import com.alibaba.fastjson.JSONObject;
import com.nvyao.mapper.OperateLogMapper;
import com.nvyao.pojo.OperateLog;
import com.nvyao.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect //切面类
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.nvyao.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人ID、操作时间、操作类名方法名、参数、返回值和耗时
        //获取请求头中的jwt令牌，解析

        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        //方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //原始方法执行前记录一次时间
        long begin = System.currentTimeMillis();

        //调用原始目标方法执行
        Object result = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);

        //原始方法执行后记录一次时间
        long end = System.currentTimeMillis();

        Long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录日志操作，{}", operateLog);

        return result;
    }

}
