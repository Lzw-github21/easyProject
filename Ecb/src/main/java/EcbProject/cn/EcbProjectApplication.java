package EcbProject.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("EcbProject.cn.controller.mybatisplus")
//@MapperScan("EcbProject.cn.controller.Transactional")
public class EcbProjectApplication{
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(EcbProjectApplication.class,args);
        System.out.println("==========================启动成功================================");
    }
}