package easy.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

/**
 * @author 李志威
 * @Description
 * @date 2023/7/18
 */
@Configuration
//@Component
@RefreshScope
public class corsFelter {

    @Value("${cors.AllowedOrigins:*}")
    private List<String> AllowedOrigins;

    /**
     * 前后端分离项目解决网关跨域问题
     *
     * @return
     */
    //@Bean
    @ConditionalOnProperty(value = {"cors.AllowCors"}, matchIfMissing = false, havingValue = "true")
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.setAllowedOrigins(AllowedOrigins);// 允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("OPTIONS");// 允许提交请求的方法类型，*表示全部允许
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");


        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
                new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
    /**
     *  @ConditionalOnProperty注解有以下属性：
     *
     * name：指定配置文件中的属性名。例如，name = "myapp.feature.enabled"表示需要检查myapp.feature.enabled属性是否存在。
     * value：与name属性等效，两者可以互换使用。
     * havingValue：指定要求的属性值。例如，havingValue = "enabled"表示要求myapp.feature.enabled属性的值为enabled。
     * matchIfMissing：指定当属性不存在时是否匹配。默认值为false。如果将matchIfMissing设置为true，表示当属性不存在时也将视为匹配。否则，如果该属性不存在，则条件不满足。
     * prefix：指定属性名的前缀。例如，prefix = "myapp"表示要检查以myapp开头的属性是否存在。
     * valueSeparator：指定属性值中的分隔符。例如，valueSeparator = ":"表示属性值可以使用冒号进行多值分隔。
     * ignoreInvalidFields：指定是否忽略无效的字段。默认值为false。如果将ignoreInvalidFields设置为true，表示忽略无效的字段。
     * ignoreUnknownFields：指定是否忽略未知的字段。默认值为true。如果将ignoreUnknownFields设置为false，表示不忽略未知的字段，即未知字段将导致条件不满足。
     *
     * @ConditionalOnProperty注解可根据配置文件中的属性名、属性值以及一些其他条件属性的设置来决定是否满足条件加载bean或配置类。通过合理使用这些属性，可以根据配置来灵活控制应用程序的加载行为。
     */
}
