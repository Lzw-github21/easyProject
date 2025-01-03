package easy.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})//排除数据源自动配置，不配置会报错。添加了依赖spring-boot-starter-jdbc，spring会自动配置数据源，导致启动失败。
@EnableAsync(proxyTargetClass=true) //开启异步线程
public class TestServer3Application{
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(TestServer3Application.class,args);
        System.out.println("==========================启动成功================================");
    }
}
