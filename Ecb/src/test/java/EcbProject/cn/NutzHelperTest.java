package EcbProject.cn;

import EcbProject.cn.controller.NutzHelper.NutzHelper;
import EcbProject.cn.controller.NutzHelper.enity.TbRecordInfo;
import EcbProject.cn.controller.NutzHelper.enity.UserTest;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static EcbProject.cn.controller.NutzHelper.Enum.Constants.DM_MASTER_GUID;
import static EcbProject.cn.controller.NutzHelper.Enum.Constants.DM_SLAVE_GUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NutzHelperTest {

    @Autowired
    private NutzHelper nutzHelper;
    /**
     * 自定义sql查询
     */
    @Test
    public void test(){
        //不设置数据源，默认主库数据源
        Dao dao = nutzHelper.getDao(DM_MASTER_GUID);
        Sql sql = Sqls.create("select * from tbrecordinfo where id = @id");
        sql.setParam("id", 1);
        System.out.println(nutzHelper.queryOne("", sql));
        System.out.println(sql.getUpdateCount());
        System.out.println(sql.getOutParams());
        System.out.println(sql.getEntity());
        System.out.println(sql.getSourceSql());
        System.out.println(sql.getContext());
        System.out.println(sql.getResult());
    }

    /**
     * 根据实体@Name()字段查询
     */
    @Test
    public void test1(){
        Dao dao = nutzHelper.getDao(DM_MASTER_GUID);
        TbRecordInfo recordInfo = dao.fetch(TbRecordInfo.class, "b54f13e1-f997-42af-89fd-2b78ad36222f");
        TbRecordInfo[] recordInfos;
        System.out.println(recordInfo);
    }

    /**
     * 自定义where条件
     */
    @Test
    public void test2(){
        Dao dao = nutzHelper.getDao(DM_MASTER_GUID);
        SimpleCriteria cri = Cnd.cri();
        List<String> idcardList = new ArrayList<>();
        idcardList.add("911101086000175782");
        cri.where().andInStrList("CORPCODE", idcardList);
        Record record = dao.fetch("tbrecordinfo",cri);
        System.out.println(record);
    }

    /**
     * 分页查询
     */
    @Test
    public void test3(){
        Dao dao = nutzHelper.getDao(DM_MASTER_GUID);
        List<TbRecordInfo> people = dao.query(TbRecordInfo.class, Cnd.where("id", ">", 18), dao.createPager(2, 4));
        System.out.println(JSON.toJSONString(people));
        for (TbRecordInfo person : people) {
            person.setCityNum(null);
        }
        dao.update(people);
    }

    @Test
    public void test4(){
        UserTest user1 = new UserTest();
        user1.setRecordType(1);
        user1.setRowGuid(UUID.randomUUID().toString());
        UserTest user2 = new UserTest();
        user2.setRowGuid(UUID.randomUUID().toString());
        user2.setRecordType(2);
        UserTest user3 = new UserTest();
        user3.setRowGuid(UUID.randomUUID().toString());
        user3.setRecordType(3);
        Dao dao1 = nutzHelper.getDao(DM_MASTER_GUID);
        Dao dao2 = nutzHelper.getDao(DM_SLAVE_GUID);
//        dao1.insert(user1);
//        dao2.insert(user2);
//        dao2.insert(user3);
//        Trans.exec(() -> {
//            dao1.insert(user1);
//            dao2.insert(user2);
//            dao2.insert(user3);
//            throw new RuntimeException("测试事务回滚");
//        });

    }

}
