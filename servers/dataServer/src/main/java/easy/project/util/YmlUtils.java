package easy.project.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * 从yml配置文件中获取参数
 */
@Slf4j
public class YmlUtils {

    private static String PROPERTY_NAME = "bootstrap.yml";

    public static Object getCommonYml(Object key){
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return properties.get(key);
    }
}
