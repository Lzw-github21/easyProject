package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.entity.User;
import project.mapper.UserMapper;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    private String test(){
        System.out.println("1");
        User user = userMapper.selectById("1");
        System.out.println(user);
        return "1";
    }
}
