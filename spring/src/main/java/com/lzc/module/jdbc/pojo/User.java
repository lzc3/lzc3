package com.lzc.module.jdbc.pojo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    int id;

    String name;

    int age;


}
