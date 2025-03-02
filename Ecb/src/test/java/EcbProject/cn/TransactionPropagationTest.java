package EcbProject.cn;

import EcbProject.cn.controller.Transactional.Order;
import EcbProject.cn.controller.Transactional.OrderMapper;
import EcbProject.cn.controller.Transactional.OrderService;
import EcbProject.cn.controller.Transactional.StatusEnum;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionPropagationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testRequired() {
        Order order = new Order();
        order.setUserId(1L);
//        order.setStatus("PENDING");
        order.setTotalAmount(new BigDecimal("100.00"));
        orderService.createOrder(order);
    }
    @Test
    public void readUncommittedTest() {
        orderService.readUncommittedTest();
    }

    @Test
    public void testRequired2() {
        System.out.println(JSON.toJSONString(orderService.selectByUserIdAndStatus(1234)));
    }

    @Test
    public void testEnum() {
        String status = "DISABLED";
        Order order = new Order();
        order.setUserId(12L);
        order.setStatus( StatusEnum.valueOf(StatusEnum.class,status));
        orderMapper.insert(order);
    }
    @Test
    public void deleteEnum() {
        orderMapper.selectById(16L);
    }


}
