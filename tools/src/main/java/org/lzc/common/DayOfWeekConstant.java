package org.lzc.common;

import java.time.DayOfWeek;

public interface DayOfWeekConstant {


    static boolean isWorkDay(DayOfWeek dayOfWeek) {
        return dayOfWeek!= DayOfWeek.SATURDAY && dayOfWeek!= DayOfWeek.SUNDAY;
    }


    static int transDayOfWeekToNum(DayOfWeek dayOfWeek) {
        int dayValue;
        switch (dayOfWeek) {
            case MONDAY:
                dayValue = 1;
                break;
            case TUESDAY:
                dayValue = 2;
                break;
            case WEDNESDAY:
                dayValue = 3;
                break;
            case THURSDAY:
                dayValue = 4;
                break;
            case FRIDAY:
                dayValue = 5;
                break;
            case SATURDAY:
                dayValue = 6;
                break;
            case SUNDAY:
                dayValue = 7;
                break;
            default:
                dayValue = 0;
                break;
        }
        return dayValue;
    }

}
