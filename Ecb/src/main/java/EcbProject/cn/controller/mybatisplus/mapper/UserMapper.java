package EcbProject.cn.controller.mybatisplus.mapper;

import EcbProject.cn.controller.mybatisplus.User2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User2> {

    @Select("SELECT * FROM user")
    List<User2> getAllUsers2();
}
