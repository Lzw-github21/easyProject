package project.entity.configproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author mhd
 * @date 2021/11/30/ 09:40:00
 * @Description TODO
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth-time-out")
public class EcaTokenConfig {
    /**
     * token有效时间，过期自动删除
     */
    private Integer codeTimeOut = 5;
    /**
     * userInfo有效时间，登录时长达到3/4时自动续时
     */
    private Integer tokenTimeOut = 60;
    /**
     * 是否允许账号再多个地方同时登录，默认false
     */
    private Boolean multiaddressUsing = false;
    /**
     * 用户时间戳:两天
     */
    private Integer appKeySignTimeOut = 2880;

}
