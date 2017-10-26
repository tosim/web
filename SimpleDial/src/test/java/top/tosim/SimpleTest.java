package top.tosim;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.tosim.dao.UserDao;
import top.tosim.entity.User;

public class SimpleTest {
    @Test
    public void testData(){
        User user = new User();
        user.setUid(2);
        System.out.println(user.getUid());
    }

    @Test
    public void testUserDao(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserDao userDao = (UserDao)ctx.getBean("userDaoImpl");
        User user = userDao.getUser(1);
        System.out.println(user.getUname());
    }
}
