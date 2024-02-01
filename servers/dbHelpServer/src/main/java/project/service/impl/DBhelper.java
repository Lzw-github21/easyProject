package project.service.impl;

import org.nutz.dao.sql.SqlCallback;
import project.entity.basic.DBType;
import project.entity.basic.datatable.DataColumn;
import project.entity.basic.datatable.DataTable;
import project.entity.configproperties.EcaDataSourceProperties;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DBhelper {

    /**
     * 批量操作，默认开启事务
     * 执行insert/update/delete语句
     * @param datasourceguid 数据源
     * @param sqlKey sql唯一标识
     * @param params 批量参数
     * @return 受影响的行数
     * @throws Exception
     */
    int queryIntBatch(String datasourceguid, String sqlKey, List<HashMap<String, Object>> params) throws Exception;

    /**
     * 批量操作
     * 执行insert/update/delete语句
     * @param datasourceguid 数据源
     * @param sqlKey sql唯一标识
     * @param params 批量参数
     * @param isTrans 是否进行事务批量操作
     * @return 受影响的行数
     * @throws Exception
     */
    int queryIntBatch(String datasourceguid, String sqlKey, List<HashMap<String, Object>> params, boolean isTrans) throws Exception;

    /**
     * 获取所有数据源
     *
     * @return
     * @throws Exception
     */
    Map<String, DataSource> getDataSources() throws Exception;


    /**
     * 获取所有数据源
     *
     * @return
     * @throws Exception
     */
    Map<String, EcaDataSourceProperties> getDataSourcePropertiesMap() throws Exception;

    /**
     * 添加数据源
     *
     * @param dataSource
     */
    void addDataSource(EcaDataSourceProperties dataSource);

    /**
     * 原子事务
     *
     * @param run
     */
    void StartTrans(Runnable run);

    /**
     * 获取数据库类型
     *
     * @param datasourceguid
     * @return
     */
    DBType GetDbType(String datasourceguid) throws Exception;

    /**
     * 返回值为单行数据的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception;

    /**
     * 返回值为单行数据的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @return
     * @throws Exception
     */
    Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception;

    /**
     * 返回值为单行数据的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @param fromSlave      来自从库
     * @return
     * @throws Exception
     */
    Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars, boolean fromSlave) throws Exception;

    /**
     * 返回值为多行数据表的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception;

    /**
     * 返回值为多行数据表的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @return
     * @throws Exception
     */
    DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param startIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param startIndex
     * @param pageSize
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @return
     * @throws Exception
     */
    DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param startIndex
     * @param pageSize
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @param fromSlave      从库读取
     * @return
     * @throws Exception
     */
    DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars, boolean fromSlave) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    DataTable QueryCursor(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数
     * @return
     * @throws Exception
     */
    DataTable QueryCursor(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception;

    /**
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数
     * @return
     * @throws Exception
     */
    void QuerySqlCallback(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars, SqlCallback callback) throws Exception;


    /**
     * 返回值为数量的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    int QueryInt(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception;


    /**
     * 根据传入的对象，动态生成insert、update赋值语句
     *
     * @param datasourceguid 数据源guid
     * @param sqlKey         配置的sql键值
     * @param entity         对象
     * @return
     * @throws Exception
     */

    int QueryInt(String datasourceguid, String sqlKey, Object entity) throws Exception;

    /**
     * 返回值为数量的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @return
     * @throws Exception
     */
    int QueryInt(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception;

    /**
     * 没有返回值的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @throws Exception
     */
    void QueryVoid(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception;

    /**
     * 没有返回值的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @param vars           变量参数,$作为参数标识 例如 $param1
     * @throws Exception
     */
    void QueryVoid(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception;


    /**
     * 获取sql查询得到的列
     *
     * @param datasourceguid
     * @param strSql
     * @param params
     * @return
     */
    List<DataColumn> GetColumns(String datasourceguid, String strSql, HashMap<String, Object> params) throws Exception;

    /**
     * 获取数据源所有表名
     *
     * @param datasourceguid
     * @return
     * @throws Exception
     */
    List<String> GetTables(String datasourceguid) throws Exception;

    /**
     * 获取数据表所有列信息
     *
     * @param datasourceguid
     * @return
     * @throws Exception
     */
    DataTable GetColumns(String datasourceguid, String tableName) throws Exception;

    DataTable GetProcParams(String datasourceguid, String procName) throws Exception;

    /**
     * 是否是select语句，返回false标识是存储过程，或者其他服务形式
     *
     * @param sqlStr
     * @return
     */
    boolean IsSelectCommandText(String sqlStr);

    /**
     * 是否是存储过程语句，返回true标识是存储过程
     *
     * @param sqlStr
     * @return
     */
    boolean IsProcCommandText(String sqlStr);

    /**
     * 获取最大主键值
     *
     * @param datasourceguid
     * @param tableName
     * @param fieldName
     * @return
     */
    Integer GetPkValue(String datasourceguid, String tableName, String fieldName) throws Exception;


    /**
     * 生成主键值（雪花id)）
     *
     * @param tableName
     * @return
     */
    Long GetPkValue(String tableName);


}
