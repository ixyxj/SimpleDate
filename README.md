### SimpleDate
:dash: 最全的日期操作工具 Full date option tool

[![](https://jitpack.io/v/ixyxj/SimpleDate.svg)](https://jitpack.io/#ixyxj/SimpleDate)

### Feature
- 时间日期转换 所有时间格式 DateStyle
- 获取星期几
```
Week week = DateUtil.getWeek("2019/3/22")
```
- 获取时间间隔和年龄
- 农历相关操作
- 获取生肖
```
LunarUtil.getAnimalYear(1995)
```
- 天干地支转换
```
LunarUtil.getCyclicalString("2019/3/23")
```
- 获取季节
```
DateUtil.getSeason("2019/3/21");
//spring
```
- 指定日期是否为二十四节气
```
SolarUtil.getTermName(2019, 3, 6)
```

### Usage
> 相关案例都在单元测试中

Gradle引入：
project build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
app build.gradle
```
dependencies {
        implementation 'com.github.ixyxj:SimpleDate:1.0.1'
}
```

Maven 引入：
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
依赖：
```
<dependency>
    <groupId>com.github.ixyxj</groupId>
    <artifactId>SimpleDate</artifactId>
    <version>1.0.0</version>
</dependency>
```

### End
give me a like!!!
