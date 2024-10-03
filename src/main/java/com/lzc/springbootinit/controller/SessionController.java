package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.pojo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    String LOGIN_USERNAME = "login_username";
    String LOGIN_VALUE = "lzc";
    String COOKIE_LOGIN_KEY = LOGIN_USERNAME;
    String SESSION_LOGIN_KEY = LOGIN_USERNAME;

    @GetMapping("/cookie/add")
    public ResponseVo cookieAdd(HttpServletResponse httpServletResponse) {
        httpServletResponse.addCookie(new Cookie(COOKIE_LOGIN_KEY, LOGIN_VALUE));
        return ResponseVo.success();
    }

    @GetMapping("/cookie/get")
    public ResponseVo cookieGet(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (COOKIE_LOGIN_KEY.equals(cookieName)) {
                log.info(COOKIE_LOGIN_KEY + "：{}", cookie.getValue());
            }
        }
        return ResponseVo.success();
    }

    @GetMapping("/session/set")
    public ResponseVo sessionSet(HttpSession httpSession) {
        httpSession.setAttribute(SESSION_LOGIN_KEY, LOGIN_VALUE);
        return ResponseVo.success();
    }

    @GetMapping("/session/get")
    public ResponseVo sessionGet(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Object attribute = session.getAttribute(SESSION_LOGIN_KEY);
        return ResponseVo.success(attribute);
    }


}
