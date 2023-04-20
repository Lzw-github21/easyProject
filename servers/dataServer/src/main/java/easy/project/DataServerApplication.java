package easy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步线程
@EnableScheduling//允许支持定时器了
@EnableConfigurationProperties//允许通过@ConfigurationProperties(prefix = "ymlString")注解读取yml配置文件信息
public class DataServerApplication {
    public static void main(String[] args) {
        System.out.println("==========================开始启动================================");
        SpringApplication.run(DataServerApplication.class,args);
        System.out.println("==========================启动成功================================");
    }
}
