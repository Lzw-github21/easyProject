package easy.project.Mapper;

import cn.ecasoft.basic.datatable.DataTable;
import cn.ecasoft.configproperties.EcaSqlsConfig;
import cn.ecasoft.utils.DBhelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author XuLiuKai
 * @Desc
 */
@Service
public class CommonMapperByDBHelp {

    @Autowired
    private DBhelper dBhelper;
    @Autowired
    private EcaSqlsConfig ecaSqlsConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DataTable executeSqlForDataTable(String dbSource, String sql, HashMap<String, Object> paramsMap) {
        DataTable dataTable;
        String sqlKey = UUID.randomUUID().toString();
        ecaSqlsConfig.getMap().put(sqlKey, sql);
        try {
            dataTable = dBhelper.QueryDataTable(dbSource, sqlKey, paramsMap);
        } catch (Exception e) {
            logger.error("执行sql报错--->报错信息为：" + e.getMessage() + "\n----->错误数组：" + Arrays.toString(e.getStackTrace()) + "<-----\n");
            throw new RuntimeException("executeSqlForDataTable fail : " + e.getMessage());
        } finally {
            ecaSqlsConfig.getMap().remove(sqlKey);
        }
        return dataTable;
    }

    public int executeSqlForCount(String dbSource, String sql, HashMap<String, Object> paramsMap) {
        int count;
        String sqlKey = UUID.randomUUID().toString();
        ecaSqlsConfig.getMap().put(sqlKey, sql);
//        logger.info("待执行sql------>：" + sql);
        try {
            count = dBhelper.QueryInt(dbSource, sqlKey, paramsMap);
        } catch (Exception e) {
            logger.error("执行sql报错--->报错信息为：" + e.getMessage() + "\n----->错误数组：" + Arrays.toString(e.getStackTrace()) + "<-----\n");
            throw new RuntimeException("executeSqlForCount fail : " + e.getMessage());
        } finally {
            ecaSqlsConfig.getMap().remove(sqlKey);
        }
        return count;
    }

    public EntitySql entityToInsertSql(Object object, String tableName) {
        EntitySql entitySql = new EntitySql();

        // 获取实体类的所有字段
        Field[] fields = object.getClass().getDeclaredFields();
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sqlStr = new StringBuilder();
        StringBuilder sqlValue = new StringBuilder();

        sqlStr.append("insert into ").append(tableName).append(" (");
        sqlValue.append("values( ");
        // 遍历字段并输出字段名和值 拼接字段和值占位符
        //例如 insert tableA(a,b,c) valuse(@a,@b,@c)
        for (Field field : fields) {
            try {
                //设置字段的访问权限，使其可以访问私有字段
                field.setAccessible(true);
                // 获取字段的值
                Object value = field.get(object);
                // 输出字段名和值
                if (value != null && StringUtils.isNotBlank(value.toString())) {
                    sqlStr.append(field.getName()).append(",");
                    sqlValue.append("@").append(field.getName()).append(",");
                    params.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //去掉最后一个逗号并加上）
        sqlStr.deleteCharAt(sqlStr.length() - 1).append(") ");
        sqlValue.deleteCharAt(sqlValue.length() - 1).append(") ");
        //sql拼接
        sqlStr.append(sqlValue);

        entitySql.setSql(sqlStr.toString());
        entitySql.setParams(params);
        return entitySql;
    }

    /*
    pageindex=startline/pagesize+1;
    pageindex从1开始
     */
    public DataTable executePageSqlForDataTable(String dbSource, String sql, HashMap<String, Object> paramsMap, int startline, int pageSize) {
        DataTable dataTable;
        String sqlKey = UUID.randomUUID().toString();
        ecaSqlsConfig.getMap().put(sqlKey, sql);
        logger.info("待执行sql------>：" + sql);
        try {
            dataTable = dBhelper.QueryDataTable(dbSource, sqlKey, paramsMap, startline, pageSize);
        } catch (Exception e) {
            logger.error("执行sql报错--->报错信息为：" + e.getMessage() + "\n----->错误数组：" + Arrays.toString(e.getStackTrace()) + "<-----\n");
            throw new RuntimeException("executeSqlForDataTable fail : " + e.getMessage());
        } finally {
            ecaSqlsConfig.getMap().remove(sqlKey);
        }
        return dataTable;
    }


}
