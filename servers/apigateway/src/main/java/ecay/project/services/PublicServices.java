package ecay.project.services;

import org.springframework.scheduling.annotation.Async;

public interface PublicServices {

    /**
     * 待执行任务
     */
    void work();

    /**
     *
     * @throws InterruptedException
     */
    void workBanch(String counter) throws InterruptedException;
}
