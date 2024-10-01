package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.mybatis.pojo.Emp;
import com.lzc.springbootinit.mybatis.pojo.PageBean;
import com.lzc.springbootinit.pojo.ResponseVo;
import com.lzc.springbootinit.service.impl.EmpServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/emps")
public class EmpController {

    private final EmpServiceImpl empService;

    @GetMapping()
    public ResponseVo page(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询，参数为：{}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return ResponseVo.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public ResponseVo delete(@PathVariable List<Integer> ids) {
        log.info("批量删除，ids：{}", ids);
        empService.delete(ids);
        return ResponseVo.success();
    }

    @PostMapping()
    public ResponseVo save(@RequestBody Emp emp) {
        log.info("新增员工, {}", emp);
        empService.save(emp);
        return ResponseVo.success();
    }


}
