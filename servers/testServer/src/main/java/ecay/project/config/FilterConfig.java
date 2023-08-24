package ecay.project.config;

import ecay.project.filter.AuthorticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description： XSSFilter过滤器配置
 * @Date：Created in 10:41 2019/10/25
 * @Modified By：
 */

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authorticationFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthorticationFilter());
        filterRegistrationBean.setOrder(Integer.MAX_VALUE - 2);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/css/*,/img/*,/js/*,/layer-v3.1.1/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


}
