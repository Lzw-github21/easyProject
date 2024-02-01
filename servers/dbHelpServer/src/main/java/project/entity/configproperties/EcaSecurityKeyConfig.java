package project.entity.configproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: CaiZhiGang
 * @Title: EcaSecurityKeyConfig
 * @ProjectName: servers
 * @Description: TODO
 * @date: 2021/6/1 16:27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security-key")
public class EcaSecurityKeyConfig {
    private String jwtKey;
    private String md5Key;
    private String md5Pwdkey;
}
