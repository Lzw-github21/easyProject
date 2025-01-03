package easy.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.openapi.info.title}")
    private String title;

    @Value("${springdoc.openapi.info.description}")
    private String description;

    @Value("${springdoc.openapi.info.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version));
    }

//    /**
//     * 对api进行分组，便于管理
//     * @return
//     */
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("用户管理")
//                .pathsToMatch("/api/**")
//                .build();
//    }
//
    /**
     * 对api进行分组，便于管理
     * @return
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户管理")
                .pathsToMatch("/api/**")
                .build();
    }
    /**
     * 对api进行分组，便于管理
     * @return
     */
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("项目管理")
                .pathsToMatch("/project/**")
                .pathsToExclude("/project/create")
                .build();
    }

    /**
     * 对api进行分组，便于管理
     * @return
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("所有")
                .pathsToMatch("/**")
                .build();
    }


    /**
     * 根据路径分组
     * @return
     */
    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder()
                .group("其他所有接口")
                .packagesToScan("easy.project.controller") // 扫描指定包
                .pathsToMatch("/**")
                .build();
    }
}
