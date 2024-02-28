package easyProject.时间;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();

        //Tue Feb 27 16:10:56 CST 2024
        System.out.println(date);


        //日期转化为毫秒
        Long time = date.getTime();
        System.out.println(time);

        //两个Date比较
        Date anotherDate = new Date(1709021639857L);
        System.out.println(anotherDate.compareTo(date));
    }
}
