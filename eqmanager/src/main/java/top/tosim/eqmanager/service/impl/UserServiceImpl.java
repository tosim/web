package top.tosim.eqmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tosim.eqmanager.dao.UserDao;
import top.tosim.eqmanager.entity.User;
import top.tosim.eqmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public void userRegist(User user) {
        user.setType(new Byte("1"));
        userDao.insertSelective(user);
    }

    public User loginCheck(String loginName, String password) {
        return userDao.selectByLoginNameAndPassword(loginName,password);
    }
}
