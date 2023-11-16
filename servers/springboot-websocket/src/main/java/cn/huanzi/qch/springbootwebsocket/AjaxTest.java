package cn.huanzi.qch.springbootwebsocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李志威
 * @Description
 * @date 2023/4/19
 */
@RestController
@RequestMapping("/test")
public class AjaxTest {
    /**
     * 登录
     */
    @RequestMapping("/ajaxTest")
    public ModelAndView ajaxTest(HttpServletRequest request) {
        return new ModelAndView("跨域测试页面.html");
    }
}
