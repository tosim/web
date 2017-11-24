package top.tosim.actrainer.tmpdao;

import java.util.List;
import java.util.Map;

public class ProblemTmpDao {
    public List<Map<String,Object>> selectByPage(Integer pageSize,Integer pageNum){
        int startId = (pageNum - 1)*pageSize;
        return null;
    }
}
