package project.entity.configproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "eca.sqls")
public class EcaSqlsConfig {
    //region sql
    private Map<String, Object> map = new HashMap<>();

    /**
     * 视图返回数据最大记录数
     */
    private Long maxRowNumber = 3000L;

    /**
     * 忽略视图guid集合
     */
    private List<String> ignoreDataGuids = new ArrayList<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Long getMaxRowNumber() {
        return maxRowNumber;
    }

    public void setMaxRowNumber(Long maxRowNumber) {
        this.maxRowNumber = maxRowNumber;
    }

    public List<String> getIgnoreDataGuids() {
        return ignoreDataGuids;
    }

    public void setIgnoreDataGuids(List<String> ignoreDataGuids) {
        this.ignoreDataGuids = ignoreDataGuids;
    }
}
