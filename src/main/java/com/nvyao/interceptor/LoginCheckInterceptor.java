package com.nvyao.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.nvyao.pojo.Result;
import com.nvyao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * ServletRequest 和 HttpServletRequest 是 Java Servlet API 中的两个接口，用于处理 HTTP 请求。
     * ServletRequest 是 Servlet API 中定义的通用请求接口，它提供了处理来自客户端的请求的方法和属性。ServletRequest 接口定义了处理请求的通用方法，无论请求是基于 HTTP 还是其他协议。它是 Servlet API 中处理请求的基础接口，提供了一些常见的请求信息和方法，如获取参数、获取请求头信息、获取请求属性等。
     * HttpServletRequest 是 ServletRequest 的子接口，它扩展了 ServletRequest 接口，提供了更多与 HTTP 请求相关的方法。HttpServletRequest 接口定义了一系列用于获取 HTTP 请求信息的方法，如获取请求方法、获取请求 URL、获取请求头、获取请求参数等。它还提供了一些用于操作 HTTP Session、Cookie、请求分发等的方法。
     * 总的来说，ServletRequest 是一个通用的请求接口，适用于处理各种类型的请求，而 HttpServletRequest 是对 HTTP 请求的特定扩展，提供了更多与 HTTP 协议相关的方法和属性。如果你只需要处理 HTTP 请求，通常会使用 HttpServletRequest 接口，因为它提供了更多与 HTTP 相关的功能和信息
     */

    @Override   //目标资源方法运行前执行，返回true表示放行，返回false表示不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("访问的url是：{}", url);

        //2.判断请求url中是否login，如果包含说明是登录操作
        //但是，因为在WebConfig类中进行配置：.addPathPatterns("/**").excludePathPatterns("/login");，所以下面的判断方法永远不会执行
        if (url.contains("login")){
            log.info("登录操作，放行");
            return true;
        }

        //3.获取请求头的令牌
        String jwttoken = request.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误信息（未登录）
        if(!StringUtils.hasLength(jwttoken)){   //如果 jwttoken 为空或只包含空白字符（即没有长度）
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换， 对象--json
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
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
            response.getWriter().write(notLogin);
            return false;
        }

        //6.jwt解析没问题，放行
        log.info("登录的令牌合法，放行");
        return true;
    }

    @Override   //目标资源方法运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override   //视图渲染完毕后执行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
