package com.lzc.springbootinit.pojo;

import lombok.Data;

@Data
public class TestDto {
    String name;
    String age;
    TestSonDto testSonDto;
}
