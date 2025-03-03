package com.lzc.springbootinit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@PropertySource("classpath:timesheet.properties")
@ConfigurationProperties(prefix = "timesheet")
public class TimesheetProperties {

    String url = "";

    String tenantid = "241251302414221313";
    String xdapappid = "291278919397539840";
    String timeZone = "+08:00";
    String token;

    String monthDayUuid;
    String weekDayUuid;

    Integer year;
    Integer month;

    List<String> blackMonthDay;
    List<Integer> addWorkDay;
}
