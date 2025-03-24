package com.lzc.module.jdbc.service;

import com.lzc.module.jdbc.pojo.User;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
@RequiredArgsConstructor
public class JdbcService {

    private final JdbcTemplate jdbcTemplate;


    public User select(String id) {
        return jdbcTemplate.execute((Connection conn) -> {
            // 可以直接使用conn实例，不要释放它，回调结束后JdbcTemplate自动释放:
            // 在内部手动创建的PreparedStatement、ResultSet必须用try(...)释放:
            try (var ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?")) {
                ps.setObject(1, id);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return User.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .build();
//                        return new User( // new User object:
//                                rs.getInt("id"), // id
//                                rs.getString("email"), // email
//                                rs.getString("password"), // password
//                                rs.getString("name")); // name
                    }
                    throw new RuntimeException("user not found by id.");
                }
            }
        });
    }

}
