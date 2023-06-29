package easy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignServerApplication {
    public static void main(String[] args) {
        System.out.println("==========================开始启动================================");
        SpringApplication.run(FeignServerApplication.class,args);
        System.out.println("==========================启动成功================================");
    }
}
