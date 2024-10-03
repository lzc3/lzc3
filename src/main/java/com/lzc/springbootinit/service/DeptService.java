package com.lzc.springbootinit.service;

import com.lzc.springbootinit.mybatis.pojo.Dept;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 部门管理
 */
@Component
public interface DeptService {

    List<Dept> list();

    Integer delete(Integer id);

    Object add(Dept dept);

}
