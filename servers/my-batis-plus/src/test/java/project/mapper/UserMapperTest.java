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



}