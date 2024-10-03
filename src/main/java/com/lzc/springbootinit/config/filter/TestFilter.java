package com.lzc.springbootinit.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.lzc.springbootinit.config.Utils.JwtUtils;
import com.lzc.springbootinit.pojo.ResponseVo;
import io.jsonwebtoken.Claims;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 同时，去启动类添加注解@ServletComponentScan
//@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {

    /**
     * 初始化方法，只调用一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        if (url != null && url.contains("login")) {
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = req.getHeader("token");
        if (StringUtils.hasLength(token)) {
            ResponseVo noLogin = ResponseVo.error("NO_LOGIN");
            String jsonString = JSONObject.toJSONString(noLogin);
            resp.getWriter().write(jsonString);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJwt(token);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseVo noLogin = ResponseVo.error("NO_LOGIN");
            String jsonString = JSONObject.toJSONString(noLogin);
            resp.getWriter().write(jsonString);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 销毁方法,只调用一次
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
