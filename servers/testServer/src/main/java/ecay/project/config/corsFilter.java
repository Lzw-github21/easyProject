package ecay.project.config;

import org.springframework.stereotype.Component;

/**
 * @author 李志威
 * @Description
 * @date 2023/7/18
 */
@Component
public class corsFilter {

//    @Value("${cors.AllowedOrigins:*}")
//    private List<String> AllowedOrigins;
//    /**
//     * 前后端分离项目解决网关跨域问题
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnProperty(value = {"cors.AllowCors"}, matchIfMissing = false, havingValue = "true")
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 允许cookies跨域
//        config.setAllowedOrigins(AllowedOrigins);// 允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
//        config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
//        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
//        config.addAllowedMethod("OPTIONS");// 允许提交请求的方法类型，*表示全部允许
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//
//
//        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
//                new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }

}
