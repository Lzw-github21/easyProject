package cn.ecasoft.wechat.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author mhd
 * @date 2021/12/24/ 09:38:00
 * @Description TODO
 */
@Configuration
public class WeChatAutoConfiguration {

    @Autowired
    WeChatProperties weChatProperties;

    /**
     * 初始化微信模板消息内容
     * @return
     */
    @Bean
    public HashMap<String, WeChatProperties.Template> WeChatTemplateMap() {
        HashMap<String, WeChatProperties.Template> templateMap = new HashMap<>();
        weChatProperties.getTemplates().forEach(template -> {
            templateMap.put(template.getType(), template);
        });
        return templateMap;
    }
}
