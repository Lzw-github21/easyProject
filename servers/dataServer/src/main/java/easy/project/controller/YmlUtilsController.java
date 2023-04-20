package easy.project.controller;

import easy.project.configproperties.EcaConfiguration;
import easy.project.util.YmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lzw
 * @Date 2023/3/16 16:13
 * @Description
 */
@RestController
public class YmlUtilsController {

    @Autowired
    EcaConfiguration ecaConfiguration;

    @GetMapping("/")
    public String test(){
        String ymlStr = (String)YmlUtils.getCommonYml("spring.application.name");
        System.out.println(ymlStr);
        System.out.println(ecaConfiguration);
        System.out.println(ecaConfiguration.getString1());
        System.out.println(ecaConfiguration.getString2());
        System.out.println(ecaConfiguration.getString3());
        System.out.println(ecaConfiguration.getItems());
        System.out.println(ecaConfiguration.getInnerMap());
        return ymlStr;
    }
}
