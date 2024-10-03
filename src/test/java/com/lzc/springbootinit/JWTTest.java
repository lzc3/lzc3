package com.lzc.springbootinit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    String SIGN_STRING = "lzc_asd";

    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Tom");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_STRING) // s太短会报错
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)) // 有效期
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzI3ODc2Mjg5LCJ1c2VybmFtZSI6IlRvbSJ9.N34Wmlmu0z2FHKqh-CdqYTQnPMeU88eiC7SUihbQlFo";
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_STRING)
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }

}
