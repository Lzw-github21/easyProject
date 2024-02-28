package easyProject.时间;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    Calendar c = Calendar.getInstance();

    public static void main(String[] args) {
        // 如果不设置时间，则默认为当前时间
        Calendar calendar = Calendar.getInstance();
        // 将系统当前时间赋值给 Calendar 对象
        calendar.setTime(new Date());
        // 获取当前时间
        System.out.println("现在时刻：" + calendar.getTime());
        //把当前的年份增加两年
        calendar.add(Calendar.YEAR, 2);
        //把当前的月份减少3个月
        calendar.add(Calendar.MARCH, -3);
        // 获取当前年份
        int year = calendar.get(Calendar.YEAR);
        System.out.println("现在是" + year + "年");
        // 获取当前月份（月份从 0 开始，所以加 1）
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.print(month + "月");
        // 获取日
        int day = calendar.get(Calendar.DATE);
        System.out.print(day + "日");
        // 获取今天星期几（以星期日为第一天）
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.print("星期" + week);
        // 获取当前小时数（24 小时制）
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.print(hour + "时");
        // 获取当前分钟
        int minute = calendar.get(Calendar.MINUTE);
        System.out.print(minute + "分");
        // 获取当前秒数
        int second = calendar.get(Calendar.SECOND);
        System.out.print(second + "秒");
        // 获取毫秒数
        int millisecond = calendar.get(Calendar.MILLISECOND);
        System.out.print(millisecond + "毫秒");
        // 获取今天是本月第几天
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("今天是本月的第 " + dayOfMonth + " 天");
        // 获取今天是本月第几周
        int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println("今天是本月第 " + dayOfWeekInMonth + " 周");
        // 获取今天是今年第几天
        int many = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("今天是今年第 " + many + " 天");
        Calendar c = Calendar.getInstance();
        // 设置年月日，时分秒将默认采用当前值
        c.set(2012, 8, 8);
        // 输出时间
        System.out.println("设置日期为 2012-8-8 后的时间：" + c.getTime());
    }
}
