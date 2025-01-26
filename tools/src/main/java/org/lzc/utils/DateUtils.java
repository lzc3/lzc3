package org.lzc.utils;

import org.lzc.common.DayOfWeekConstant;

import java.time.YearMonth;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DateUtils {

    public static void main(String[] args) {
        YearMonth yearMonth = YearMonth.of(2024, 12);
        System.out.println(yearMonth.lengthOfMonth());

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        System.out.println(year + month);

    }

    public static String buildString(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month, day);
    }


    /**
     * 获取指定年份，月份对应的工作日及其周几数
     * @param year 年份
     * @param month 月份
     * @return map
     */
    public static Map<String, Integer> getWorkdaysOfDecember(Integer year, Integer month) {

        YearMonth yearMonth = YearMonth.of(year, month);
        int dayOfMonth = yearMonth.lengthOfMonth();

        Map<String, Integer> workdays = new HashMap<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, dayOfMonth);
        LocalDate currentDate = startDate;

        while (currentDate.isBefore(endDate.plusDays(1))) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (DayOfWeekConstant.isWorkDay(dayOfWeek)) {
                String dateKey = buildString(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
                int dayValue = DayOfWeekConstant.transDayOfWeekToNum(dayOfWeek);
                workdays.put(dateKey, dayValue);
            }
            currentDate = currentDate.plusDays(1);
        }

        return new TreeMap<>(workdays);
    }
}
