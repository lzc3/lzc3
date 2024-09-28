package com.lzc.springbootinit.mybatis.mapper;


import com.lzc.springbootinit.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理。
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> list();

}
