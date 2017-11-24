package top.tosim.eqmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import top.tosim.eqmanager.dao.UserDao;
import top.tosim.eqmanager.entity.User;
import top.tosim.eqmanager.service.impl.Printing;

import javax.servlet.ServletContext;
import java.util.List;

@Component
@Lazy(false)
public class InitUserList implements ServletContextAware{
    //private static ServletContext servletContext;
    @Autowired
    private UserDao userDao;
    @Autowired
    private Printing printing;
    public void setServletContext(ServletContext servletContext) {
        List<User> userList = userDao.selectUsers();
        servletContext.setAttribute("userList",userList);
        //new Thread(printing).start();
    }
}