package com.blog.utils;


import org.quartz.CronExpression;

import java.util.Date;

/**
 * Cron表达式工具类
 *
 * @author hy
 * @version 1.0
 */
public class CronUtil {

    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    public static String getInvalidMessage(String cronExpression) {
        try {
            new CronExpression(cronExpression);
            return null;
        } catch (Exception pe) {
            return pe.getMessage();
        }
    }

    public static Date getNextExecution(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}