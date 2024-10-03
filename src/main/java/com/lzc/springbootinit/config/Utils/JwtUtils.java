package com.lzc.springbootinit.config.Utils;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    private static String signKey = "lzc_asd";

    private static Long expire = 12L * 3600 * 1000;

    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey) // s太短会报错
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)) // 有效期
                .compact();
        return jwt;
    }


    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
