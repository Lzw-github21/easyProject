package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.DBhelper;
import project.service.impl.DbhelpSelectServer;

/**
 * @author 李志威
 * @Description
 * @date 2024/2/1
 */
@RestController
@RequestMapping("")
public class TestController {

    @Autowired
    DbhelpSelectServer dbhelpSelectServer;

    @GetMapping("/")
    private void test() {
        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(() -> {
                System.out.println(Thread.currentThread() + "3");
                try {
                    dbhelpSelectServer.test1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "t3");
            t1.start();
        }
    }

    @GetMapping("/test1")
    private void test1() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }

    @GetMapping("/test2")
    private void test2() {
        System.out.println("hahahahahah");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test3();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }

    @GetMapping("/test3")
    private void test3() {
        System.out.println("hahahahahah");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test4();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }
}
