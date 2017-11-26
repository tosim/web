package top.tosim.actrainer.dao;

import org.apache.ibatis.annotations.Param;
import top.tosim.actrainer.dto.UserPageSelectDto;
import top.tosim.actrainer.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<Map<String, Object>> selectPartByPage(UserPageSelectDto pageSelectDto);
    Integer selectTotalCount(UserPageSelectDto pageSelectDto);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByAccountNameAndPass(String accountName,String password);

    int updateAcOrFailCount(@Param("userId") Integer userId, @Param("flag") Integer flag);
}