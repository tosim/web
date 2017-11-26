package top.tosim.actrainer.dao;

import top.tosim.actrainer.dto.ProblemPageSelectDto;
import top.tosim.actrainer.entity.Problem;

import java.util.List;
import java.util.Map;

public interface ProblemDao {
    List<Map<String, Object>> selectPartByPage(ProblemPageSelectDto pageSelectDto);
    Integer selectTotalCount(ProblemPageSelectDto pageSelectDto);

    Problem selectByOjAndPid(String oj,String pid);

    int deleteByPrimaryKey(Integer id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);

    int updateByRemoteOjAndProblemId(Problem problem);
}