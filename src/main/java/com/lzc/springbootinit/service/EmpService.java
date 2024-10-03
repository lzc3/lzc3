package com.lzc.springbootinit.service;

import com.lzc.springbootinit.mybatis.pojo.Emp;
import com.lzc.springbootinit.mybatis.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {

    PageBean page(Integer page,
                  Integer pageSize,
                  String name,
                  Short gender,
                  LocalDate begin,
                  LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp login(Emp emp);
}
