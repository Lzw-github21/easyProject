package easy.project.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李志威
 * @Description
 * @date 2023/7/19
 */
@RestController
public class setCookie {

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        // 创建一个Cookie对象
        Cookie cookie = new Cookie("cookie_name", "cookie_value");
        // 设置Cookie的路径
        cookie.setPath("/");
        // 设置Cookie的有效期，以秒为单位
        cookie.setMaxAge(3600); // 1 hour

        // 将Cookie添加到响应中
        response.addCookie(cookie);

        return "Cookie set successfully!";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(name = "cookie_name", required = false) String cookieValue) {
        if (cookieValue != null) {
            return "Cookie value: " + cookieValue;
        } else {
            return "Cookie not found!";
        }
    }
}
