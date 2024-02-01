package project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbHelpServerApplication {
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(DbHelpServerApplication.class, args);
        System.out.println("==========================启动成功================================");
    }
}
