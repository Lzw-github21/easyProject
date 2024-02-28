package easyProject.时间;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {
    public static void main(String[] args) throws ParseException {

        // 创建一个 SimpleDateFormat 对象  不是线程安全的,
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 创建一个 Date 对象
        Date date = new Date();

        // 将当前时间以指定的格式转换成为字符串
        String str1 = sdf.format(date);
        System.out.println(str1);

        Date str2 = sdf.parse(str1);
        System.out.println(str2);

        // 按照格式创建一个字符串对象
        String dateStr = "2008-08-08 12:34:45";
        Date newDate = sdf.parse(dateStr);
        System.out.println(newDate);

        String dateNew = sdf.format(newDate);
        System.out.println(dateNew);
    }

}

//下边代码演示为什么不是线程安全的
//Date now =new Date(1985,9,23);
//        System.out.println(now);
//
////2.时间格式化和解析是线程不安全的
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < 50; i++) {
//        new Thread(()->{
//        try {
//Date date = sdf.parse("2019-09-09");
//                    System.out.println("date:"+date);
//                } catch (ParseException e) {
//        e.printStackTrace();
//                }
//                        }).start();
//        }


// 多线程测试结果：(会有日期格式化错误的，还有直接报错的，说明线程是不安全)

