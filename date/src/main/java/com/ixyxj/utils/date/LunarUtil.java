package com.ixyxj.utils.date;

import java.util.Calendar;
import java.util.Date;

import static com.ixyxj.utils.date.Lunar.Gan;
import static com.ixyxj.utils.date.Lunar.Zhi;
import static com.ixyxj.utils.date.Lunar.chineseAnimals;
import static com.ixyxj.utils.date.Lunar.chineseNumber;
import static com.ixyxj.utils.date.Lunar.chineseTen;
import static com.ixyxj.utils.date.Lunar.lunarInfo;
import static com.ixyxj.utils.date.SolarTerm.solarTermInfo;

/**
 * created by silen on 2019/3/22 14:02
 * 农历工具类
 */
public class LunarUtil {

    /**
     * 获取农历日期
     *
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
     *
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
     *
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
     *
     * @param y 年
     * @return 闰月
     */
    public static int getLeapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 传回农历 y年m月的总天数
     *
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
     *
     * @return
     */
    public static String getAnimalYear(int year) {
        return chineseAnimals[(year - 4) % 12];
    }

    /**
     * 传入 月日的offset 传回干支, 0=甲子
     *
     * @param num
     * @return
     */
    private static String getCyclicalNum(int num) {
        return Gan[num % 10] + Zhi[num % 12];
    }

    /**
     * 传入 offset 传回干支, 0=甲子
     *
     * @param year 年
     * @return 干支
     */
    public static String getCyclical(int year) {
        int num = year - 1900 + 36;
        return getCyclicalNum(num);
    }

    private static String getCyclical2(int year) {
        int num = year - 1900 + 36 - 1;
        return getCyclicalNum(num);
    }

    /**
     * 干支年计算
     *
     * @param year 年
     * @return 干支
     */
    @Deprecated
    public static String getCyclicalY(int year) {
        int num = (year - 3) % 10;//干
        String gan = Gan[num == 0 ? 9 : num - 1];
        num = (year - 3) % 12;
        String zhi = Zhi[num == 0 ? 11 : num - 1];
        return gan + zhi;
    }

    /**
     * 干支月计算
     *
     * @param year  年
     * @param month 月
     * @return 干支
     */
    @Deprecated
    public static String getCyclicalM(int year, int month) {
        int num = (((year - 3) % 10) * 2 + month) % 10;//干
        String gan = Gan[num == 0 ? 9 : num - 1];
        String zhi = Zhi[(month + 1) % 12];
        return String.format("%s年%s月", getCyclicalYear(year), gan + zhi);
    }

    /**
     * 天干地支包含时
     *
     * @param date 日期
     * @return 天干地支所有
     */
    public static String getCyclicalString(String date) {
        Date da = DateUtil.parseToDate(date);
        return getCyclicalString(da);
    }

    /**
     * 传入date
     *
     * @param date 日期
     * @return 天干地支所有
     */
    public static String getCyclicalString(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getCyclicalString(c);
    }

    private static String getCyclicalString(Calendar c) {
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DATE);

        //干支
        String cy, cm, cd, ch;
        if (m < 2) {
            cy = getCyclical2(y);
        } else {
            cy = getCyclical(y);
        }
        int t = getSolarTermDay(y, 2); //spring
        int node = getSolarTermDay(y, m * 2);
        cm = getCyclicalNum((y - 1900) * 12 + m + 12);

        // 依节气调整二月分的年柱, 以立春为界
        if (m == 1 && (d) >= t)
            cy = getCyclical(y);
        // 依节气月柱, 以「节」为界
        if (d >= node)
            cm = getCyclicalNum((y - 1900) * 12 + m + 13);
        // 日柱
        cd = getCyclicalNum(DateUtil.getIntervalDays(DateUtil.parseToDate(y + "/" + m), DateUtil.parseToDate(y + "/01/01")) + d + 1);
        // 时柱
        int hour = c.get(Calendar.HOUR_OF_DAY);
        ch = Gan[hourG(cd.substring(0, 1), hour) % 10] + Zhi[hourZ(hour) % 12];
        return String.format("%s年%s月%s日%s时", cy, cm, cd, ch);
    }

    /**
     * 获取干支年
     *
     * @param year
     * @return
     */
    public static String getCyclicalYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        return getCyclicalString(c).substring(0, 3);
    }

    /**
     * 获取月干支
     *
     * @param year
     * @param month
     * @return
     */
    public static String getCyclicalMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        return getCyclicalString(c).substring(0, 6);
    }

    /**
     * 获取日干支
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getCyclicalDay(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        return getCyclicalString(c).substring(0, 9);
    }

    /**
     * 根据 日干 推算 时柱 根据提供的推算图来计算
     *
     * @param dG
     * @param hour
     * @return
     */
    private static int hourG(String dG, int hour) {
        int ind = 1;
        for (String s : Gan) {
            if (s.equals(dG)) {
                break;
            }
            ind++;
        }
        ind = ind % 5; // 五个为一周期
        int i = hourZ(hour);
        if (i > 10)
            return i - 10 + (ind - 1) * 2;
        else {
            i = i + (ind - 1) * 2;
            return i >= 10 ? i - 10 : i;
        }
    }

    /**
     * 返回 小时对应的 支的索引
     *
     * @param hour
     * @return
     */
    private static int hourZ(int hour) {
        if (hour >= 23 || hour < 1)
            return 0;
        else if (hour < 3)
            return 1;
        else if (hour < 5)
            return 2;
        else if (hour < 7)
            return 3;
        else if (hour < 9)
            return 4;
        else if (hour < 11)
            return 5;
        else if (hour < 13)
            return 6;
        else if (hour < 15)
            return 7;
        else if (hour < 17)
            return 8;
        else if (hour < 19)
            return 9;
        else if (hour < 21)
            return 10;
        else
            return 11;
    }

    public static int getSolarTermDay(int y, int n) {
        long times = 31556925974L * (y - 1900) + solarTermInfo[n] * 60000L
                + (long) (0.7 * (y - 1900));
        Date offDate = new Date(times - 2208549300000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(offDate);
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return (cal.get(Calendar.DATE));
    }
}
