package ecay.project.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志威
 * @Description
 * @date 2023/8/21
 */
@RequestMapping(value = "/test")
@RestController
@Slf4j
public class SneakyThrowsController {
    @GetMapping("/sneakyThrows")
    private String sneakyThrowsTest() throws Exception{
        String string = null;
        System.out.println(string.substring(1));
        return test();
    }

    @SneakyThrows
    public String test(){
        if(true){
            throw new Exception("Something went wrong");
        }
        return "";
    }
}
