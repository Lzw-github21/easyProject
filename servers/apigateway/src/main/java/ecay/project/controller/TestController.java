package ecay.project.controller;

import ecay.project.services.PublicServices;
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

    @GetMapping("/helloWorld")
    public String helloWorld(@RequestParam(value = "dateTime", required = false) String dateTime) {
        return "helloWorld!";
    }

    @GetMapping("/SyncPoolExecutor")
    public String helloWorld() {
        publicServices.work();
        return "helloWorld!";
    }
}
