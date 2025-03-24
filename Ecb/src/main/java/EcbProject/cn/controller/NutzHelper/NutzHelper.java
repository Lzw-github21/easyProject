package EcbProject.cn.controller.NutzHelper;

import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import java.util.List;

/**
 * @author Dee
 * @date 2022/9/30
 * <p>Description:
 */
public interface NutzHelper {



    /**
     * 初始化dao
     * @param datasourceGuid 数据源guid
     * @return
     */
    Dao getDao(String datasourceGuid);

    /**
     * 默认分页查询(分页功能可能会失效)
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @return
     */
    QueryResult pageList(String datasourceGuid, Sql sql);

    /**
     * 分页查询(分页功能可能会失效)
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param current 当前页
     * @param size 页大小
     * @return
     */
    QueryResult pageList(String datasourceGuid, Sql sql, int current, int size);

    /**
     * 默认分页查询并自定义返回类型(分页功能可能会失效)
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param callback 回调类型
     * @param clazz 类
     * @return
     * @param <T>
     */
    <T> QueryResult pageList(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz);

    /**
     * 分页查询并自定义返回类型(分页功能可能会失效)
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param current 当前页
     * @param size 页大小
     * @param callback 回调类型
     * @param clazz 类
     * @return
     * @param <T>
     */
    <T> QueryResult pageList(String datasourceGuid, Sql sql, int current, int size, SqlCallback callback, Class<T> clazz);

    /**
     * ddl工具
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @return 执行影响行数
     */
    int queryInt(String datasourceGuid, Sql sql);

    /**
     * 单行查询
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @return
     */
    Record queryOne(String datasourceGuid, Sql sql);

    /**
     * 单行查询并自定义返回类型
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param callback 回调类型
     * @param clazz 类
     * @return
     * @param <T>
     */
    <T> T queryOne(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz);

    /**
     * 列表查询
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @return
     */
    List<Record> queryList(String datasourceGuid, Sql sql);

    /**
     * 列表查询并自定义返回类型
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param callback 回调类型
     * @param clazz 类
     * @return
     * @param <T>
     */
    <T> List<T> queryList(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz);

    /**
     * 查询数量接口
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @return
     */
    int queryCount(String datasourceGuid, Sql sql);

    /**
     * 自定义方式分页查询
     * @param datasourceGuid 数据源guid
     * @param sql 查询sql
     * @param countSql 查询数量sql
     * @param current 当前页
     * @param size 页大小
     * @return
     */
    QueryResult queryResult(String datasourceGuid, Sql sql, Sql countSql, int current, int size);

    Integer GetPkValue(String datasourceguid, String tableName, String fieldName);
}
