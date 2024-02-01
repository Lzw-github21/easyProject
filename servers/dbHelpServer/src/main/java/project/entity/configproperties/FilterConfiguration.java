package project.entity.configproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: LUOYUNHENG
 * @date: 2023/9/12
 * @description:sql过滤器注册
 */
@Configuration
public class FilterConfiguration {

    @Autowired
    private SqlInjectionFilter filter;

    @Bean
    public FilterRegistrationBean<SqlInjectionFilter> sqlFilterRegistrationBean() {
        FilterRegistrationBean<SqlInjectionFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(filter);
        filterReg.addUrlPatterns("/*");
        filterReg.setOrder(1-Integer.MAX_VALUE);
        return filterReg;
    }
}
