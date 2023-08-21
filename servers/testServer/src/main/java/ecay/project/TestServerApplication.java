package ecay.project;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步线程
public class TestServerApplication{
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(TestServerApplication.class,args);
        System.out.println("==========================启动成功================================");
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }
    @Component
    public class MyCommandLineRunner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            // 在应用程序启动后，打印所有参数
            for (String arg : args) {
                System.out.println(arg);
            }
        }
    }
}
