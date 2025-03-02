//package EcbProject.cn.controller.mybatis;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/mybatis")
//public class MybatisController {
//
////    @Autowired
////    UserMapper userMapper;
//    @RequestMapping("/test")
//    public String test() throws IOException {
//        MybatisUtils.getSqlSessionFactory().openSession();
//        try(SqlSession session = MybatisUtils.getSqlSessionFactory().openSession()){
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            User user = userMapper.getUserById(1);
//            System.out.println(JSON.toJSONString(user));
//        }
//        return "Hello, Mybatis!";
//    }
//    @RequestMapping("/test2")
//    public String test2() throws IOException {
//        MybatisUtils.getSqlSessionFactory().openSession();
//        try(SqlSession session = MybatisUtils.getSqlSessionFactory().openSession()){
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            User user = userMapper.getUserById2(1);
//            System.out.println(JSON.toJSONString(user));
//            System.out.println(userMapper.getAllUsers2());
//        }
//        return "Hello, Mybatis!";
//    }
//
//
//}
