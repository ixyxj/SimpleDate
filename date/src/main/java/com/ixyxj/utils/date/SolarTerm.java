package com.ixyxj.utils.date;

import java.util.HashMap;
import java.util.Map;

/**
 * For more information, you can visit https://github.com/ixyxj,
 * or contact me by xyxjun@gmail.com
 *
 * @author xyxj on 2019/3/23 23:25
 * Copyright (c) 2019 in FORETREE
 */
public class SolarTerm {
    /**
     * 节气D值
     */
    private static final double D = 0.2422;
    /**
     * 20世纪的节气C值
     */
    private static final double[] C_20 = {
            6.11, 20.84, 4.6295, 19.4599, 6.3826, 21.4155,
            5.59, 20.888, 6.318, 21.86, 6.5, 22.2, 7.928,
            23.65, 8.35, 23.95, 8.44, 23.822, 9.098, 24.218,
            8.218, 23.08, 7.9, 22.6};
    /**
     * 21世纪的节气C值
     */
    private static final double[] C_21 = {
            5.4055, 20.12, 3.87, 18.73, 5.63, 20.646, 4.81,
            20.1, 5.52, 21.04, 5.678, 21.37, 7.108, 22.83,
            7.5, 23.13, 7.646, 23.042, 8.318, 23.438, 7.438,
            22.36, 7.18, 21.94};

    final static long[] solarTermInfo = new long[]{
            0, 21208, 42467, 63836, 85337, 107014, 128867,
            150921, 173149, 195551, 218072, 240693, 263343,
            285989, 308563, 331033, 353350, 375494, 397447,
            419210, 440795, 462224, 483532, 504758};

    final static String[] solarTerm = {
            "小寒", "大寒", "立春", "雨水", "惊蛰", "春分",
            "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
            "小暑", "大暑", "立秋", "处暑", "白露", "秋分",
            "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"
    };

    /**
     * 节气缓存表
     */
    private static Map<Integer, Map<String, String>> termMap = new HashMap<>();


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


    /**
     * 获得制定年份的表
     *
     * @param year
     * @return
     */
    static Map<String, String> getYearTermMap(int year) {
        //处理C值
        double[] c;
        c = C_21;
        if (year > 1900 && year <= 2000) {
            c = C_20;
        }

        Map<String, String> map = termMap.get(year);
        if (map != null) return map;

        synchronized (solarTerm) {
            map = new HashMap<>();
            int y = year % 100;
            for (int i = 0; i < 24; i++) {
                //计算节气公式：(yd + c) * l
                int date;
                if (i < 2 || i > 22) {
                    date = (int) ((y * D + c[i]) - (y - 1) / 4);
                } else {
                    date = (int) ((y * D + c[i]) - (y / 4));
                }
                map.put(getTermKey(i / 2 + 1, date), solarTerm[i]);
            }
            termMap.put(year, map);
        }
        return map;
    }

    /**
     * 组装节气key
     *
     * @param month
     * @param date
     * @return
     */
    static String getTermKey(int month, int date) {
        String key = String.valueOf(month);
        if (month < 10) key = "0" + key;
        if (date < 10) key += "0";
        key += date;
        return key;
    }
}
