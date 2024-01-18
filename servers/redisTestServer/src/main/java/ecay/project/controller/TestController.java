package ecay.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    /**
     * 测试接口
     * @return
     */
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld!";
    }


}
