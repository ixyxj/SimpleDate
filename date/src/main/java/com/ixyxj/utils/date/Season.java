package com.ixyxj.utils.date;

/**
 * created by silen on 2018/3/22 13:33
 * 季节枚举 春夏秋冬
 */
public enum Season {
    spring("春"),
    summer("夏"),
    autumn("秋"),
    winter("冬");

    //中文名
    public String cnName;
    Season(String cnName) {
        this.cnName = cnName;
    }
}
