package cn.ecasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * 认证微服务启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ServletComponentScan 自动扫描并注册Servlet、Filter和Listener
public class App_WebServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("App_WebServer!");
        SpringApplication.run(App_WebServer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App_WebServer.class);
    }
}
