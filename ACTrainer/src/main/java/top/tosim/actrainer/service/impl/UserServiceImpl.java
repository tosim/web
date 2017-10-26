package top.tosim.actrainer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tosim.actrainer.dao.UserDao;
import top.tosim.actrainer.entity.User;
import top.tosim.actrainer.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public User getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }
}
