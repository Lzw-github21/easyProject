package cn.ecasoft.wechat.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mhd
 * @date 2021/12/23/ 12:19:00
 * @Description TODO
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties("wechat")
public class WeChatProperties {

    private List<Config> configs = new ArrayList<>();

    private List<Template> templates = new ArrayList<>();

    private UrlConfig urlConfig = new UrlConfig();

    @Data
    public static class UrlConfig {
        /**
         * 公众号获取AccessToken接口地址
         */
        private String getAccessToken = "";

        /**
         * 公众号获取Ticket接口地址
         */
        private String getTemporaryTicket = "";

        /**
         * 公众号获取带参数二维码接口地址
         */
        private String showQrCode = "";

        /**
         * 公众号发送模板消息接口地址
         */
        private String sendTemplateMq = "";
    }

    @Data
    public static class Config {
        /**
         * 设置微信公众号的appid
         */
        private String appId = "";

        /**
         * 设置微信公众号的app secret
         */
        private String secret = "";

        /**
         * 设置微信公众号的token
         */
        private String token = "";

        /**
         * 设置微信公众号的EncodingAESKey
         */
        private String aesKey = "";
    }

    @Data
    public static class Template {
        private String id = "";
        private String type = "";
        private String first = "";
        private String remark = "";
    }
}
