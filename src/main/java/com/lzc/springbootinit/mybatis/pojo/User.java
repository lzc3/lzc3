package com.lzc.springbootinit.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    private Short age;

    private Short gender;

    private String phone;

}
