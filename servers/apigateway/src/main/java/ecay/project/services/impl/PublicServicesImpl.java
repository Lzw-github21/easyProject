package ecay.project.services.impl;

import ecay.project.services.PublicServices;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PublicServicesImpl implements PublicServices {

    public void work() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("12");
        }
        arrayList.forEach(list -> {
            try {
                String i = "1";
                this.workBanch(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("执行完了！");
    }

    @Async("threadPoolExecutor")
    @Override
    public void workBanch(String couter) throws InterruptedException {
        Thread.sleep(50000);
        System.out.println("Current Thread Name: " + Thread.currentThread().getName());
        return;
    }
}
