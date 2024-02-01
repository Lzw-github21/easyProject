package project.entity.configproperties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求响应加密配置
 *
 * @author caizhigang
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "response.encrypt")
public class ResponseEncryptProperties implements InitializingBean {
    /**
     * 是否开启响应内容加密
     */
    private boolean enabled = true;
    /**
     * 匹配的uri
     */
    private List<String> uris = new ArrayList<>();

    /**
     * 排除的uri
     */
    private List<String> excludeUris = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
