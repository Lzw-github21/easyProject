package project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//不加exclude = {DataSourceAutoConfiguration.class}属性，运行报错Reason: Failed to determine a suitable driver class
public class DbHelpServerApplication {
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(DbHelpServerApplication.class, args);
        System.out.println("==========================启动成功================================");
    }
}
