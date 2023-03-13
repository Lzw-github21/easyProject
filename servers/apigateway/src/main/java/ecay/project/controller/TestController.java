package ecay.project.controller;

import ecay.project.services.PublicServices;
import ecay.project.services.impl.AsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller找不到路径
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    PublicServices publicServices;
    @Autowired
    AsyncServiceImpl asyncService;

    @GetMapping("/helloWorld")
    public String helloWorld(@RequestParam(value = "dateTime", required = false) String dateTime) {
        System.out.println("编译测试");
        return "helloWorld!";
    }

    @GetMapping("/SyncPoolExecutor")
    public String helloWorld() {
        publicServices.work();
        return "helloWorld!";
    }

    @GetMapping("/async")
    public String asyncTest() throws InterruptedException {
        //不同类中异步方法生效
        //asyncService.asyncExample();
        //同类中异步方法失效
        publicServices.work();
        return "helloWorld!";
    }
}
