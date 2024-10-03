package com.lzc.springbootinit.service.impl;


import com.lzc.springbootinit.config.annotation.TestAnnotation;
import com.lzc.springbootinit.mybatis.mapper.DeptMapper;
import com.lzc.springbootinit.mybatis.mapper.EmpMapper;
import com.lzc.springbootinit.mybatis.pojo.Dept;
import com.lzc.springbootinit.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;
    private final EmpMapper empMapper;
    @Override
    @TestAnnotation
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Integer delete(Integer id) {
        Integer integer = deptMapper.deleteById(id);

        empMapper.deleteByDeptId(id);
        return integer;
    }

    @Override
    public Object add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
        return dept;
    }
}
