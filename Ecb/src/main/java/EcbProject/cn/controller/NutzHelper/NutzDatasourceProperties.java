package EcbProject.cn.controller.NutzHelper;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * @author Dee
 * @date 2022/10/21
 * <p>Description:
 */
@Getter
@Setter
public class NutzDatasourceProperties extends DataSourceProperties {
    private String guid;
    private String description;
    private DruidDataSource conf;

    public NutzDatasourceProperties() {
    }
}
