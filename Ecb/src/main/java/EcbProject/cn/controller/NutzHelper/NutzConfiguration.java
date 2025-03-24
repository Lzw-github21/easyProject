package EcbProject.cn.controller.NutzHelper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidFilterConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidWebStatFilterConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Dee
 * @date 2022/10/27
 * <p>Description:
 */
@Configuration
@EnableConfigurationProperties({DruidStatProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
@ConfigurationProperties(prefix = "dee")
public class NutzConfiguration {
    private List<NutzDatasourceProperties> ds = new ArrayList<>();
    private Properties defaultDruidConfig = new Properties();


    public NutzConfiguration() {
    }

    public List<NutzDatasourceProperties> getDs() {
        return ds;
    }

    public void setDs(List<NutzDatasourceProperties> ds) {
        this.ds = ds;
    }


    public Properties getDefaultDruidConfig() {
        return defaultDruidConfig;
    }

    public void setDefaultDruidConfig(Properties defaultDruidConfig) {

        this.defaultDruidConfig = defaultDruidConfig;
    }



}
