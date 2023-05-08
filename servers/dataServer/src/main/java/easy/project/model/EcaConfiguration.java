package easy.project.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ConfigurationProperties注解是Spring Boot框架中的一个注解，用于将配置文件中的属性值注入到Java对象中。它可以将配置文件中的属性值映射到Java对象的字段中，从而方便地读取和操作配置文件中的属性。
//使用@ConfigurationProperties注解，需要先在Java类上添加注解@EnableConfigurationProperties，表示开启配置属性注入功能。然后在Java类中使用@ConfigurationProperties注解，指定配置文件的前缀
//除此之外，可以通过@Value注解获取配置文件数据。
//@Value("${userinfo.name}")
//private String UserName;
/**
 * 通过@ConfigurationProperties注解读取yml不同类型配置文件
 * @author 李志威
 * @date 2023/4/20
 */
@Configuration
@ConfigurationProperties(prefix = "yml-string")
@Data
public class EcaConfiguration {
    String string1;
    String string2;
    String string3;
    List<String> items;
    Map<String,String> innerMap = new HashMap<>();
}


