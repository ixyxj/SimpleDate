package com.ixyxj.utils.date;

import org.junit.Test;

import java.util.Calendar;

/**
 * created by zhihong on 2019/3/22 14:28
 */
public class LunarUtilTest {
    @Test
    public void getAnimalYear() {
        String year = LunarUtil.getAnimalYear(1995);
        System.out.println(year);
    }

    @Test
    public void getLunar() {
        Lunar lunar = new Lunar(Calendar.getInstance());
        System.out.println("" + lunar.getYear() + lunar.getMonth() + lunar.getDay());
    }
    @Test
    public void getGanZhi() {
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.parseToDate("2019/3/23"));
        System.out.println(c.get(Calendar.DATE));
        System.out.println(LunarUtil.getCyclicalString("2019/3/23"));
        System.out.println(LunarUtil.getCyclicalYear(2019));
        System.out.println(LunarUtil.getCyclicalMonth(2019, 3));
        System.out.println(LunarUtil.getCyclicalDay(2019, 3, 23));
    }
}
