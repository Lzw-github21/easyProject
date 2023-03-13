package easy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步线程
public class DataServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class,args);
    }
}
