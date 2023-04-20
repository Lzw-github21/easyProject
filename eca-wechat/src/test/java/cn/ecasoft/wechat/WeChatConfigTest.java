package cn.ecasoft.wechat;

import cn.ecasoft.basic.JsonData;
import cn.ecasoft.wechat.api.config.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author mhd
 * @date 2021/12/24/ 10:12:00
 * @Description TODO
 */
public class WeChatConfigTest {
    @Autowired
    WeChatProperties weChatProperties;
    @Autowired
    HashMap<String, WeChatProperties.Template> weChatTemplateMap;

    public void test() {
        System.out.println(JsonData.toString(weChatProperties));
        System.out.println(JsonData.toString(weChatTemplateMap));
        System.out.println(JsonData.toString(weChatProperties.getUrlConfig()));
    }
}
