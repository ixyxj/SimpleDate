package com.ixyxj.utils.date;

import org.junit.Test;

/**
 * created by zhihong on 2019/3/22 14:28
 */
public class LunarUtilTest {
    @Test
    public void getAnimalYear() {
        String year = LunarUtil.getAnimalYear(1995);
        System.out.println(year);
    }
}
