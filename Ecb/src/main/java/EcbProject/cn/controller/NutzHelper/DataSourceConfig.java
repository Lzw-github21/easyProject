package EcbProject.cn.controller.NutzHelper;

import EcbProject.cn.controller.NutzHelper.Enum.AppHttpCodeEnum;
import EcbProject.cn.controller.NutzHelper.Enum.Constants;
import EcbProject.cn.controller.NutzHelper.Exception.NutzHelperException;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Dee
 * @date 2022/10/25
 * <p>Description:
 */
@Configuration
public class DataSourceConfig implements EnvironmentAware {

    private Environment environment;
    @Resource
    private NutzConfiguration deeConfig;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    //配置主数据源
    @Primary
    @Bean
    public DataSource initDataSource(){
        NutzDatasourceProperties dataSource = deeConfig.getDs().stream().filter(Objects::nonNull)
                .filter(ds -> Constants.DM_MASTER_GUID.equals(ds.getGuid())).findFirst()
                .orElseThrow(() -> new NutzHelperException(AppHttpCodeEnum.DB_BAD_DATASOURCE));
        try {
            this.afterPropertiesSet(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(String.format("数据源密码解密错误: %s", e.getMessage()));
        }
        DataSource dataSourceConf = dataSource.getConf();
        return dataSourceConf;
    }

    //配置从数据源
    @Bean(name = "dmsds")
    public DataSource initSDataSource() {
        NutzDatasourceProperties dataSource = deeConfig.getDs().stream().filter(Objects::nonNull)
                .filter(ds -> Constants.DM_SLAVE_GUID.equals(ds.getGuid())).findFirst()
                .orElseThrow(() -> new NutzHelperException(AppHttpCodeEnum.DB_BAD_DATASOURCE));
        try {
            this.afterPropertiesSet(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(String.format("数据源密码解密错误: %s", e.getMessage()));
        }
        DataSource dataSourceConf = dataSource.getConf();
        return dataSourceConf;
    }


    //主数据源绑定Nutz
    @Primary
    @Bean
    public Dao initNutzDao(DataSource dataSource){
        return new NutDao(dataSource);
    }

    //从数据源绑定Nutz
    @Bean(name = "dmsdao")
    public Dao initSNutzDao(@Qualifier("dmsds") DataSource dataSource){
        return new NutDao(dataSource);
    }
    /**
     * 配置数据源参数
     * @param nutzDatasourceProperties
     * @throws Exception
     */
    public void afterPropertiesSet(NutzDatasourceProperties nutzDatasourceProperties) throws Exception {
        Properties properties = new Properties();
        //获取额外配置
        DruidDataSource dataSourceConf = nutzDatasourceProperties.getConf();
        if (nutzDatasourceProperties.getConf() == null) {
            dataSourceConf = new DruidDataSource();
            nutzDatasourceProperties.setConf(dataSourceConf);
            properties = deeConfig.getDefaultDruidConfig();
        }
        //基础连接配置
        dataSourceConf.setUsername(nutzDatasourceProperties.getUsername());
        dataSourceConf.setPassword(nutzDatasourceProperties.getPassword());
        dataSourceConf.setUrl(nutzDatasourceProperties.getUrl());
        dataSourceConf.setDriverClassName(nutzDatasourceProperties.getDriverClassName());
        dataSourceConf.setTestWhileIdle(true);
        dataSourceConf.setValidationQuery("SELECT 1");
        DruidDataSourceFactory.config(dataSourceConf, properties);
    }
}
