package top.tosim.actrainer.dao;

import org.apache.ibatis.annotations.Param;
import top.tosim.actrainer.dto.ContestPageSelectDto;
import top.tosim.actrainer.dto.ProblemPageSelectDto;
import top.tosim.actrainer.entity.Contest;
import top.tosim.actrainer.entity.ContestProblem;

import java.util.List;
import java.util.Map;

public interface ContestDao {
    List<Contest> selectPartByPage(ContestPageSelectDto pageSelectDto);
    Integer selectTotalCount(ContestPageSelectDto pageSelectDto);

    Integer insertIntoContestProblem(ContestProblem contestProblem);

    int deleteByPrimaryKey(Integer id);

    int insert(Contest record);

    int insertSelective(Contest record);

    Contest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKey(Contest record);

    Integer selectFromContestProblem(@Param("contestId") Integer contestId,@Param("remoteOj") String remoteOj, @Param("remoteProblemId") String remoteProblemId);
    int updateContestProblemForEdit(@Param("contestId") Integer contestId,@Param("remoteOj") String remoteOj, @Param("remoteProblemId") String remoteProblemId);
}