package easy.project.springbootFilter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/index")
    public String index(@RequestParam(value = "token",required = false) String token){
        return "index首页";
    }

    @GetMapping("/test")
    public String test(){
        return "test路径测试";
    }

    @GetMapping("/api/encrypt/test")
    public String test2(HttpServletRequest request){
        //String idCard = request.getParameter("idCard");
        String IDCODE = request.getAttribute("IDCODE").toString();
        return IDCODE;
    }
}
