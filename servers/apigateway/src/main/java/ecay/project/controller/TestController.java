package ecay.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller找不到路径
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping("/helloWorld")
    public String helloWorld(@RequestParam(value = "dateTime", required = false) String dateTime) {
        return "helloWorld!";
    }
}
