package project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import project.entity.User;

/**
 * @author 李志威
 * @Description
 * @date 2024/1/26
 */
public interface UserMapper extends BaseMapper<User> {
    
}
