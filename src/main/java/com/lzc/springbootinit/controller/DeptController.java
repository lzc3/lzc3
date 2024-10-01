package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.mybatis.pojo.Dept;
import com.lzc.springbootinit.pojo.ResponseVo;
import com.lzc.springbootinit.service.DeptService;
import com.lzc.springbootinit.service.impl.DeptServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/depts")
public class DeptController {

    //private static Logger log= LoggerFactory.getLogger(DeptController.class);

    private final DeptServiceImpl deptService;


    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping()
    public ResponseVo list() {
        log.info("查询全部部门数据");

        List<Dept> list = deptService.list();

        return ResponseVo.success(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);
        Integer delete = deptService.delete(id);
        return ResponseVo.success(delete);
    }

    @PostMapping()
    public ResponseVo add(@RequestBody Dept dept) {
        log.info("新增部门, 部门为:{}", dept);
        Object add = deptService.add(dept);
        return ResponseVo.success(add);
    }

}
