package top.tosim.eqmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import top.tosim.eqmanager.dao.UserDao;
import top.tosim.eqmanager.entity.User;

import javax.servlet.ServletContext;
import java.util.List;

@Component
@Lazy(false)
public class InitBean implements ServletContextAware{
    private static ServletContext servletContext;
    @Autowired
    private UserDao userDao;
    public void setServletContext(ServletContext servletContext) {
        InitBean.servletContext = servletContext;
        List<User> userList = userDao.selectUsers();
        servletContext.setAttribute("userList",userList);
    }
    //TODO 从配置文件初始化任何属性
    //It's so easy! let's get it;
}