package easyProject.java多态;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class DuoTai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String a = "哈哈";
        System.out.println(a);
        byte[] b = a.getBytes(StandardCharsets.UTF_8);
        System.out.println(b);
        System.out.println(b[1]);
        System.out.println(b[0]);
        System.out.println(Arrays.toString(a.getBytes(StandardCharsets.UTF_8)));

        String ab = "aa";
        ab = ab + "bb";
        System.out.println(ab);

        System.out.println("\uD840\uDC38");
        //4E00-9FA5是基本汉字,只占一个字符，也就是一个char，也就是2字节，也就是16位
        String s = "一";//Unicode编码:4E00
        String s1 = "龥";//Unicode编码:9FA5
        //?是汉字扩展字符,占两个字符，也就是两个char，也就是4字节，也就是32位
        String s2 = "?";//Unicode编码:20000
        System.err.println("测试字符s:" + s);
        System.err.println("测试字符s2:" + s2);
        System.err.println("测试字符s长度:" +s.length());
        System.err.println("测试字符s2长度:" +s2.length());
        System.out.println("s转为二进制:" + Integer.toBinaryString(s.charAt(0)));
        System.out.println("s2转为二进制:" + Integer.toBinaryString(s2.charAt(0)) + "-" + Integer.toBinaryString(s2.charAt(1)));

        //char c='?';//当我们设置一个占多字符的汉字给char的时候，编译器会报错
    }
}
