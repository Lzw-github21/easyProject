package ecay.project.services.impl;

import ecay.project.services.PublicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 测试Async注解实现异步
 */
@Service
public class AsyncServiceImpl {
    @Autowired
    PublicServices publicServices;

    @Async("threadPoolExecutor")
    public void asyncExample() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);
            publicServices.workBanch(String.valueOf(i));
        }
    }
}
