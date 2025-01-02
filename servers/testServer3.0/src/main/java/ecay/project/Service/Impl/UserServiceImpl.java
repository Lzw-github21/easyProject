package ecay.project.Service.Impl;

import ecay.project.entity.User;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl {
    public List<User> findUserList(){

        return Collections.singletonList(new User("tom",18));
    }
}
