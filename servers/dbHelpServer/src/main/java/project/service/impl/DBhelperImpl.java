package project.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.DB;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.sql.SqlType;
import org.nutz.dao.util.blob.SimpleBlob;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import project.annotation.EcaTableId;
import project.entity.DataSourceConstants;
import project.entity.basic.DBType;
import project.entity.basic.JsonData;
import project.entity.basic.datatable.DataColumn;
import project.entity.basic.datatable.DataRow;
import project.entity.basic.datatable.DataTable;
import project.entity.configproperties.EcaConfiguration;
import project.entity.configproperties.EcaDataSourceProperties;
import project.entity.configproperties.EcaSqlsConfig;
import project.utils.Convert;
import project.utils.SecurityHelper;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.sql.Types.*;

@Slf4j
@Service
@Import(value = {EcaConfiguration.class, EcaSqlsConfig.class})
public class DBhelperImpl implements DBhelper {

    private final static Map<String, DataSource> DATASOURCE_MAP = new HashMap<>();
    private final static Map<String, EcaDataSourceProperties> DATASOURCE_PROPERTIES_MAP = new HashMap<>();
    private final static Map<String, Dao> _Daos = new HashMap<String, Dao>();
    private static EcaConfiguration ecaConfig;
    private static EcaSqlsConfig ecaSqlsConfig;
    private static SecurityHelper securityHelper;

    public DBhelperImpl(EcaConfiguration ecaConfiguration, EcaSqlsConfig ecaSqlsConfig, SecurityHelper securityHelper) {
        DBhelperImpl.ecaConfig = ecaConfiguration;
        DBhelperImpl.ecaSqlsConfig = ecaSqlsConfig;
        DBhelperImpl.securityHelper = securityHelper;
        this.initDataSources(ecaConfiguration, ecaSqlsConfig);
    }

    /**
     * 获取数据源
     *
     * @param datasourceGuid
     * @return
     * @throws Exception
     */
    private DataSource getDataSource(String datasourceGuid) throws Exception {
        return this.getDataSource(datasourceGuid, false);
    }

    /**
     * 获取数据源
     *
     * @param datasourceGuid
     * @return
     */
    private DataSource getDataSource(String datasourceGuid, boolean isSlave) {
        if (datasourceGuid == null || datasourceGuid.isEmpty()) {
            datasourceGuid = this.ecaConfig.getDataSources().get(0).getGuid();
        }
        String datasourceKey = datasourceGuid;
        if (isSlave && DATASOURCE_MAP.containsKey(datasourceGuid + "_slave")) {
            datasourceKey = datasourceGuid + "_slave";
        }
        if (!DATASOURCE_MAP.containsKey(datasourceKey)) {
            log.error("数据源{}不存在，请检查！", datasourceKey);
            throw new NullPointerException("数据源不存在，请检查！");
        }
        return DATASOURCE_MAP.get(datasourceKey);
    }

    @Override
    public int queryIntBatch(String datasourceguid, String sqlKey, List<HashMap<String, Object>> params) throws Exception {
        if (!ecaSqlsConfig.getMap().containsKey(sqlKey))
            throw new Exception("未在ecasqls中配置对应的sql键！键值：" + sqlKey);
        String strSql = ecaSqlsConfig.getMap().get(sqlKey).toString();
        return this.ExecuteBatch(datasourceguid, params, strSql, Sqls.callback.map(), null, false, true);
    }

    @Override
    public int queryIntBatch(String datasourceguid, String sqlKey, List<HashMap<String, Object>> params, boolean isTrans) throws Exception {
        if (!ecaSqlsConfig.getMap().containsKey(sqlKey))
            throw new Exception("未在ecasqls中配置对应的sql键！键值：" + sqlKey);
        String strSql = ecaSqlsConfig.getMap().get(sqlKey).toString();
        return this.ExecuteBatch(datasourceguid, params, strSql, Sqls.callback.map(), null, false, isTrans);
    }

    /**
     * 获取所有数据源
     *
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, DataSource> getDataSources() throws Exception {
        return DATASOURCE_MAP;
    }

    /**
     * 获取所有数据源
     *
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, EcaDataSourceProperties> getDataSourcePropertiesMap() throws Exception {
        return DATASOURCE_PROPERTIES_MAP;
    }

    /**
     * 初始化数据源
     */
    private void initDataSources(EcaConfiguration ecaConfig, EcaSqlsConfig ecaSqlsConfig) {
        if (ecaConfig.getDataSources() == null || ecaConfig.getDataSources().size() == 0) {
            throw new Error("没有配置数据库链接！");
        }
        for (EcaDataSourceProperties dp : ecaConfig.getDataSources()) {
            this.addDataSource(dp);
        }

        List<EcaDataSourceProperties> projectDatasources = this.getProjectDatasources(ecaSqlsConfig);
        for (EcaDataSourceProperties projectDatasource : projectDatasources) {
            this.addDataSource(projectDatasource);
        }
    }

    /**
     * @return java.util.List<cn.ecasoft.configproperties.EcaDataSourceProperties>
     * @throws
     * @Description: 获取数据库表中配置的各个项目对应的数据源
     * @param:
     * @MethodName getProjectDatasources
     * @author CaiZhiGang
     * @date 2021/5/6 10:13
     */
    private List<EcaDataSourceProperties> getProjectDatasources(EcaSqlsConfig ecaSqlsConfig) {
        String sql = "SELECT id,name,url,username,password,create_date,update_date,region_code,row_guid from sys_datasource_conf where del_flag != 1";
        final String SQL_KEY = "select-datasource";
        ecaSqlsConfig.getMap().put(SQL_KEY, sql);
        List<EcaDataSourceProperties> dataSources = new ArrayList<>();
//        try {
//            dataSources = this.QueryProjectDataSources("", SQL_KEY);
//        } catch (Exception e) {
//            log.error("加载多数据源出错：{}", e.getMessage());
//        }
        return dataSources;
    }

    @Override
    public void addDataSource(EcaDataSourceProperties dataSource) {
        try {
            afterPropertiesSet(dataSource);
        } catch (Exception e) {
            log.error("数据源密码解密错误: {}", e.getMessage());
            throw new RuntimeException(String.format("数据源密码解密错误: %s", e.getMessage()));
        }
        DataSource druid = dataSource.getDruid();
        EcaDataSourceProperties exist = DATASOURCE_PROPERTIES_MAP.get(dataSource.getGuid());
        String datasourceKey = dataSource.getGuid();
        if (exist != null) {
            exist.setSlave(dataSource);
            datasourceKey = dataSource.getGuid() + "_slave";
        } else {
            DATASOURCE_PROPERTIES_MAP.put(dataSource.getGuid(), dataSource);
        }
        try {
            DATASOURCE_MAP.put(datasourceKey, druid);
        } catch (Exception e) {
            log.error("初始化数据源{}失败", datasourceKey);
            e.printStackTrace();
        }
    }

    public void afterPropertiesSet(EcaDataSourceProperties ecaDataSourceProperties) throws Exception {
        Properties properties = new Properties();
        DruidDataSource druid = ecaDataSourceProperties.getDruid();
        if (ecaDataSourceProperties.getDruid() == null) {
            druid = new DruidDataSource();
            ecaDataSourceProperties.setDruid(druid);
            properties = ecaConfig.getDefaultDruidConfig();
        }
        druid.setUsername(ecaDataSourceProperties.getUsername());
        druid.setPassword(securityHelper.DecryptConnPwd(ecaDataSourceProperties.getPassword()));
        druid.setUrl(ecaDataSourceProperties.getUrl());
        druid.setDriverClassName(ecaDataSourceProperties.getDriverClassName());
        DruidDataSourceFactory.config(druid, properties);
    }

    /**
     * 获取数据库的操作类
     *
     * @param datasourceGuid
     * @return
     * @throws Exception
     */
    private Dao getDao(String datasourceGuid) throws Exception {
        return this.getDao(datasourceGuid, false);
    }

    /**
     * 获取数据库的操作类
     *
     * @param datasourceGuid
     * @return
     */
    private Dao getDao(String datasourceGuid, boolean isSlave) {
        String key = datasourceGuid + (isSlave ? "_Slave" : "");
        if (this._Daos.containsKey(key)) {
            return this._Daos.get(key);
        } else {
            Dao dao = new NutDao(this.getDataSource(datasourceGuid, isSlave));
            this._Daos.put(key, dao);
            return dao;
        }
    }

    /**
     * 获取数据库的类型
     *
     * @param datasourceGuid
     * @return
     * @throws Exception
     */
    private DB getDbType(String datasourceGuid) throws Exception {
        return this.getDao(datasourceGuid).meta().getType();
    }

    /**
     * 原子事务
     *
     * @param run
     */
    @Override
    public void StartTrans(Runnable run) {
        Trans.exec(new Atom() {
            @Override
            public void run() {
                run.run();
            }
        });
    }

    @Override
    public DBType GetDbType(String datasourceguid) throws Exception {
        DB db = this.getDbType(datasourceguid);
        switch (db) {
            case DM:
                return DBType.DM;
            case MYSQL:
                return DBType.MYSQL;
            case ORACLE:
                return DBType.ORACLE;
            case SQLSERVER:
                return DBType.SQLSERVER;
            case SQLITE:
                return DBType.SQLITE;
            case DB2:
                return DBType.DB2;
            case PSQL:
                return DBType.PSQL;
            case GBASE:
                return DBType.GBASE;
            case SYBASE:
                return DBType.SYBASE;
            case DERBY:
                return DBType.DERBY;
            case HSQL:
                return DBType.HSQL;
            case H2:
                return DBType.H2;
            case OTHER:
                return DBType.OTHER;

        }
        return null;
    }

    /**
     * 返回值为单行数据的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception {
        return this.QueryMap(datasourceguid, sqlKey, params, null);
    }

    @Override
    public Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception {
        return this.QueryMap(datasourceguid, sqlKey, params, vars, false);
    }

    @Override
    public Map QueryMap(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars, boolean fromSlave) throws Exception {
        Sql sql = this.Execute(datasourceguid, sqlKey, params, Sqls.callback.map(), null, vars, fromSlave);
        return this.keyToUpper((Map<String, Object>) sql.getResult());
    }

    @Override
    public DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception {

        return this.QueryDataTable(datasourceguid, sqlKey, params, 0, 0);
    }

    @Override
    public DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception {
        return this.QueryDataTable(datasourceguid, sqlKey, params, 0, 0, vars);
    }

    @Override
    public DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize) throws Exception {
        return QueryDataTable(datasourceguid, sqlKey, params, startIndex, pageSize, null);
    }

    @Override
    public DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars) throws Exception {
        return QueryDataTable(datasourceguid, sqlKey, params, startIndex, pageSize, vars, false);
    }

    @Override
    public DataTable QueryDataTable(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars, boolean fromSlave) throws Exception {
        return (DataTable) this.QueryCallBack(datasourceguid, sqlKey, params, startIndex, pageSize, vars, new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                //有可能是没有返回表数据
                if (rs == null) {
                    return null;
                }
                ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
                int columnCount = md.getColumnCount();   //获得列数
                // 查询的最大记录数，等于-1时不限制
                int maxRowNumber = -1;
                if (Objects.nonNull(vars) && vars.containsKey("maxRowNumber")) {
                    maxRowNumber = Integer.parseInt(vars.get("maxRowNumber").toString());
                }

                //以DataTable形式返回
                DataTable dt = ResultSet2DataTable(rs, maxRowNumber);

                //为DataTabel添加列信息
                HashMap<String, DataColumn> dcs = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    DataColumn dc = new DataColumn();

                    //列名
                    String colName = md.getColumnLabel(i).toUpperCase();
                    dc.setColumnName(colName);

                    //jdbcType
                    int jdbcType = md.getColumnType(i);
                    dc.setJdbcType(jdbcType);

                    //java类型
                    Class<?> javaType = jdbcType2JavaType(jdbcType);
                    dc.setDataType(javaType);

                    dcs.put(colName, dc);
                }

                dt.setColumns(dcs);
                return dt;
            }
        }, fromSlave);
    }


    private DataTable ResultSet2DataTable(ResultSet rs, int maxRowNumber) throws SQLException {
        ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据

        int columnCount = md.getColumnCount();   //获得列数

        //lzl 2020-03-31 如果sql中查询字段有同名的
        HashMap<String, Integer> columnsRepeat = new HashMap<>();//同名次数
        HashMap<Integer, String> columnsName = new HashMap<>();//索引列名
        for (int i = 1; i <= columnCount; i++) {
            String fieldName = md.getColumnLabel(i).toUpperCase();
            if (columnsRepeat.containsKey(fieldName) == false) {
                columnsRepeat.put(fieldName, 0);
            } else {
                int rep = columnsRepeat.get(fieldName) + 1;
                columnsRepeat.put(fieldName, rep);
                fieldName = fieldName + "_" + rep;
            }
            columnsName.put(i, fieldName);
        }


        //以DataTable形式返回
        DataTable dt = new DataTable();
        while (rs.next() && (rs.getRow() < maxRowNumber || maxRowNumber == -1)) {
            DataRow row = new DataRow();
            for (int i = 1; i <= columnCount; i++) {
                String fieldName = columnsName.get(i);

                Object o = rs.getObject(i);
                if (o instanceof Blob) {
                    o = Convert.parseBytes((Blob) o);
                }
                if (o instanceof Clob) {
                    o = Convert.clobToString((Clob) o);
                }
                row.put(fieldName, o);
            }
            dt.add(row);
        }
        rs.close();
        return dt;
    }

    /*
    转换jdbc类型到java类型
     */
    private Class<?> jdbcType2JavaType(int dataType) {
        switch (dataType) {
            case BIGINT:
                return Long.class;
            case BINARY:
                return byte[].class;
            case BIT:
                return Boolean.class;
            case BLOB:
                return byte[].class;
            case CHAR:
                return String.class;
            case CLOB:
                return String.class;
            case DATE:
                return java.util.Date.class;
            case DECIMAL:
                return java.math.BigDecimal.class;
            case DOUBLE:
                return Double.class;
            case FLOAT:
                return Double.class;
            case INTEGER:
                return Integer.class;
            case JAVA_OBJECT:
                return Object.class;
            case LONGVARBINARY:
                return byte[].class;
            case LONGVARCHAR:
                return String.class;
            case NUMERIC:
                return java.math.BigDecimal.class;
            case OTHER:
                return Object.class;
            case REAL:
                return Float.class;
            case SMALLINT:
                return Integer.class;
            case TIME:
                return java.util.Date.class;
            case TIMESTAMP:
                return java.util.Date.class;
            case TINYINT:
                return Byte.class;
            case VARBINARY:
                return byte[].class;
            case VARCHAR:
                return String.class;
            default:
                break;
        }
        return null;
    }


    public Object QueryCallBack(String datasourceguid, String sqlKey, HashMap<String, Object> params, SqlCallback callback) throws Exception {
        Sql sql = this.Execute(datasourceguid, sqlKey, params, callback, null, null);
        return sql.getResult();
    }

    public Object QueryCallBack(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars, SqlCallback callback) throws Exception {
        return QueryCallBack(datasourceguid, sqlKey, params, startIndex, pageSize, vars, callback, false);
    }

    public Object QueryCallBack(String datasourceguid, String sqlKey, HashMap<String, Object> params, int startIndex, int pageSize, HashMap<String, Object> vars, SqlCallback callback, boolean fromSlave) throws Exception {
        Pager pager = null;
        if (pageSize != 0) {
            //startIndex表示的是数据开始行，不是页码
            int pageIndex = startIndex / pageSize + 1;
            pager = this.getDao(datasourceguid, fromSlave).createPager(pageIndex, pageSize);
        }

        Sql sql = this.Execute(datasourceguid, sqlKey, params, callback, pager, vars, fromSlave);
        return sql.getResult();
    }

    /**
     * @return java.util.List<cn.ecasoft.configproperties.EcaDataSourceProperties>
     * @throws Exception
     * @Description: 查询数据源列表
     * @param: datasourceguid
     * @param: sqlkey
     * @MethodName QueryProjectDataSources
     * @author CaiZhiGang
     * @date 2021/5/6 11:10
     */
    private List<EcaDataSourceProperties> QueryProjectDataSources(String datasourceguid, String sqlkey) throws Exception {
        return this.castList(this.QueryCallBack(datasourceguid, sqlkey, null,
                new SqlCallback() {
                    @SneakyThrows
                    @Override
                    public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                        List<EcaDataSourceProperties> dataSourcePropertiesList = new ArrayList<>();
                        while (resultSet.next()) {
                            String name = resultSet.getString(DataSourceConstants.DS_NAME);
                            String username = resultSet.getString(DataSourceConstants.DS_USER_NAME);
                            String password = resultSet.getString(DataSourceConstants.DS_USER_PWD);
                            String url = resultSet.getString(DataSourceConstants.DS_JDBC_URL);
                            String guid = resultSet.getString(DataSourceConstants.DS_ROW_GUID);
                            EcaDataSourceProperties ecaDataSourceProperties = new EcaDataSourceProperties();
                            ecaDataSourceProperties.setDriverClassName(DataSourceConstants.DS_DRIVER);
                            ecaDataSourceProperties.setUsername(username);
                            ecaDataSourceProperties.setPassword(password);
                            ecaDataSourceProperties.setUrl(url);
                            ecaDataSourceProperties.setGuid(guid);
                            dataSourcePropertiesList.add(ecaDataSourceProperties);

                        }
                        resultSet.close();
                        return dataSourcePropertiesList;
                    }
                }), EcaDataSourceProperties.class);
    }

    private <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * 返回值为数量的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public int QueryInt(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception {
        return this.QueryInt(datasourceguid, sqlKey, params, null);
    }

    /**
     * 根据传入的对象，动态生成insert、update赋值语句，修改暂仅支持根据主键修改 <br/>
     * 传入的对象主键字段需添加注解@EcaTableId，加该注解的字段不会被修改，insert不影响 <br/>
     * 字段为static或者final也不会被认定为数据库字段
     *
     * @param datasourceguid 数据源guid
     * @param sqlKey         配置的sql键值
     * @param entity         对象
     * @return
     * @throws Exception
     */
    @Override
    public int QueryInt(String datasourceguid, String sqlKey, Object entity) throws Exception {
        String strSql = ecaSqlsConfig.getMap().get(sqlKey).toString();
        StringBuilder updateSetParams = new StringBuilder();
        StringBuilder insertParams = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        Sql sql = Sqls.create(strSql);
        boolean insert = sql.isInsert();
        boolean update = sql.isUpdate();
        Class<?> classType = entity.getClass();
        Field[] declaredFields = classType.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            EcaTableId annotation = field.getAnnotation(EcaTableId.class);
            // 如果是静态、final字段，则不处理
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String fieldName = field.getName();
            String tableFieldName = humpToUnderline(fieldName);
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            //获得和属性对应的getXXX()方法的名字
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            //获得和属性对应的setXXX()方法的名字
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            //获得和属性对应的getXXX()方法
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            //获得和属性对应的setXXX()方法
            Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});
            //调用原对象的getXXX()方法
            Object value = getMethod.invoke(entity, new Object[]{});
            // 如果value值不为null将字段添加到sql语句中，否则不对该字段进行修改
            if (Objects.nonNull(value)) {
                if (insert) {
                    insertParams.append(tableFieldName).append(",");
                    insertValues.append("@").append(fieldName).append(",");
                }
                //是修改条件且不是主键
                if (update && Objects.isNull(annotation)) {
                    updateSetParams.append(tableFieldName).append("=@").append(fieldName).append(",");
                }
            }
        }
        HashMap<String, Object> vars = new HashMap<>(10);

        if (insert) {
            vars.put("insertParams", StringUtils.strip(insertParams.toString(), ","));
            vars.put("insertValues", StringUtils.strip(insertValues.toString(), ","));
        }
        if (update) {
            vars.put("updateSetParams", StringUtils.strip(updateSetParams.toString(), ","));
        }

        sql = this.Execute(datasourceguid, JsonData.parseObject(JsonData.toString(entity), HashMap.class), strSql, Sqls.callback.integer(), null, vars, false);

        return sql.getUpdateCount();
    }

    @Override
    public int QueryInt(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception {
        Sql sql = this.Execute(datasourceguid, sqlKey, params, Sqls.callback.integer(), null, vars);
        return sql.getUpdateCount();
    }

    /**
     * 转换小驼峰为下划线
     *
     * @param filedName
     * @return
     */
    private String humpToUnderline(String filedName) {
        StringBuilder sb = new StringBuilder(filedName);
        int temp = 0;
        if (!filedName.contains("_")) {
            for (int i = 0; i < filedName.length(); i++) {
                if (Character.isUpperCase(filedName.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 没有返回值的SQL查询
     *
     * @param datasourceguid
     * @param sqlKey
     * @param params
     * @throws Exception
     */
    @Override
    public void QueryVoid(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception {
        this.QueryVoid(datasourceguid, sqlKey, params, null);
    }

    @Override
    public void QueryVoid(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception {
        this.Execute(datasourceguid, sqlKey, params, null, null, vars);
    }

    @Override
    public boolean IsSelectCommandText(String sqlStr) {
        sqlStr = sqlStr.toLowerCase().trim();
        if (IsProcCommandText(sqlStr)) return false;
        if (sqlStr.indexOf("select ") > -1 || sqlStr.indexOf("select\r") > -1 || sqlStr.indexOf("select\t") > -1 || sqlStr.indexOf("select\n") > -1)
            return true;
        return false;
    }

    @Override
    public boolean IsProcCommandText(String sqlStr) {
        sqlStr = sqlStr.toLowerCase().trim();
        if (sqlStr.indexOf("call ") > -1 || sqlStr.indexOf("call\n") > -1 || sqlStr.indexOf("call\t") > -1) return true;
        return false;
    }

    @Override
    public Integer GetPkValue(String datasourceguid, String tableName, String fieldName) throws Exception {

        HashMap<String, Object> param = new HashMap<>();
        param.put("tablename", tableName);
        param.put("fieldname", fieldName);
        param.put("OUTpkvalue", "0");
        param.put("ds_id", "1");
        this.QueryVoid(datasourceguid, "getpkvalue", param);
        return Convert.parseInt(param.get("OUTpkvalue"));

    }

    @Override
    public List<DataColumn> GetColumns(String datasourceguid, String strSql, HashMap<String, Object> params) throws Exception {
        ecaSqlsConfig.getMap().put(strSql, strSql);
        Sql sql = this.Execute(datasourceguid, strSql, params, new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                ResultSetMetaData md = rs.getMetaData();
                List<DataColumn> list = new LinkedList<DataColumn>();

                int columnCount = md.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    DataColumn dc = new DataColumn();

                    //列名
                    String colName = md.getColumnLabel(i).toUpperCase();
                    dc.setColumnName(colName);

                    //jdbcType
                    int jdbcType = md.getColumnType(i);
                    dc.setJdbcType(jdbcType);

                    //java类型
                    Class<?> javaType = jdbcType2JavaType(jdbcType);
                    dc.setDataType(javaType);

                    list.add(dc);
                }
                rs.close();
                return list;
            }
        }, null, null);
        return sql.getList(DataColumn.class);
    }

    /**
     * 获取数据库中所有表名称
     *
     * @param datasourceguid
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> GetTables(String datasourceguid) throws Exception {
        Connection conn = this.getDataSource(datasourceguid).getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet tables = databaseMetaData.getTables(null, null, "%", null);
        ArrayList<String> tablesList = new ArrayList<String>();
        while (tables.next()) {
            tablesList.add(tables.getString("TABLE_NAME"));
        }
        tables.close();
        return tablesList;
    }

    /**
     * 获取数据库中存储过程的所有参数
     *
     * @param datasourceguid
     * @param procName
     * @return
     * @throws Exception
     */
    @Override
    public DataTable GetProcParams(String datasourceguid, String procName) throws Exception {
        Connection conn = this.getDataSource(datasourceguid).getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getProcedureColumns(conn.getCatalog(), conn.getSchema(), procName, "%");
        //以DataTable形式返回
        DataTable dt = new DataTable();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            DataRow row = new DataRow();
            for (int i = 1; i <= columnCount; i++) {
                row.put(rsmd.getColumnLabel(i), rs.getObject(i));
            }
            dt.add(row);
        }
        rs.close();
        return dt;
    }

    @Override
    public DataTable GetColumns(String datasourceguid, String tableName) throws Exception {
        Connection conn = this.getDataSource(datasourceguid).getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getColumns(null, "%", tableName, "%");
        //以DataTable形式返回
        DataTable dt = new DataTable();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            DataRow row = new DataRow();
            for (int i = 1; i <= columnCount; i++) {
                row.put(rsmd.getColumnLabel(i).toUpperCase(), rs.getObject(i));
            }
            dt.add(row);
        }
        rs.close();
        return dt;
    }


    @Override
    public DataTable QueryCursor(String datasourceguid, String sqlKey, HashMap<String, Object> params) throws Exception {
        return this.QueryCursor(datasourceguid, sqlKey, params, null);
    }

    @Override
    public DataTable QueryCursor(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars) throws Exception {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("OUTC1", Types.REF_CURSOR);
        HashMap<String, Object> finalParams = params;
        Sql sql = this.Execute(datasourceguid, sqlKey, params, new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                rs = (ResultSet) sql.getOutParams().get("C1");
                if (rs == null) return null;
                // 查询的最大记录数，等于-1时不限制
                int maxRowNumber = -1;
                if (Objects.nonNull(vars) && vars.containsKey("maxRowNumber")) {
                    maxRowNumber = Integer.parseInt(vars.get("maxRowNumber").toString());
                }
                DataTable dt = ResultSet2DataTable(rs, maxRowNumber);
                finalParams.put("OUTC1", dt.copy());
                return dt;
            }
        }, null, vars);
        return (DataTable) sql.getResult();
    }

    @Override
    public void QuerySqlCallback(String datasourceguid, String sqlKey, HashMap<String, Object> params, HashMap<String, Object> vars, SqlCallback callback) throws Exception {
        if (params == null) {
            params = new HashMap<>();
        }
        this.Execute(datasourceguid, sqlKey, params, callback, null, vars);
    }

    private Sql Execute(String datasourceguid, String sqlKey, HashMap<String, Object> params, SqlCallback callback, Pager pager, HashMap<String, Object> vars) throws Exception {
        return this.Execute(datasourceguid, sqlKey, params, callback, pager, vars, false);
    }

    private Sql Execute(String datasourceguid, String sqlKey, HashMap<String, Object> params, SqlCallback callback, Pager pager, HashMap<String, Object> vars, boolean fromSlave) throws Exception {

        if (!ecaSqlsConfig.getMap().containsKey(sqlKey))
            throw new Exception("未在ecasqls中配置对应的sql键！键值：" + sqlKey);
        String strSql = ecaSqlsConfig.getMap().get(sqlKey).toString();
        return this.Execute(datasourceguid, params, strSql, callback, pager, vars, fromSlave);

    }

    private Sql Execute(String datasourceguid, HashMap<String, Object> params, String strSql, SqlCallback callback, Pager pager, HashMap<String, Object> vars, boolean fromSlave) throws Exception {


        //设定一下变量参数
        if (vars != null) {
            Pattern pattern = Pattern.compile("\\$(\\w+)");
            Matcher matcher = pattern.matcher(strSql);
            while (matcher.find()) {
                String place = matcher.group(0);
                String key = matcher.group(1);
                if (vars.keySet().contains(key)) {
                    strSql = strSql.replace(place, vars.get(key).toString());
                }
            }
        }

        //修正一下sql中的参数占位符
        char placeHolder = this.getPlaceHolder(datasourceguid);//正常的占位符
        if (params != null) {
            Pattern pattern = Pattern.compile("[@?:](\\w+)");
            Matcher matcher = pattern.matcher(strSql);
            while (matcher.find()) {
                String place = matcher.group(0);
                String key = matcher.group(1);
                if (place.charAt(0) != placeHolder) {
                    strSql = strSql.replace(place, placeHolder + key);
                }
            }
        }
        Sql sqlObject = null;
        if (strSql.startsWith("-- NOT FROM SLAVE\n") || strSql.startsWith("-- NOT FROM SLAVE")) {
            strSql = strSql.replace("-- NOT FROM SLAVE", "");
            sqlObject = Sqls.create(strSql);
            fromSlave = false;
        } else {
            sqlObject = Sqls.create(strSql);
            if (sqlObject.isSelect()) {
                fromSlave = true;
            }
        }

        Dao dao = this.getDao(datasourceguid, fromSlave);
        sqlObject.changePlaceholder(placeHolder, '$');//改变占位符
        if (callback != null) {
            sqlObject.setCallback(callback);
        }

        //设定一下sql参数
        if (params != null) {
            Sql finalSqlObject = sqlObject;
            params.forEach((k, v) -> {
                SqlType sqlType = finalSqlObject.getSqlType();
                //传出参数，带OUT并且是存储过程的时候
                if (k.startsWith("OUT") && (sqlType == SqlType.CALL || sqlType == SqlType.EXEC)) {
                    //游标参数
                    if (k.equalsIgnoreCase("OUTC1")) {
                        finalSqlObject.params().set(k, Types.REF_CURSOR);
                    } else {
                        finalSqlObject.params().set(k, Types.VARCHAR);
                    }
                } else {
                    if (v instanceof byte[]) {
                        InputStream input = new ByteArrayInputStream((byte[]) v);
                        finalSqlObject.params().set(k, input);
                    } else {
                        finalSqlObject.params().set(k, v);
                    }
                }
            });
        }

        //如果有分页参数
        if (pager != null) {
            sqlObject.setPager(pager);
        }
        dao.execute(sqlObject);
        setOutParams(params, sqlObject);
        return sqlObject;
    }

    private int ExecuteBatch(String datasourceguid, List<HashMap<String, Object>> params, String strSql, SqlCallback callback, HashMap<String, Object> vars, boolean fromSlave, boolean isTrans) throws Exception {
        Dao dao = this.getDao(datasourceguid, fromSlave);

        //修正一下sql中的参数占位符
        char placeHolder = this.getPlaceHolder(datasourceguid);//正常的占位符
        if (params != null) {
            Pattern pattern = Pattern.compile("[@?:](\\w+)");
            Matcher matcher = pattern.matcher(strSql);
            while (matcher.find()) {
                String place = matcher.group(0);
                String key = matcher.group(1);
                if (place.charAt(0) != placeHolder) {
                    strSql = strSql.replace(place, placeHolder + key);
                }
            }
        }

        Sql sqlObject = Sqls.create(strSql);
        sqlObject.changePlaceholder(placeHolder, '$');//改变占位符
        if (callback != null) {
            sqlObject.setCallback(callback);
        }

        // 批量设定sql参数，不考虑存储过程
        if (!CollectionUtils.isEmpty(params)) {
            for (HashMap<String, Object> hashMap : params) {
                hashMap.forEach((key, value) -> sqlObject.params().set(key, value));
                sqlObject.addBatch();
            }
        }
        // 选择是否进行事务性批量操作
        if (isTrans) {
            Trans.exec(() -> dao.execute(sqlObject));
        } else {
            dao.execute(sqlObject);
        }
        return sqlObject.getUpdateCount();
    }


    /**
     * 获取应该的占位符
     *
     * @param datasourceGuid
     * @return
     */
    private char getPlaceHolder(String datasourceGuid) throws Exception {
        DB db = this.getDbType(datasourceGuid);
        char c = '@';
        switch (db) {
            case ORACLE:
            case DM:
                c = ':';
                break;
            case SQLSERVER:
                c = '@';
                break;
            case MYSQL:
                c = '?';
                break;
        }
        return c;
    }

    private void setOutParams(HashMap<String, Object> params, Sql sql) throws SQLException {
        if (params != null) {
            boolean hasOut = false;
            for (Map.Entry<String, Object> entry :
                    params.entrySet()) {
                if (entry.getKey().startsWith("OUT")) {
                    hasOut = true;
                }
            }
            if (hasOut) {
                Record record = sql.getOutParams();
                if (record != null) {
                    for (Map.Entry<String, Object> entry :
                            params.entrySet()) {
                        String key = entry.getKey();
                        if (key.startsWith("OUT")) {
                            key = key.substring(3);
                            Object val = record.get(key);

                            //如果是数据集
                            if (val instanceof ResultSet) {
                                if (entry.getValue() == null && ((ResultSet) val).isClosed() == false) {
                                    entry.setValue(ResultSet2DataTable((ResultSet) val, -1));
                                }
                            } else {
                                entry.setValue(val);
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 转大写
     *
     * @param map
     * @return
     */
    private Map<String, Object> keyToUpper(Map<String, Object> map) throws SQLException {
        if (map == null) return null;
        Map<String, Object> resultMap = new HashMap<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String newKey = key.toUpperCase();
            Object oValue = map.get(key);
            if (oValue instanceof SimpleBlob) {
                SimpleBlob sb = (SimpleBlob) oValue;
                oValue = sb.getBytes((long) 1, (int) sb.length());
            }
            resultMap.put(newKey, oValue);
        }
        return resultMap;
    }

    /**
     * 生成主键值（雪花id)）
     *
     * @param tableName
     * @return
     */
    @Override
    public Long GetPkValue(String tableName) {
        return IdUtil.getSnowflakeNextId();
    }
}
