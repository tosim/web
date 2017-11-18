package top.tosim.eqmanager.dao;

import top.tosim.eqmanager.entity.Equipment;

import java.util.List;

public interface EquipmentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<Equipment> selectByUserId(Integer userId);

    List<Equipment> selectEquipments();
}