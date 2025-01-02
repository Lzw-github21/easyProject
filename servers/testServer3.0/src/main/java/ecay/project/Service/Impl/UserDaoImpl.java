package ecay.project.Service.Impl;

import ecay.project.Service.IUserDao;

public class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
