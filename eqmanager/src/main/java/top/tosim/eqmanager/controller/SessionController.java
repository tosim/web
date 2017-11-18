package top.tosim.eqmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.tosim.eqmanager.entity.Equipment;
import top.tosim.eqmanager.entity.User;
import top.tosim.eqmanager.service.EquipmentService;
import top.tosim.eqmanager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionController {
    Logger logger = LoggerFactory.getLogger(SessionController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newSessionPage(){
        return "login";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String newSession(HttpServletRequest req,@RequestParam String userName, @RequestParam String password){
        User user = userService.loginCheck(userName,password);
        if(user != null){
            req.getSession().setAttribute("user",user);
            List<Equipment> equipmentList = equipmentService.getEquipmentList(user);
            req.getSession().setAttribute("equipmentList",equipmentList);
            return "user_home";
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteSession(HttpServletRequest req){
        logger.info(req.getSession().getId());
        HttpSession session = req.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute("user");
        }
        System.out.println("get DELETE request!");
        return "redirect:/sessions/new";
    }
}
