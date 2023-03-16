package easy.project.springbootaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
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
}
