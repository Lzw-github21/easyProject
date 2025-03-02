//package EcbProject.cn.controller.fastJson测试;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONWriter;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//
//public class FastJson1Test {
//    public static void main(String[] args) {
//        JsonUser user = new JsonUser();
//        user.setName("张三");
//        user.setAge(20);
//        user.setBirthday(new Date());
//        user.setLocalDate(java.time.LocalDate.now());
//        user.setLocalTime(java.time.LocalTime.now());
//        user.setLocalDateTime(java.time.LocalDateTime.now());
//        user.setSqlDate(new java.sql.Date(System.currentTimeMillis()));
//        user.setSqlTimeStamp(new java.sql.Timestamp(System.currentTimeMillis()));
//        //fastJson1写法
////        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
////        String json = JSON.toJSONString(user, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
//        //fastJson2写法
//        String json = JSON.toJSONString(user, "yyyy-MM-dd HH:mm:ss");
//        System.out.println(json);
//
//
//        //测试2
//        LinkedHashMap<String,Object>  map = new LinkedHashMap<>();
//        map.put("name",null);
//        map.put("age",user.getAge());
//        map.put("birthday",user.getBirthday());
//        map.put("localDate",user.getLocalDate());
//        map.put("localTime",user.getLocalTime());
//        map.put("localDateTime",user.getLocalDateTime());
//        map.put("sqlDate",user.getSqlDate());
//        map.put("sqlTimeStamp",user.getSqlTimeStamp());
////        String json2 = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
//        String json2 = JSON.toJSONString(map, "yyyy-MM-dd HH:mm:ss");
//        // 将 JSON 字符串转换为 HashMap
//        HashMap<String, Object> map2 = (HashMap<String, Object>)JSON.parseObject(json2, HashMap.class);
//        System.out.println(json2);
//        System.out.println(map2);
//    }
//}
