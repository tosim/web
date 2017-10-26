package top.tosim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tosim.dao.UserDao;
import top.tosim.entity.User;
import top.tosim.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public User getUser(Integer uid) {
        return userDao.getUser(uid);
    }
}
