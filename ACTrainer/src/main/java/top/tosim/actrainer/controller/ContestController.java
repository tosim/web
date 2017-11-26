package top.tosim.actrainer.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.tosim.actrainer.dao.ContestDao;
import top.tosim.actrainer.dto.ContestPageSelectDto;
import top.tosim.actrainer.dto.ProblemPageSelectDto;
import top.tosim.actrainer.entity.Contest;
import top.tosim.actrainer.entity.ContestProblem;
import top.tosim.actrainer.entity.Problem;
import top.tosim.actrainer.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/contests")
public class ContestController {
    Logger log = LoggerFactory.getLogger(ContestController.class);
    @Autowired
    ContestDao contestDao;

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Integer> getContestTotalCount(ContestPageSelectDto pageSelectDto){
        Map<String,Integer> totalCount = new HashMap<String, Integer>();
        totalCount.put("totalCount",contestDao.selectTotalCount(pageSelectDto));
        return totalCount;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public List<Contest> createContest(ContestPageSelectDto pageSelectDto){
        pageSelectDto.validateAndCalculateStart(15);
        List<Contest> ret = contestDao.selectPartByPage(pageSelectDto);
        log.info(JSON.toJSONString(ret));
        return ret;
    }
    /*
    * 创建一场比赛，不涉及题目描述的修改
    * {
	        "title":"testjson",
	        "startTime":1511618065170,
	        "duration":360000,
	        "problemList":[["HDU",1000],["POJ",2000]]
        }
    * */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Integer> createContest(HttpServletRequest request, @RequestBody Contest contest){
        log.info(JSON.toJSONString(contest));
        User user = (User)request.getSession(true).getAttribute("user");
        Map<String,Integer> ret = new HashMap<String,Integer>();
        if(user == null){
            ret.put("success",0);
            return ret;
        }
        contest.setUserId(user.getId());
        contestDao.insert(contest);
        log.info(contest.getId().toString());
        for(List<Object> problem : contest.getProblemList()){
            ContestProblem contestProblem = new ContestProblem();
            contestProblem.setContestId(contest.getId());
            contestProblem.setRemoteOj((String)problem.get(0));
            contestProblem.setRemoteProblemId((Integer)problem.get(1));
            log.info(JSON.toJSONString(contestProblem));
            contestDao.insertIntoContestProblem(contestProblem);
        }
        ret.put("success",1);
        return ret;
    }
    /*
    * 修改比赛的基本信息
    * title
    * startTime
    * duration
    * problemList
    * */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Integer> updateContestProblem(HttpServletRequest request, @PathVariable("id") int id,@RequestBody Contest contest){
        log.info(JSON.toJSONString(contest));
        User user = (User)request.getSession(true).getAttribute("user");
        Map<String,Integer> ret = new HashMap<String,Integer>();
        if(user == null){
            ret.put("success",0);
            return ret;
        }
        int contestId = id;
        if(contest.getId() == null || id != contest.getId()){
            ret.put("success",0);
            return ret;
        }
        contestDao.updateByPrimaryKeySelective(contest);
        for(List<Object> problem : contest.getProblemList()){
            ContestProblem contestProblem = new ContestProblem();
            contestProblem.setContestId(contestId);
            contestProblem.setRemoteOj((String)problem.get(0));
            contestProblem.setRemoteProblemId((Integer)problem.get(1));
            log.info(JSON.toJSONString(contestProblem));
            contestDao.insertIntoContestProblem(contestProblem);
        }
        ret.put("success",1);
        return ret;
    }

    @RequestMapping(value = "/{id}/{remoteOj}/{remoteProblemId}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Integer> modifyProblem(HttpServletRequest request,
                                             @PathVariable("id") int id,
                                             @PathVariable("remoteOj") String remoteOj,
                                             @PathVariable("remoteProblemId") String remoteProblemId,
                                             @RequestBody Problem problem){
        User user = (User)request.getSession(true).getAttribute("user");
        Map<String,Integer> ret = new HashMap<String,Integer>();
        if(user == null){
            ret.put("success",0);
            return ret;
        }
        int contestId = id;
        //如果这个问题没有更新过
        if(contestDao.selectFromContestProblem(contestId,remoteOj,remoteProblemId) == 0){
            contestDao.updateContestProblemForEdit(contestId,remoteOj,remoteProblemId);
        }
        return null;
    }
}
