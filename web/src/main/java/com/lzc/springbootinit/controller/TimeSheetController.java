package com.lzc.springbootinit.controller;

import com.alibaba.fastjson2.JSONObject;
import com.lzc.springbootinit.config.TimesheetProperties;
import com.lzc.springbootinit.service.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.lzc.pojo.ResponseVo;
import org.lzc.utils.DateUtils;
import org.lzc.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class TimeSheetController {


    private final TimesheetService timesheetService;

    @RequestMapping("/timeSheet")
    public ResponseVo timeSheet(@RequestBody Object data) {
        return timesheetService.sendTimeSheet(data);
    }


}
