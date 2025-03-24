package EcbProject.cn.controller.NutzHelper;

import EcbProject.cn.controller.NutzHelper.Enum.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Dee
 * @date 2022/9/30
 * <p>Description:
 */
@Service
public class NutzHelperImpl implements NutzHelper {

    @Autowired
    private Dao dao;
    @Autowired
    @Qualifier("dmsdao")
    private Dao dmsdao;

    @Autowired
    private HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger("[DeeHelper_Error]");

    public Dao getDao(String datasourceGuid){
        switch (datasourceGuid){
            case Constants.DM_MASTER_GUID:
                return this.dao;
            case Constants.DM_SLAVE_GUID:
                return this.dmsdao;
            default:
                return this.dao;
        }
    }

    @Override
    public QueryResult pageList(String datasourceGuid, Sql sql) {
        return this.pageList(datasourceGuid, sql, 1, 20, Sqls.callback.records(), Record.class);
    }

    @Override
    public QueryResult pageList(String datasourceGuid, Sql sql, int current, int size) {
        return this.pageList(datasourceGuid, sql, current, size, Sqls.callback.records(), Record.class);
    }

    @Override
    public <T> QueryResult pageList(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz) {
        return this.pageList(datasourceGuid, sql, 1, 20, callback, clazz);
    }


    @Override
    public <T> QueryResult pageList(String datasourceGuid, Sql sql, int current, int size, SqlCallback callback, Class<T> clazz) {
        //初始化数据源
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceGuid) ? datasourceGuid : Constants.DM_MASTER_GUID);
        //设置分页
        Pager pager = dao.createPager(current, size);
        sql.setPager(pager);
        //回调类型
        sql.setCallback(ObjectUtils.isNotEmpty(callback)?callback:Sqls.callback.records());
        //实体设置
        sql.setEntity(dao.getEntity(clazz));
        //执行sql
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        List<T> list = sql.getList(clazz);

        long count = Daos.queryCount(dao, sql);
        pager.setRecordCount((int) count);
        return new QueryResult(list, pager);
    }

    @Override
    public int queryInt(String datasourceGuid, Sql sql) {
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceGuid) ? datasourceGuid : Constants.DM_MASTER_GUID);
        sql.setCallback(Sqls.callback.integer());
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        return sql.getUpdateCount();
    }

    @Override
    public Record queryOne(String datasourceGuid, Sql sql) {
        return this.queryOne(datasourceGuid, sql, Sqls.callback.record(), Record.class);
    }

    @Override
    public <T> T queryOne(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz) {
        //初始化数据源
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceGuid) ? datasourceGuid : Constants.DM_MASTER_GUID);
        //回调类型
        sql.setCallback(ObjectUtils.isNotEmpty(callback)?callback:Sqls.callback.record());
        //实体设置
        sql.setEntity(dao.getEntity(clazz));
        //执行sql
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        return sql.getObject(clazz);
    }



    @Override
    public List<Record> queryList(String datasourceGuid, Sql sql) {
        return this.queryList(datasourceGuid, sql, Sqls.callback.records(), Record.class);
    }

    @Override
    public <T> List<T> queryList(String datasourceGuid, Sql sql, SqlCallback callback, Class<T> clazz) {
        //初始化数据源
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceGuid) ? datasourceGuid : Constants.DM_MASTER_GUID);
        //回调类型
        sql.setCallback(ObjectUtils.isNotEmpty(callback)?callback:Sqls.callback.records());
        //实体设置
        sql.setEntity(dao.getEntity(clazz));
        //执行sql
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        return sql.getList(clazz);
    }

    @Override
    public int queryCount(String datasourceGuid, Sql sql) {
        //初始化数据源
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceGuid) ? datasourceGuid : Constants.DM_MASTER_GUID);
        //回调类型
        sql.setCallback(Sqls.callback.integer());
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        return sql.getInt();
    }

    @Override
    public QueryResult queryResult(String datasourceGuid, Sql sql, Sql countSql, int current, int size) {
        List<Record> records = this.queryList(datasourceGuid, sql);
        int count = this.queryCount(datasourceGuid, countSql);
        Pager pager = new Pager(current, size);
        pager.setRecordCount(count);
        return new QueryResult(records,pager);
    }

    @Override
    public Integer GetPkValue(String datasourceguid, String tableName, String fieldName) {
        Sql sql = Sqls.create("call GetPKValue (@tablename,@fieldname,@OUTpkvalue,@ds_id)");
        sql.params().set("tablename",tableName).set("fieldname",fieldName).set("OUTpkvalue",0).set("ds_id","1");
        Dao dao = this.getDao(StringUtils.isNotBlank(datasourceguid) ? datasourceguid:Constants.DM_MASTER_GUID);
        try {
            dao.execute(sql);
        } catch (Exception e) {
            this.loggerExcetion(e);
            throw new RuntimeException(e.getMessage());
        }
        Record outParams = sql.getOutParams();
        return outParams.getInt("pkvalue");
    }

    private void loggerExcetion(Exception e) {
        String fileName = "";
        String methodName = "";
        int lineNumber = 0;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if(stackTraceElement.toString().contains("cn.ecasoft.dataexchange") && !stackTraceElement.toString().contains("common")){
                fileName = stackTraceElement.getFileName();
                methodName = stackTraceElement.getMethodName();
                lineNumber = stackTraceElement.getLineNumber();
                break;
            }
        }
        logger.warn("[{}]<{}>#{}@{}: {}", fileName, request.getRequestURI(), methodName, lineNumber, e.toString());
    }

}
