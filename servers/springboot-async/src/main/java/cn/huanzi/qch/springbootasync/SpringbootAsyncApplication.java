package cn.huanzi.qch.springbootasync;

import cn.huanzi.qch.springbootasync.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@EnableAsync//开启异步调用
@SpringBootApplication
public class SpringbootAsyncApplication {

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncApplication.class, args);
    }

    /**
     * 启动成功
     * springBoot提供了五种项目启动后自动自行的代码
     * 具体可以参考文章 https://blog.csdn.net/qq_40454136/article/details/127049550【spring boot应用在项目启动后执行代码】
     * 此处以
     */
    @Bean
    @Order(8)//可以通过older注解指定自动执行顺序
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "：开始调用异步业务");
            //无返回值
            //testService.asyncTask();

            //有返回值，但主线程不需要用到返回值
            //Future<String> future = testService.asyncTask("huanzi-qch");
            //有返回值，且主线程需要用到返回值
//           System.out.println(Thread.currentThread().getName() + "：返回值：" + testService.asyncTask("huanzi-qch").get());

            //事务测试，事务正常提交
            testService.asyncTaskForTransaction(false);
            //事务测试，模拟异常事务回滚
            //testService.asyncTaskForTransaction(true);

            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "：调用异步业务结束，耗时：" + (endTime - startTime));
        };
    }

    @Bean
    @Order(3)//可以通过older注解指定自动执行顺序
    public ApplicationRunner applicationRunner2() {
        return applicationArguments -> {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "：开始调用异步业务2");
            //无返回值
            //testService.asyncTask();

            //有返回值，但主线程不需要用到返回值
            //Future<String> future = testService.asyncTask("huanzi-qch");
            //有返回值，且主线程需要用到返回值
//           System.out.println(Thread.currentThread().getName() + "：返回值：" + testService.asyncTask("huanzi-qch").get());

            //事务测试，事务正常提交
            //testService.asyncTaskForTransaction(false);
            //事务测试，模拟异常事务回滚
            testService.asyncTaskForTransaction(true);

            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "：调用异步业务2结束，耗时：" + (endTime - startTime));
        };
    }
}
