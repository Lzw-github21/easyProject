package project.entity.configproperties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Import;
import project.utils.SecurityHelper;

@Import(SecurityHelper.class)
public class EcaDataSourceProperties extends DataSourceProperties {

    @Autowired
    SecurityHelper securityHelper;

    private String guid;

    private DruidDataSource druid;

    private EcaDataSourceProperties slave;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public DruidDataSource getDruid() {
        return druid;
    }

    public void setDruid(DruidDataSource druid) {
        this.druid = druid;
    }

    public EcaDataSourceProperties getSlave() {
        return slave;
    }

    public void setSlave(EcaDataSourceProperties slave) {
        this.slave = slave;
    }

}
