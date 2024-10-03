package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.config.Utils.JwtUtils;
import com.lzc.springbootinit.mybatis.pojo.Emp;
import com.lzc.springbootinit.pojo.ResponseVo;
import com.lzc.springbootinit.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpServiceImpl empService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody Emp emp) {
        Emp e = empService.login(emp);

        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return ResponseVo.success(jwt);
        }

        return ResponseVo.error(e);
    }

}
