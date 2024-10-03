package com.lzc.springbootinit.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzc.springbootinit.mybatis.mapper.EmpMapper;
import com.lzc.springbootinit.mybatis.pojo.Emp;
import com.lzc.springbootinit.mybatis.pojo.PageBean;
import com.lzc.springbootinit.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;


    @Override
    public PageBean page(Integer page,
                         Integer pageSize,
                         String name,
                         Short gender,
                         LocalDate begin,
                         LocalDate end) {

        PageHelper.startPage(page, pageSize);

        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserNameAndPassword(emp);
    }
}
