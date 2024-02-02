package easy.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志威
 * @Description
 * @date 2023/7/17
 */
@RestController
@RequestMapping("/exception")
public class ExceptionDeal {

    @GetMapping("/test")
    public String testClass(@RequestParam("param") String param){
        String result = param;
        return result;
    }
}
