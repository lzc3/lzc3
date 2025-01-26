package com.lzc.springbootinit.service;


import com.alibaba.fastjson2.JSONObject;
import com.lzc.springbootinit.config.TimesheetProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lzc.pojo.ResponseVo;
import org.lzc.utils.DateUtils;
import org.lzc.utils.HttpUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimesheetService {

    private final TimesheetProperties timesheetProperties;

    private Map<String, Integer> generateWorkDaysMap() {
        Map<String, Integer> workdaysMap = DateUtils.getWorkdaysOfDecember(timesheetProperties.getYear(), timesheetProperties.getMonth());
        List<String> blackMonthDay = timesheetProperties.getBlackMonthDay();
        Optional.ofNullable(blackMonthDay)
                .orElseGet(ArrayList::new)
                .forEach(workdaysMap::remove);
        return workdaysMap;
    }

    private HttpHeaders generateTimeSheetHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        addTimeSheetHeader(headers);
        return headers;
    }

    private void addTimeSheetHeader(HttpHeaders headers) {
        headers.add("xdapappid", timesheetProperties.getXdapappid());
        headers.add("xdaptenantid", timesheetProperties.getTenantid());
        headers.add("xdaptimezone", timesheetProperties.getTimeZone());
        headers.add("xdaptimestamp", new Date().getTime() + "0");
        headers.add("xdaptoken", timesheetProperties.getToken());
    }

    interface PostFunction {
        String post(String url, JSONObject jsonObject, HttpHeaders headers);
    }

    private void workdaysForeachPost(Object data, PostFunction postFunction) {
        Map<String, Integer> workdaysMap = generateWorkDaysMap();
        HttpHeaders headers = generateTimeSheetHeader();

        String url = timesheetProperties.getUrl();
        String monthDayUuid = timesheetProperties.getMonthDayUuid();
        String weekDayUuid = timesheetProperties.getWeekDayUuid();

        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
        JSONObject businessDataJsonObject = null;
        Object businessData = jsonObject.get("data");
        if (businessData instanceof JSONObject) {
            businessDataJsonObject = (JSONObject) businessData;
        } else {
            throw new IllegalArgumentException("The 'data' field in jsonObject is not a JSONObject");
        }

        for (Map.Entry<String, Integer> entry : workdaysMap.entrySet()) {
            businessDataJsonObject.put(monthDayUuid, entry.getKey());
            businessDataJsonObject.put(weekDayUuid, entry.getValue());
//            jsonObject.put("data", businessDataJsonObject);

            System.out.println("stop");
            // send request
//            String postResult = postFunction.post(url, jsonObject, headers);
//            log.info(postResult);
        }
    }

    public ResponseVo sendTimeSheet(Object data) {
        workdaysForeachPost(data, HttpUtils::post);
        return ResponseVo.success("postResult");
    }

}
