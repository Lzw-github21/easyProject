package ecay.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author 李志威
 * @Description
 * @date 2024/1/30
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class TestControllerTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 测试redis取值
     *
     * @return
     */
    @Test
    public void testGetRedis() {
        redisTemplate.opsForValue().set("key",new ArrayList<String>().toString());
        String value = redisTemplate.opsForValue().get("key");
        if(value.equals("[]")){
            System.out.println("空");
        } else {
            System.out.println("有值");
        }
        System.out.println(value);
    }
    @Test
    public void test(){
        System.out.println("11111");
    }
}