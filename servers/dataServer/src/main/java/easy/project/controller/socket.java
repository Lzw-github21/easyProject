package easy.project.controller;//package ecay.project.controller;
//
//import org.apache.commons.lang.time.DateFormatUtils;
//import org.apache.commons.lang.time.DateUtils;
//
//import java.text.ParseException;
//
//public class socket {
//    public static void main(String args[]){
////        BufferedReader buf = null ;		// 声明对象
////        buf = new BufferedReader(new InputStreamReader(System.in)) ;	// 将字节流变为字符流
////        String str = null ;	// 接收输入内容
////        System.out.print("请输入内容：") ;
////        try{
////            str = buf.readLine() ;	// 读取一行数据
////        }catch(IOException e){
////            e.printStackTrace() ;	// 输出信息
////        }
////        System.out.println("输入的内容为：" + str) ;
//        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
//        socket s = new socket();
//        System.out.println(s.dealWithDate(null));
//
//    }
//
//    public String dealWithDate(String key) {
//        if(key ==null)
//            return null;
//        String[] parsePatterns = {"yyyy-MM-dd", "yyyy年MM月dd日",
//                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
//                "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};
//        try {
//            String strDateFormat = "yyyy-MM-dd";
//            //String data = DateFormatUtils.format(DateUtils.parseDate(key, parsePatterns), strDateFormat);
//            return null;
//        } catch (ParseException e) {
//            return null;
//        }
//    }
//}
