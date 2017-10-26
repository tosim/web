package top.tosim.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.tosim.dao.UserDao;
import top.tosim.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public User getUser(Integer uid) {
        return (User)getCurrentSession().get(User.class,uid);
    }

}
