package ecay.project.services.impl;

import ecay.project.services.PublicServices;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PublicServicesImpl implements PublicServices {

    @Override
    public void work() {
        ArrayList<String> arrayList = new ArrayList<>(1000000);
        for (int i = 0; i < 1000; i++) {
            arrayList.add("420281198010103227");
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,  //核心数，一直都能工作的数量
                10,  //maximumPoolSize, 请求处理大时，可以开放的最大工作数 如果队列中任务已满，并且当前线程个数(poolSize)小于maximumPoolSize，那么会创建新的线程来执行任务。这个必须比核心数大。
                6,  //开启最大工作数后，当无请求时，还让其存活的时间
                TimeUnit.SECONDS,   //存活时间单位
                new LinkedBlockingDeque<>(1000),  //阻塞队列，保存操作请求线程
                Executors.defaultThreadFactory(),   //创建线程的工厂类
                new ThreadPoolExecutor.CallerRunsPolicy());  //拒绝策略
        /*
         * AbortPolicy：直接抛出异常，这是默认策略；
         * CallerRunsPolicy：用调用者所在的线程来执行任务；
         * DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
         * DiscardPolicy：直接丢弃任务；
         */

        arrayList.forEach(list -> {
            threadPoolExecutor.execute(() -> {
                atomicInteger.getAndIncrement();
                int poolNum = atomicInteger.get();
                try {
                    this.workBanch(poolNum,atomicInteger);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        });
        //线程池不会立刻关闭,而是等执行完正在执行的任务和队列中等待的任务后才彻底关闭。
        threadPoolExecutor.shutdown();
        System.out.println("hell");
    }

    public void work2(int poolNum,AtomicInteger atomicInteger) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 800; i++) {
            arrayList.add("12");
        }
        arrayList.forEach(list -> {
            try {
                this.workBanch(poolNum,atomicInteger);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Async("threadPoolExecutor")
    public void workBanch(int poolNum,AtomicInteger atomicInteger) throws InterruptedException {
        //System.out.println("线程开始执行:" + count);
        Thread.sleep(5000);
        System.out.println("poolNum:"+poolNum +"atomicInteger:"+ atomicInteger+"Current Thread Name: " + Thread.currentThread().getName());
        //System.out.println("线程执行完毕:" + count);
        return;
    }
}
