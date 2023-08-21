package easy.project.controller;

import com.alibaba.fastjson.JSONObject;
import easy.project.services.PublicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李志威
 * @Description
 * @date 2023/6/25
 */
@RestController
public class FeignConfigController {
    @Autowired
    PublicServices publicServices;

    /**
     * feign传String
     *
     * @return
     */
    @GetMapping("/")
    public String getIpInfo() {
        return publicServices.getRemotInfo();
    }

    /**
     * feign传Json 及传参验证
     *
     * @return
     */
    @PostMapping("/getJsonInfo")
    public JSONObject PostJsonInfo(@RequestParam("param") String param,
                                  @RequestHeader("header") String header,
                                  @RequestParam("bodyParam") String bodyParam,
                                  @RequestBody JSONObject body) {
        return publicServices.getJsonInfo(param, header,bodyParam, body);
    }

    /**
     * feign传Json 及传参验证
     *
     * @return
     */
    @GetMapping("/getJsonInfo")
    public JSONObject getJsonInfo(@RequestParam("param") String param,
                                  @RequestHeader("header") String header,
                                  @RequestParam("bodyParam") String bodyParam,
                                  @RequestBody JSONObject body) {
        return publicServices.getJsonInfo(param, header,bodyParam, body);
    }

    /**
     * feign传Json 及传参验证 自定义config
     *
     * @return
     */
    @PostMapping("/getJsonInfoUseConfig")
    public JSONObject getJsonInfoUseConfig(@RequestParam("param") String param,
                                           @RequestHeader("header") String header,
                                           @RequestParam("bodyParam") String bodyParam,
                                           @RequestBody JSONObject body) {
        return publicServices.getUserConfigJsonInfo(param, header,bodyParam, body);
    }

    /**
     * feign传文件流
     *
     * @return
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadFiles(@RequestPart("files") MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File dest = new File("/path/to/save/files/" + fileName);
            file.transferTo(dest);
            fileNames.add(fileName);
        }

        return ResponseEntity.ok(fileNames);
    }
}
