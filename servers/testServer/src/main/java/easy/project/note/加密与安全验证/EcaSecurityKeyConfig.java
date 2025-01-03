package easy.project.note.加密与安全验证;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Data
@Configuration("customEcaSecurityKeyConfig")//自定义配置类名称，此处不使用默认值，避免与其它同名类冲突
@ConfigurationProperties(prefix = "security-key")
@Primary//标记为首选配置,在有多个配置时，优先使用此配置。此处使用该注解不生效，原因为 @ComponentScan 扫描的范围包含了两个冲突的类所在的包
public class EcaSecurityKeyConfig {
    private String jwtKey;
    private String md5Key;
    private String md5Pwdkey;
}
