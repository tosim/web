package top.tosim.eqmanager.dao;

import org.apache.ibatis.annotations.Param;
import top.tosim.eqmanager.entity.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password")String password);

    List<User> selectUsers();
}