package EcbProject.cn.controller.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(Order order) {
        System.out.println("Creating order..." + JSON.toJSONString(order));
        orderMapper.insert(order);
        System.out.println("Order created successfully!" + JSON.toJSONString(order));
        createOrder2();
        throw new RuntimeException("Exception occurred!");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void createOrder2() {
        System.out.println("createOrder2开始执行: ");
        Order order = new Order();
        order.setUserId(1L);
//        order.setStatus("PAID");
        order.setTotalAmount(new BigDecimal("100.00"));
        orderMapper.insert(order);
        System.out.println("createOrder2执行成功: ");
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void readUncommittedTest() {
        // 第一次查询
        Order order = orderMapper.selectById(1L);
        System.out.println("First read: " +order.getTotalAmount());

        // 模拟其他事务修改数据（未提交）
        orderMapper.update(null, new UpdateWrapper<Order>().set("total_amount", 150.00).eq("id", 1L));

        // 第二次查询
        order = orderMapper.selectById(1L);
        System.out.println("Second read: " + order.getTotalAmount());

    }

    public Order selectByUserIdAndStatus(int userId) {
//        return orderMapper.selectByUserIdAndStatus(userId);
        return orderMapper.selectById(1L);
    }
}
