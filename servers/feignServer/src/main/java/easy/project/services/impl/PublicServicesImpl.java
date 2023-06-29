package easy.project.services.impl;

import com.alibaba.fastjson.JSONObject;
import easy.project.feign.MyFeignClient;
import easy.project.feign.MyFeignClientUseConfig;
import easy.project.services.PublicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李志威
 * @Description
 * @date 2023/6/25
 */
@Service
public class PublicServicesImpl implements PublicServices {

    @Autowired
    MyFeignClient myFeignClient;
    @Autowired
    MyFeignClientUseConfig MyFeignClientUseConfig;
    @Override
    public String getRemotInfo() {
        return myFeignClient.getUserIp();
    }

    @Override
    public JSONObject getJsonInfo(String params, String header, String bodyParam,JSONObject body) {
        return myFeignClient.getJsonUserIp(params,header,bodyParam,body);
    }
    @Override
    public JSONObject getUserConfigJsonInfo(String params, String header, JSONObject body) {
        return MyFeignClientUseConfig.getJsonUserIp(params,header,body);
    }
    @Override
    public JSONObject getFileDataInfo() {
        return MyFeignClientUseConfig.getFileDataInfo();
    }
}
