package top.tosim.actrainer.dao;

import org.apache.ibatis.annotations.Param;
import top.tosim.actrainer.dto.ProblemPageSelectDto;
import top.tosim.actrainer.dto.SubmissionPageSelectDto;
import top.tosim.actrainer.entity.Submission;

import java.util.List;
import java.util.Map;

public interface SubmissionDao {
    List<Map<String, Object>> selectPartByPage(SubmissionPageSelectDto pageSelectDto);
    Integer selectTotalCount(SubmissionPageSelectDto pageSelectDto);

    int deleteByPrimaryKey(Integer id);

    int insert(Submission record);

    int insertSelective(Submission record);

    Submission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKey(Submission record);

    Submission selectByOJAndRealRunId(String Oj, Integer realRunId);

    int selectAcCountByUser(Integer userId);
    int selectFailCountByUser(Integer userId);
}