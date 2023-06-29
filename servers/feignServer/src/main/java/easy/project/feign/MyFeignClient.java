package easy.project.feign;

import com.alibaba.fastjson.JSONObject;
import easy.project.config.MyFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李志威
 * @Description
 * @date 2023/6/25
 */
@FeignClient(url = "http://localhost:28710", path = "/", name = "MyFeignClient")
public interface MyFeignClient {

    @PostMapping("test/getUserIp")
    String getUserIp();

    @PostMapping("test/getJsonUserInfo")
    JSONObject getJsonUserIp(@RequestParam("param") String param,
                             @RequestHeader("header") String header,
                             @RequestParam("bodyParam") String bodyParam,
                             @RequestBody JSONObject requestBody);

}
