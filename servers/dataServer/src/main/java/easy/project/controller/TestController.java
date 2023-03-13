package easy.project.controller;

import easy.project.services.PublicServices;
import easy.project.services.impl.AsyncServiceImpl;
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

    /**
     * 测试接口
     * @param dateTime
     * @return
     */
    @GetMapping("/helloWorld")
    public String helloWorld(@RequestParam(value = "dateTime", required = false) String dateTime) {
        System.out.println(dateTime);
        return "helloWorld!";
    }

    @GetMapping("/async")
    public String asyncTest() throws InterruptedException {
        //不同类中异步方法生效
        //asyncService.asyncExample();
        //同类中异步方法失效(经验证同样生效)
        publicServices.work();
        return "helloWorld!";
    }
}
