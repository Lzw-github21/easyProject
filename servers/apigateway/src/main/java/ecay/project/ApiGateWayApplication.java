package ecay.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步线程
public class ApiGateWayApplication{
    public static void main(String[] args) {
        System.out.println("==========================开始启动================================");
        SpringApplication.run(ApiGateWayApplication.class,args);
        System.out.println("==========================启动成功================================");
    }
}
