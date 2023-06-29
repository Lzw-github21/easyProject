package easy.project.springbootaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@RestController
@SpringBootApplication
@Slf4j
//@CrossOrigin(origins = {"http://localhost:80","http://127.0.0.1:80","http://localhost:28700"})
public class SpringbootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopApplication.class, args);
    }

    @MyAnnotation("aop测试")
    @MyAnnotation2("aop测试2")
    @RequestMapping("/")
    public String index(@RequestParam (value = "queryParms") String parms,
                        @RequestParam (value = "type") String type){
        log.info("这是日志输出queryParms:{},type:{}",parms,type);
        return "欢迎访问 springboot-aop";
    }

    /**
     * 获取请求ip
     * @return
     */
    //经过测试通过注解配置跨域不生效，具体原因有待进一步验证
    @CrossOrigin(origins = {"http://localhost:80","http://127.0.0.1:80","http://localhost:28700"})
    @GetMapping("/getUserIp")
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
     * 登录
     */
    @RequestMapping("/htmlTest")
    public ModelAndView ajaxTest(HttpServletRequest request) {
        return new ModelAndView("test.html");
    }
}
