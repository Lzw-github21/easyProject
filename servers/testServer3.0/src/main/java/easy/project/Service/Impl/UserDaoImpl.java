package easy.project.Service.Impl;

import easy.project.Service.IUserDao;

public class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
