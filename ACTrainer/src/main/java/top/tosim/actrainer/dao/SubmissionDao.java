package top.tosim.actrainer.dao;

import org.apache.ibatis.annotations.Param;
import top.tosim.actrainer.entity.Submission;

public interface SubmissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Submission record);

    int insertSelective(Submission record);

    Submission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKey(Submission record);

    Submission selectByOJAndRealRunId(String Oj, Integer realRunId);
}