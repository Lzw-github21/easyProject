package EcbProject.cn;

import EcbProject.cn.controller.mybatisplus.mapperTest.UserMapper2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapper2Test {

    @Autowired
    private UserMapper2 userMapper;

    @Test
    public void test(){
        System.out.println(userMapper.selectList(null));
    }
}
