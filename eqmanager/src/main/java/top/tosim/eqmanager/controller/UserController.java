package top.tosim.eqmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tosim.eqmanager.entity.User;
import top.tosim.eqmanager.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String getUserNew(){
        return "regist";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getUser(User user){
        return "user_home";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String userNew(User user){
        userService.userRegist(user);
        return "login";
    }
}
