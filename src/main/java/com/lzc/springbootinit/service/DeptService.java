package com.lzc.springbootinit.service;

import com.lzc.springbootinit.mybatis.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */

public interface DeptService {

    List<Dept> list();

    Integer delete(Integer id);

    Object add(Dept dept);

}
