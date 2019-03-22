package com.ixyxj.utils.date;

import static com.ixyxj.utils.date.Lunar.Gan;
import static com.ixyxj.utils.date.Lunar.Zhi;
import static com.ixyxj.utils.date.Lunar.chineseAnimals;
import static com.ixyxj.utils.date.Lunar.chineseNumber;
import static com.ixyxj.utils.date.Lunar.chineseTen;
import static com.ixyxj.utils.date.Lunar.lunarInfo;

/**
 * created by silen on 2019/3/22 14:02
 * 农历工具类
 */
public class LunarUtil {

    /**
     * 获取农历日期
     * @param day
     * @return
     */
    public static String getChinaDay(int day) {
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30)
            return "";
        if (day == 10)
            return "初十";
        else
            return chineseTen[day / 10] + chineseNumber[n];
    }

    /**
     * 传回农历 y年的总天数
     * @param y 年
     * @return 总天数
     */
    public static int getYearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0)
                sum += 1;
        }
        return (sum + getLeapDays(y));
    }

    /**
     * 传回农历 y年闰月的天数
     * @param y 年
     * @return 天数
     */
    public static int getLeapDays(int y) {
        if (getLeapMonth(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0)
                return 30;
            else
                return 29;
        } else
            return 0;
    }

    /**
     * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
     * @param y 年
     * @return 闰月
     */
    public static int getLeapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 传回农历 y年m月的总天数
     * @param y 年
     * @param m 月
     * @return 天数
     */
    public static int getMonthDays(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }

    /**
     * 传回农历 y年的生肖
     * @return
     */
    public static String getAnimalYear(int year) {
        return chineseAnimals[(year - 4) % 12];
    }

    /**
     * 传入 月日的offset 传回干支, 0=甲子
     * @param num
     * @return
     */
    private static String getCyclicalNum(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /**
     * 传入 offset 传回干支, 0=甲子
     * @param year 年
     * @return 干支
     */
    public static String getCyclical(int year) {
        int num = year - 1900 + 36;
        return (getCyclicalNum(num));
    }
}
