package easyProject.时间;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//TODO 在 JDK8 之前，我们经常使用到的时间API包括(Date、Calendar)，Date 与字符串之间的转换使用 SimpleDateFormat
// 进行转换（parse()、format() 方法），
//TODO 然而 SimpleDateFormat 不是线程安全的。在设计上也是存在一些缺陷的，比如有两个 Date 类，一个在 java.util 包中，
// 一个在 java.sql 包中。
//TODO 在JDK8 中，引入了一套全新的时间日期API，这套 API 在设计上比较合理，使用时间操作也变得更加方便。
// 并且支持多线程安全操作。
public class LocalDateTest {
    public static void main(String[] args) {

    }
}

/**
 * TODO JDK8之前日期存在的问题
 */
class DateDemo01 {

    public static void main(String[] args) {
        //旧版日期时间 API 存在的问题
        //1.设计不合理(JDK8 Date类已经 @Deprecated 注释,不推荐使用)
        Date now =new Date(1985,9,23);
        System.out.println(now);

        //2.时间格式化和解析是线程不安全的
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    Date date = sdf.parse("2019-09-09");
                    System.out.println("date:"+date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
// TODO 新日期时间 API 介绍
//LocalDate：表示日期，包含：年月日。格式为：2020-01-13
//LocalTime：表示时间，包含：时分秒。格式为：16:39:09.307
//LocalDateTime：表示日期时间，包含：年月日 时分秒。格式为：2020-01-13T16:40:59.138
//DateTimeFormatter：日期时间格式化类
//Instant：时间戳类
//Duration：用于计算 2 个时间(LocalTime，时分秒)之间的差距
//Period：用于计算 2 个日期(LocalDate，年月日)之间的差距
//ZonedDateTime：包含时区的时间


/**
 * TODO 时间日期类
 *      LocalDate、LocalTime、LocalDateTime
 *
 * @author liuzebiao
 * @Date 2020-1-13 13:42
 */
class DateDemo02 {

    /**
     * LocalDate 日期类(年月日)
     */
    public void testLocalDate() {
        //获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println(now);
        //指定日期 LocalDate.of(year,month,day)
        LocalDate date = LocalDate.of(2008, 8, 8);
        System.out.println(date);
        //获取年
        System.out.println("年:" + date.getYear());
        //获取月(英文)
        System.out.println("月(英文):" + date.getMonth());
        //获取月(阿拉伯数字)
        System.out.println("月(数字):" + date.getMonthValue());
        //获取日
        System.out.println("日:" + date.getDayOfMonth());
        //是否是闰年
        System.out.println("是否是闰年:" + date.isLeapYear());
        //...其他方法,自行研究
    }

    /**
     * LocalDate 时间类(时分秒)
     */
    public void testLocalTime() {
        //获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println(now);
        //指定日期 LocalTime.of(hour,minute,second)
        LocalTime date = LocalTime.of(13, 26, 39);
        System.out.println(date);
        //获取时
        System.out.println(date.getHour());
        //获取分
        System.out.println(date.getMinute());
        //获取秒
        System.out.println(date.getSecond());
        //获取纳秒
        System.out.println(now.getNano());
        //...其他方法,自行研究

    }

    /**
     * LocalDateTime 日期时间类(年月日 时分秒)
     */
    public void testLocalDateTime() {
        //LocalDateTime: LocalDate + LocalTime,有年月日 时分秒
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间:"+now);
        //指定日期时间 LocalDateTime.of(year,month,day,hour,minute,second)
        LocalDateTime date = LocalDateTime.of(2018, 7, 23, 18, 59, 31);
        System.out.println(date);
        //获取年
        System.out.println(date.getYear());
        //获取月
        System.out.println(date.getMonth());
        //获取日
        System.out.println(date.getDayOfMonth());
        //获取时
        System.out.println(date.getHour());
        //获取分
        System.out.println(date.getMinute());
        //获取秒
        System.out.println(date.getSecond());
        //...其他方法,自行研究
    }

    /**
     * 修改时间
     */
    public void modifyTime() {
        //以LocalDateTime为例(LocalDate、LocalTime与此类似)
        LocalDateTime now = LocalDateTime.now();
        //修改年[修改时间(不是JDK8之前的setXXX(),而是使用withXXX())]
        System.out.println("修改年后:" + now.withYear(9102));
        //增加年(减使用 minusYear()方法)
        System.out.println("+2年后:" + now.plusYears(2));
        //增加日(减使用 minusDays()方法)
        System.out.println("47天后:" + now.plusDays(47));
        //...其他方法,自行研究
    }

    /**
     * 时间比较
     */
    public void compareTime() {
        //以LocalDateTime为例(LocalDate、LocalTime与此类似)
        //时间1
        LocalDateTime now = LocalDateTime.now();
        //时间2
        LocalDateTime dateTime = LocalDateTime.of(2018, 7, 12, 13, 28, 51);
        //判断前面日期是否在后面日期后
        System.out.println("A时间是否晚于B时间:" + now.isAfter(dateTime));
        //判断前面日期是否在后面日期前
        System.out.println("A时间是否早于B时间:" + now.isBefore(dateTime));
        //判断两个日期时间是否相等
        System.out.println("两个时间是否相等:" + now.isEqual(dateTime));
        //...其他方法,自行研究
    }
}

/**
 * TODO 日期时间格式化 + 解析 + 多线程执行(安全)
 *
 * @author liuzebiao
 * @Date 2020-1-13 14:12
 */
class DateDemo03 {

    public void dateFormat(){
        LocalDateTime now = LocalDateTime.now();

        //格式化
        //使用JDK自带的时间格式:ISO_DATE_TIME(默认提供了很多格式,请自行查看)
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        String format = now.format(dtf);
        System.out.println("format="+format);

        //指定时间格式(ofPattern()方法)
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String format1 = now.format(dtf1);
        System.out.println(format1);

        //解析(parse()方法)
        LocalDateTime parse = LocalDateTime.parse(format1, dtf1);
        System.out.println("parse="+parse);

        /**
         * 多线程执行(验证线程安全性)
         * 1.返回结果正确   2.不抛异常
         */
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                LocalDateTime parse1 = LocalDateTime.parse(format1, dtf1);
                System.out.println("parse="+parse1);
            }).start();
        }
    }
}

/**
 * TODO JDK8的 Instant类(时间戳)
 * （主要不是面向用户使用）
 *
 * @author liuzebiao
 * @Date 2020-1-13 14:31
 */
class DateDemo04 {

    public void Instant(){
        //Instant
        // 内部保存了秒和纳秒，一般不是给用户使用的，而是方便程序做一些统计的(比如:统计方法耗时)
        Instant now = Instant.now();
        System.out.println("当前时间戳:"+now);//2020-01-13T06:48:46.267Z
        //Instant类 并没有修改年月日等操作.因为 Instant 本来就不是给用户使用的
        //Instant类:对 秒、纳秒等操作方便
        Instant plus = now.plusSeconds(20);
        System.out.println("+20秒后:"+plus);

        Instant minus = now.minusSeconds(20);
        System.out.println("-20秒后:"+minus);

        //获取秒、毫秒、纳秒
        long second = now.getEpochSecond();
        System.out.println("秒:"+second);
        int nano = now.getNano();
        System.out.println("纳秒:"+nano);
        //...其他方法,自行研究
    }
}


/**
 * TODO JDK8 计算日期时间差值
 *
 * @author liuzebiao
 * @Date 2020-1-13 14:55
 */
class DateDemo05 {

    /**
     * Duration类:计算时间的差值
     */
    public void testTimeDiff(){
        //时间1
        LocalTime now = LocalTime.now();
        //时间2
        LocalTime dateTime = LocalTime.of(8, 15, 46);
        //计算两个时间的差值
        //计算规则:让第二个参数 减去 第一个参数(位置错误可能出现负数)
        Duration duration = Duration.between(dateTime,now);
        System.out.println("相差的天数:"+duration.toDays());
        System.out.println("相差的小时数:"+duration.toHours());
        System.out.println("相差的分钟数:"+duration.toMinutes());
        //System.out.println("相差的秒数:"+duration.toSeconds());//JDK 9+ 出现(JDK8会报错误)
        System.out.println("相差的纳秒数:"+duration.toNanos());
        //...其他方法,自行研究
    }

    /**
     * Period类:计算日期的差值
     */
    public void testDateDiff(){
        //日期1
        LocalDate now = LocalDate.now();
        //日期2
        LocalDate date = LocalDate.of(1999,5,29);
        //计算两个日期的差值
        //计算规则:让第二个参数 减去 第一个参数(位置错误可能出现负数)
        Period period = Period.between(date,now);
        System.out.println("相差的年:"+period.getYears());
        System.out.println("相差的月:"+period.getMonths());
        System.out.println("相差的日:"+period.getDays());
        //...其他方法,自行研究
    }
}