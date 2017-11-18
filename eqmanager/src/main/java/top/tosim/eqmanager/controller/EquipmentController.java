package top.tosim.eqmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tosim.eqmanager.entity.Equipment;
import top.tosim.eqmanager.service.EquipmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String equipmentNew(HttpServletRequest req,Equipment equipment){
        equipment.setAddTime(new Date());
        equipmentService.addEquipment(equipment);
        List<Equipment> equipmentList = (List<Equipment>)req.getSession().getAttribute("equipmentList");
        equipmentList.add(equipment);

        return "user_home";
    }

}
