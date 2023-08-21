package ecay.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

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

    /**
     * 获取session
     * @param request
     * @return
     */
    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request,@RequestParam(required = false,value = "name") String name) {
        HttpSession session = request.getSession();
        System.out.println(name);
        System.out.println(session.getId());
        System.out.println(session.getServletContext());
        return "helloWorld!";
    }


    /**
     * 获取请求ip
     * @return
     */
    @PostMapping("/getUserIp")
    public String getUserIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println(ipAddress);
        return ipAddress;
    }
    /**
     * 获取Json请求ip
     * @return
     */
    @PostMapping("/getJsonUserInfo")
    public JSONObject getJsonUserInfo(@RequestParam("param") String param,
                                      @RequestHeader("header") String header,
                                      @RequestParam("bodyParam") String bodyParam,
                                      @RequestBody JSONObject body) throws UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        //对header中的中文解码
        String header2 = URLDecoder.decode(header, "UTF-8");
        String header3 = URLDecoder.decode(header2, "UTF-8");
        //对params中的中文解码
        String params2 = URLDecoder.decode(param, "UTF-8");
        String params3 = URLDecoder.decode(param, "UTF-8");
        //输出header
        System.out.println(header);
        System.out.println(header2);
        System.out.println(header3);
        //输出params
        System.out.println(param);
        System.out.println(params2);
        System.out.println(params3);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip",ipAddress);
        jsonObject.put("header",header3);
        jsonObject.put("bodyParam",bodyParam);
        jsonObject.put("body",body);
        jsonObject.put("param",param);
        return jsonObject;
    }

}
