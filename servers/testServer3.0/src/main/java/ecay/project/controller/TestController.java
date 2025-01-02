package ecay.project.controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

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
        return "helloWorld11!";
    }

    /**
     * 获取session
     * @param
     * @return
     */
    //TODO HttpServletRequest Springboot3.0.2版本不支持，需要换成RequestContextHolder.getRequestAttributes()获取request对象
    @GetMapping("/getSession")
    public String getSession(@RequestParam(required = false,value = "name") String name) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
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
        List<String> list = new ArrayList();
        return jsonObject;
    }



}
