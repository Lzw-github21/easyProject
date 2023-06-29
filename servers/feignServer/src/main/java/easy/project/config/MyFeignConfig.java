package easy.project.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置类
 *
 * @author yangsen
 * @date 2021/4/26 10:04
 */
@Slf4j
public class MyFeignConfig<T> {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    public Logger logger() {
        return new Logger() {
            @Override
            protected void log(String configKey, String format, Object... args) {
                log.info(String.format(methodTag(configKey) + format, args));
            }
        };
    }
//    //绕过https证书验证
//    @Bean
//    public Client feignClient() throws Exception {
//        SSLContextBuilder builder = new SSLContextBuilder();
//        builder.loadTrustMaterial(null, (certificate, authType) -> true);
//        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
//        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
//        return new ApacheHttpClient(httpClient);
//    }
//
//    /**
//     * 注入新的Decoder Feign将自动 替换
//     */
////    @Bean
////    public Decoder feignDecoder() {
////        MessageConverter messageConverter = new MessageConverter();
////        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(messageConverter);
////        return new SpringDecoder(objectFactory);
////    }
//
//
//
//    /**
//     * 构建动态URL
//     * @param t 接口泛型
//     * @param url 请求的url地址
//     */
//    public T target(Class<T> t, String url) {
//        MyFeignConfig<T> tMyFeignConfig = new MyFeignConfig<>();
//        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
//        ObjectFactory<HttpMessageConverters> converter = ()-> new HttpMessageConverters(jsonConverter);
//        return Feign.builder()
//                //.decoder(tMyFeignConfig.feignDecoder())
//                .encoder(new SpringEncoder(converter))
//                .logLevel(tMyFeignConfig.feignLoggerLevel())
//                .logger(tMyFeignConfig.logger())
//                .target(t, url);
//    }
}
