package project;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.entity.User;

@SpringBootApplication
@MapperScan("project.mapper")
public class MybatisServerApplication {
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(MybatisServerApplication.class, args);
        System.out.println("==========================启动成功================================");
    }
}
