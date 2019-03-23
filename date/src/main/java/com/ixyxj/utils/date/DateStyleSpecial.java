package com.ixyxj.utils.date;


/**
 * created by silen on 2018/3/22 13:33
 * 不常用的日期格式
 */
public interface DateStyleSpecial {
    String YYYYMM = "yyyyMM";
    String YYYYMMDD = "yyyyMMdd";

    /**
     * 带空格
     */
    String YYYYMM_HH_MM = "yyyyMMdd HH:mm";
    String YYYYMM_HH_MM_SS = "yyyyMMdd HH:mm:ss";
    /**
     * 不带空格
     */
    String YYYYMMHH_MM = "yyyyMMddHH:mm";
    String YYYYMMHHMM_SS = "yyyyMMddHH:mm:ss";

    /**
     * 无符号
     */
    String YYYYMMHHMM = "yyyyMMddHHmm";
    String YYYYMMHHMMSS = "yyyyMMddHHmmss";
}