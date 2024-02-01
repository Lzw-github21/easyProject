package project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 李志威
 * Ctrl + Shift + T生成测试类
 * @Description
 * @date 2024/1/29
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 通过id查询单条记录
     */
    @Test
    public void selectTestV(){
        System.out.println(userMapper.selectById(1));
    }
    /**
     * 通过List<id>查询多条数据
     */
    @Test
    public void selectTestV1(){
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<User> list = userMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }
     /**
     * 通过map参数查询多条数据
     */
    @Test
    public void selectTestV2(){
        Map<String, Object> map = new HashMap<>();
   //map的key指代的是mysql表中的列名，并非java实体的属性名
        map.put("name", "Jone");
    List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
}
     /**
     * 通过QueryWrapper条件构造参数查询指定字段数据
     */
    @Test
    public void selectTestV3(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("*")   //指定查询结果字段 指定字段逗号隔开 "name","age"
                .in("age", Arrays.asList(18, 20))
                .in("name",Arrays.asList("Jone"))
                .last("limit 1");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
}
    /**
     * 新增一条记录
     * 测试证明字段名为id，不赋值，雪花算法会自动赋值uuid
     * 字段名改外prjGuid，雪花算法不会自动赋值
     */
    @Test
    public void insertTest(){
        User user = new User();
        user.setName("字母哥");
        user.setAge(18);
        user.setId(111434224235534L);
        int row = userMapper.insert(user);
        System.out.println("影响记录数："+row);
        System.out.println("雪花算法id: "+user.getId());
    }
    /**
     * 根据map字段值删除记录
     */
    @Test
    public void deleteTest(){
        //构造条件
        Map<String,Object> map = new HashMap<>();
        map.put("name","字母哥");
        map.put("age",18);
        //执行删除
        int rows = userMapper.deleteByMap(map);
        System.out.println("影响记录数：" + rows);
    }
    /**
     * 根据指定id修改数据
     */
    @Test
    public void updateTest(){
        User user = new User();
        user.setId(1L);
        user.setAge(18);
        user.setEmail("hadoopcn2@163.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }
    /**
     * 根据条件构造器QueryWrapper修改数据
     */
    @Test
    public void updateTest1(){
        UpdateWrapper<User> update = new UpdateWrapper<>();
        update.eq("name", "Jack").eq("age", 20);    //eq是MP的条件构造器，表示"等于"关系
        User user = new User();
        user.setAge(29);
        user.setEmail("hadoopcn2@163.com");
        int rows = userMapper.update(user, update);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void test(){
        System.out.println("insert into TB_GoodInfo(ROW_GUID,RecordGuid,IsGlPrj,PtjName,Mark,LhType,LhNeiRong,LhDate,YouXiaoTime,Lhfs,scDate)( select guid() ROW_GUID,cp.ROW_GUID RecordGuid,t.Isglgcxm,t.Gcxmmc,t.Mark,t.RewardType,t.RewardNeiRong,t.FPraiseDate,t.YouXiaoTime,t.FenShu,getdate() scDate\n" +
                "        from(select row_number()over(partition by--qylh.CorpName, qylh.CorpCode,\n" +
                "                        qylh.RecordGuid, xmlh.Gcxmmc, xmlh.RewardType order by xmlh.RewardNeiRong)rn,\n" +
                "                --qylh.CorpName, qylh.CorpCode,\n" +
                "                qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime,\n" +
                "        case when IsCJDW=1 then convert (numeric(8, 2), jl.FenShu)/2 else jl.FenShu end FenShu,case when xmlh.Gcxmlx = 2\n" +
                "        then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99 then 99 else NULL end\n" +
                "        Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 1 and xmlh.RewardType in (1001, 1002, 1003, 1009)\n" +
                "        and getdate () < dateadd(m, jl.YouXiaoMonth, xmlh.FPraiseDate))t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid and cp.ProjectType = t.Gcxmlx\n" +
                "                -- and cp.CorpName = t.CorpName and cp.CorpCode = t.CorpCode\n" +
                "        where t.rn = 1\n" +
                "        union all\n" +
                "        select guid () ROW_GUID, cp.ROW_GUID\n" +
                "        RecordGuid, t.Isglgcxm, t.Gcxmmc, t.Mark, t.RewardType, t.RewardNeiRong, t.FPraiseDate, t.YouXiaoTime, t.FenShu, getdate()\n" +
                "        scDate\n" +
                "        from(select row_number()over(partition by--qylh.CorpName, qylh.CorpCode,\n" +
                "                        qylh.RecordGuid, xmlh.RewardType order by xmlh.RewardNeiRong)rn,\n" +
                "                --qylh.CorpName, qylh.CorpCode,\n" +
                "                qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime, jl.FenShu,\n" +
                "        case when xmlh.Gcxmlx = 2 then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99\n" +
                "        then 99 else NULL end Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 0 and xmlh.RewardType in (1005, 1006, 1007, 1008)\n" +
                "        and getdate () < dateadd(m, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime), xmlh.FPraiseDate)  )t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid-- and cp.CorpName = t.CorpName and cp.CorpCode = t.CorpCode\n" +
                "        where t.rn = 1\n" +
                "        union all\n" +
                "        select guid () ROW_GUID, cp.ROW_GUID\n" +
                "        RecordGuid, t.Isglgcxm, t.Gcxmmc, t.Mark, t.RewardType, t.RewardNeiRong, t.FPraiseDate, t.YouXiaoTime, t.FenShu, getdate()\n" +
                "        scDate\n" +
                "        from(select qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime,\n" +
                "        case when notRelationAwards=1 then convert (numeric(8, 2), jl.FenShu)/2 else jl.FenShu end FenShu,\n" +
                "        case when xmlh.Gcxmlx = 2 then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99\n" +
                "        then 99 else NULL end Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 0 and xmlh.RewardType = 1004\n" +
                "        and getdate () < dateadd(m, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime), xmlh.FPraiseDate)  )t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid )");
    }

}