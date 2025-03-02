package EcbProject.cn.controller.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Insert("select * from orders  where user_id = #{userId}")
    Order selectByUserIdAndStatus(int userId);
}
