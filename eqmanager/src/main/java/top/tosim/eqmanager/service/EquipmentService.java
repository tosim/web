package top.tosim.eqmanager.service;

import org.springframework.stereotype.Service;
import top.tosim.eqmanager.entity.Equipment;
import top.tosim.eqmanager.entity.User;

import java.util.List;

@Service
public interface EquipmentService {
    public void addEquipment(Equipment equipment);
    public List<Equipment> getEquipmentList(User user);
}
