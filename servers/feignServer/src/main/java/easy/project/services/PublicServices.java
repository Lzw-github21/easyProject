package easy.project.services;

import com.alibaba.fastjson.JSONObject;

public interface PublicServices {

    String getRemotInfo();

    JSONObject getJsonInfo(String params, String header,String bodyParam, JSONObject body);

    JSONObject getUserConfigJsonInfo(String params, String header, String bodyParam,JSONObject body);

    JSONObject getFileDataInfo();
}
