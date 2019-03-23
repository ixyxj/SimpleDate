package com.ixyxj.utils.date;

import java.util.Map;

import static com.ixyxj.utils.date.SolarTerm.getTermKey;
import static com.ixyxj.utils.date.SolarTerm.getYearTermMap;

/**
 * For more information, you can visit https://github.com/ixyxj,
 * or contact me by xyxjun@gmail.com
 *
 * @author xyxj on 2019/3/23 23:51
 * Copyright (c) 2019 in FORETREE
 * 阳历工具类
 */
public class SolarUtil {

    /**
     * 获取指定日期节气名称
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static String getTermName(int year, int month, int date) {
        Map<String, String> map = getYearTermMap(year);
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get(getTermKey(month, date));
    }
}
