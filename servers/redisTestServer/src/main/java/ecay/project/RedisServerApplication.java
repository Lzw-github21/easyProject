package ecay.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisServerApplication {
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(RedisServerApplication.class,args);
        System.out.println("==========================启动成功================================");
    }
}
