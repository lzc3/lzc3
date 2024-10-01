package com.lzc.springbootinit.mybatis.mapper;

import com.lzc.springbootinit.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface EmpMapperXml {


    List<Emp> list (String name, Short gender, LocalDate begin, LocalDate end);


    void update(Emp emp);

    void deleteByIds(List<Integer> ids);

}
