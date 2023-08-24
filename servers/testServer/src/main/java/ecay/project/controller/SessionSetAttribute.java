package ecay.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 李志威
 * @Description
 * @date 2023/8/21
 */
@RestController
public class SessionSetAttribute {
    @GetMapping("/sessionSetAttribute")
    public String sessionSetAttribute(HttpServletRequest request){
        // 获取当前用户的会话对象
        HttpSession session = request.getSession();
        // 存储属性到会话对象中
        session.setAttribute("username", "John");
        session.setAttribute("age", 25);
        String result = "{\"result\":\"success\"}";
        return result;
    }

    @GetMapping("/getSessionSetAttribute")
    public String getSessionSetAttribute(HttpServletRequest request){
        // 获取当前用户的会话对象
        HttpSession session = request.getSession();
        // 获取指定的session变量
        String sessionGet = session.getAttribute("username").toString();
        return sessionGet;
    }
}
