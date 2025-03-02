//package EcbProject.cn.controller.mybatis;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class MybatisUtils{
//
//    private static SqlSessionFactory sqlSessionFactory = null;
//
//    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
//        if(sqlSessionFactory == null){
//            String resource = "mybatis-config.xml";
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "org/mybatis/example/mybatis-config.xml");
//        }
//        return sqlSessionFactory;
//    }
//}
