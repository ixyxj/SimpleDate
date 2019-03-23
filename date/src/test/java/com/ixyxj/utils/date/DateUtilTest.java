package com.ixyxj.utils.date;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * created by zhihong on 2019/3/22 13:42
 */
public class DateUtilTest {

    @Test
    public void formatToString() {
        String date = "20190323";
        String str1 = DateUtil.formatToString(date, "yyyyMMdd", DateStyle.YYYY_MM_CN);
        System.out.println(str1);
        System.out.println(DateUtil.getWeek(date, "yyyyMMdd").name_cn);
    }

    @Test
    public void getSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parseToDate("2019/1/1"));
        System.out.println(calendar.get(Calendar.MONTH));
    }

    @Test
    public void getWeekTest() {
        Week week = DateUtil.getWeek("2019/3/22");
        assert week == Week.FRIDAY;
    }

    @Test
    public void isDate() {
        boolean is = DateUtil.isDate("12345");
        assertFalse(is);
    }

    @Test
    public void getDateStyle() {
        DateStyle dateStyle = DateUtil.getDateStyle("19/3/22");
        assertEquals("yyyy/MM/dd", dateStyle.getValue());
    }
}