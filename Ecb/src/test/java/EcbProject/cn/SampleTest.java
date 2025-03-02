package EcbProject.cn;

import EcbProject.cn.controller.mybatisplus.User2;
import EcbProject.cn.controller.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User2> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
//        userList.forEach(System.out::println);
        System.out.println(userMapper.getAllUsers2());
    }

}