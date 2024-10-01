package com.lzc.springbootinit.service.impl;


import com.lzc.springbootinit.mybatis.mapper.DeptMapper;
import com.lzc.springbootinit.mybatis.pojo.Dept;
import com.lzc.springbootinit.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public Integer delete(Integer id) {
        return deptMapper.deleteById(id);
    }

    @Override
    public Object add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
        return dept;
    }
}
