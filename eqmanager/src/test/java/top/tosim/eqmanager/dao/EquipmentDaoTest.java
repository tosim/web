package top.tosim.eqmanager.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.tosim.eqmanager.config.RootConfig;
import top.tosim.eqmanager.entity.Equipment;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置类
@ContextConfiguration(classes = {RootConfig.class})
public class EquipmentDaoTest {
    @Autowired
    private EquipmentDao equipmentDao;

    @Test
    public void testGetEquipments(){
        List<Equipment> equipmentList = equipmentDao.selectEquipments();
        for(Equipment equipment:equipmentList){
            System.out.println(equipment.getName());
        }
        //检测延迟加载
        for(Equipment equipment:equipmentList){
            System.out.println(equipment.getUser().getRealName());
        }
    }
}
