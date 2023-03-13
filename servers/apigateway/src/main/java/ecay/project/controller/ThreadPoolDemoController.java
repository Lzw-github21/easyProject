package ecay.project.controller;


import com.alibaba.nacos.client.naming.utils.ThreadLocalRandom;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李志威
 * @date 2023/2/20 11:48
 */
@RestController
@RequestMapping(value = "/threadPool")
public class ThreadPoolDemoController {
    //最大线程数
    private static final int pool_Length = 20;
    //初始化线程池
    private static final ThreadPoolExecutor threadPoolExecutor = newThreadPool1();
    //当前任务
    private static Integer taskCurrent = 0;
    //锁
    private static Lock lock = new ReentrantLock();
    //任务数量
    private static final Integer taskSum = 100;
    //线程安全变量，可多线程共享
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 初始化线程方法整理
     * @return
     */
    @GetMapping(value = "/threadLock")
    public String threadTest() {
        System.out.println("执行了");
        //通过循环创建 10个线程，一起去调用 自增方法
        for (int i = 0; i < 10; i++) {
            //创建方式1
            new Thread(() -> {
                autoCountNum();
            }).start();
            //创建方式2
            new Thread("T-" + i) {
                @Override
                public void run() {
                }
            }.start();
        }
        return "执行成功";
    }

    /**
     * 初始化线程池整理
     *
     * @return
     */
    public static ThreadPoolExecutor newThreadPool1() {
        return new ThreadPoolExecutor(
                0,  //核心数，一直都能工作的数量
                pool_Length,  //请求处理大时，可以开放的最大工作数
                10,  //开启最大工作数后，当无请求时，还让其存活的时间
                TimeUnit.SECONDS,   //存活时间单位
                new LinkedBlockingDeque<>(10),  //阻塞队列，保存操作请求线程
                Executors.defaultThreadFactory(),   //创建线程的工厂类
                new ThreadPoolExecutor.CallerRunsPolicy());
        /*
         * AbortPolicy：直接抛出异常，这是默认策略；
         * CallerRunsPolicy：用调用者所在的线程来执行任务；
         * DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
         * DiscardPolicy：直接丢弃任务；
         */
    }

    /**
     * 线程池基本用法整理
     * @return
     */
    @GetMapping(value = "/pool")
    public String threadPool(){
        //结束点
        while (atomicInteger.get()<taskSum) {
            threadPoolExecutor.execute(() -> {
                try {
                    int couter = atomicInteger.addAndGet(1);
                    System.out.println(java.lang.Thread.currentThread().getName() + "开始执行!这是第" + couter + "个任务");
                    //TODO 业务逻辑执行处,这里未来模拟实际业务处理时间，sleep一个100ms - 5000ms 的时间
                    int sleepTime = ThreadLocalRandom.current().nextInt(100, 5000);
                    java.lang.Thread.sleep(sleepTime);
                    System.out.println(java.lang.Thread.currentThread().getName() + "执行结束!这是第" + couter + "个任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //线程池不会立刻关闭,而是等执行完正在执行的任务和队列中等待的任务后才彻底关闭。
        threadPoolExecutor.shutdown();
        return "执行成功";
    }

    /**
     * 使用lock 的自增方法
     */
    public static void autoCountNum() {
        //获得锁（加锁）
        lock.lock();
        try {
            //执行业务方法 todo
            taskCurrent++;
            System.out.println("线程名："+Thread.currentThread().getName()+";当前数为："+ taskCurrent);
        }finally {
            //finally 块内总是 释放掉锁
            lock.unlock();
        }
    }
}
