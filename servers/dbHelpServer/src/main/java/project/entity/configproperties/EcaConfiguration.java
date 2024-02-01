package project.entity.configproperties;

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

@Configuration
@EnableConfigurationProperties({DruidStatProperties.class})
@Import({DruidSpringAopConfiguration.class,
        DruidStatViewServletConfiguration.class,
        DruidWebStatFilterConfiguration.class,
        DruidFilterConfiguration.class})
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
@ConfigurationProperties(prefix = "eca")
public class EcaConfiguration {
    private List<EcaDataSourceProperties> dataSources = new ArrayList();
    private Properties defaultDruidConfig = new Properties();
    private String licence;

    public List<EcaDataSourceProperties> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<EcaDataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Properties getDefaultDruidConfig() {
        return defaultDruidConfig;
    }

    public void setDefaultDruidConfig(Properties defaultDruidConfig) {
        this.defaultDruidConfig = defaultDruidConfig;
    }
}


