package ecay.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})//排除数据源自动配置，不配置会报错。添加了依赖spring-boot-starter-jdbc，spring会自动配置数据源，导致启动失败。
@EnableAsync(proxyTargetClass=true) //开启异步线程
@ComponentScan(basePackages = {"cn.ecasoft", "ecay.project"})//不添加该配置，无法注入DBHelp的bean
public class TestServerApplication{
    public static void main(String[] args) {

        System.out.println("==========================开始启动================================");
        SpringApplication.run(TestServerApplication.class,args);
        System.out.println("==========================启动成功================================");
    }

//    //获取所有程序运行所需的javaBen
//    @Bean(name = "commandLineRunner")
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
//    @Bean
//    public MyCommandLineRunner myCommandLineRunner() {
//        return new MyCommandLineRunner();
//    }
//    //程序启动时执行代码
//    @Component
//    public class MyCommandLineRunner implements CommandLineRunner {
//
//        @Override
//        public void run(String... args) throws Exception {
//            // 在应用程序启动后，打印所有参数
//            for (String arg : args) {
//                System.out.println(arg);
//            }
//        }
//    }
}
