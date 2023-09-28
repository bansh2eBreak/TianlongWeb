package com.nvyao.filter;

import com.alibaba.fastjson.JSONObject;
import com.nvyao.pojo.Result;
import com.nvyao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init被执行....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("访问的url是：{}", url);

        //2.判断请求url中是否login，如果包含说明是登录操作放行
        if (url.contains("login")){
            log.info("登录操作，放行");
            chain.doFilter(request, response);
            return;
        }

        //3.获取请求头的令牌
        String jwttoken = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误信息（未登录）
        if(!StringUtils.hasLength(jwttoken)){
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换， 对象--json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5.解析jwttoekn，如果解析失败，返回错误信息（未登录）
        try {
            JwtUtils.parseJwt(jwttoken);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换， 对象--json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6.jwt解析没问题，放行
        log.info("登录的令牌合法，放行");
        chain.doFilter(request, response);
    }

}
