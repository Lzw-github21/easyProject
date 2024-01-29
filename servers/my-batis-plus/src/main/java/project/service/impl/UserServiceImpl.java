package project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import project.entity.User;
import project.mapper.UserMapper;
import project.service.IUserService;

/**
 * @author 李志威
 * @Description
 * @date 2024/1/29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
