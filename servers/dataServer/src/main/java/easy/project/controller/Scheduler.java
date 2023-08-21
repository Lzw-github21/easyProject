package easy.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李志威
 * @Description 整合注解实现定时器功能
 * @date 2023/4/10
 */
@Component
@Slf4j
public class Scheduler {
    //@Scheduled(cron="0/30 * * * * ?")
    private void test(){
        System.err.println("这句话每30秒打印一次  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    //可通过@Async("asyncTaskExecutor")优雅异步调用方式，防止定时任务阻塞
    //@Scheduled(cron = "0/1 * * * * ?")
    public void taskA() {
        log.info("taskA方法（这句话每1秒打印一次）"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    @Bean
    void index(){
        System.out.println("xxx");
        System.out.println("yyy");
        System.out.println("zzz");
    }
}
