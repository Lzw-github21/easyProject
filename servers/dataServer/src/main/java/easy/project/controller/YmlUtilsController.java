package easy.project.controller;

import easy.project.util.YmlUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lzw
 * @Date 2023/3/16 16:13
 * @Description
 */
@RestController
public class YmlUtilsController {
    @GetMapping("/")
    public String test(){
        String ymlStr = (String)YmlUtils.getCommonYml("spring.application.name");
        System.out.println(ymlStr);
        return ymlStr;
    }
}
