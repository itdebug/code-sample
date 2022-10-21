package org.example.regex;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TaskFrequency {

    ONCE("一次性"),
    DAILY("每日"),
    WEEKLY("每周"),
    MONTHLY("每月"),
    YEARLY("每年");

    private final String msg;
    private static final Map<String, TaskFrequency> all;

    TaskFrequency(String msg) {
        this.msg = msg;
    }

    public static TaskFrequency getItem(String name) {
        if (name == null) {
            return ONCE;
        }
        name = name.toUpperCase();
        return all.get(name) == null ? ONCE : all.get(name);
    }

    public static String getMsg(String name) {
        TaskFrequency item = getItem(name);
        return item == null ? "" : item.getMsg();
    }

    /**
     * 根据频率类型判断 判断是否是规定的时间点
     */
    public boolean isTrue(LocalDate day, int pushDay) {
        // 小于0时 不关心频率类型--直接判断是否本月最后一天
        if (pushDay < 0) {
            return day.compareTo(day.with(TemporalAdjusters.lastDayOfMonth())) == 0;
        }

        switch (this) {
            case WEEKLY:
                return pushDay == day.getDayOfWeek().getValue();
            case MONTHLY:
                return pushDay == day.getDayOfMonth();
            case YEARLY:
                return pushDay == day.getDayOfYear();
            default:
                return true;
        }
    }

    public String getMsg() {
        return msg;
    }

    static {
        all = Arrays.stream(TaskFrequency.values()).collect(Collectors.toMap(TaskFrequency::name, Function.identity()));
    }

}
