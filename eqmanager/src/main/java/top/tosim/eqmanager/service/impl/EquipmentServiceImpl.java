package top.tosim.eqmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tosim.eqmanager.dao.EquipmentDao;
import top.tosim.eqmanager.entity.Equipment;
import top.tosim.eqmanager.entity.User;
import top.tosim.eqmanager.service.EquipmentService;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipmentDao equipmentDao;

    public void addEquipment(Equipment equipment) {
        equipmentDao.insertSelective(equipment);
    }

    public List<Equipment> getEquipmentList(User user) {
        List<Equipment> equipmentList;
        if(user.getType() == 1){
            equipmentList = equipmentDao.selectByUserId(user.getId());
        }else{
            equipmentList = equipmentDao.selectEquipments();
        }
        return equipmentList;
    }
}
