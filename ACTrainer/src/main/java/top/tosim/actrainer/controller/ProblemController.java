package top.tosim.actrainer.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tosim.actrainer.config.init.SubmissionManager;
import top.tosim.actrainer.dao.SubmissionDao;
import top.tosim.actrainer.entity.Submission;
import top.tosim.actrainer.remote.RemoteOJ;
import top.tosim.actrainer.entity.Problem;
import top.tosim.actrainer.remote.provider.hdu.HDUCrawler;

@Controller
@RequestMapping("/problem")
public class ProblemController {
    private Logger log = LoggerFactory.getLogger(ProblemController.class);
//    @Autowired
//    private SubmitterInitializer submitterInitializer;
    @Autowired
    private SubmissionDao submissionDao;

    @RequestMapping(value = "/submit",method = RequestMethod.GET)
    public String showSubmit(){
        //submitterInitializer.init();
        //((SubmitterInitializer)SpringContextUtils.getBeanByClass(SubmitterInitializer.class)).init();
        return "submit_page";
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public String doSubmit(@RequestBody Submission submission){
        System.out.println(JSON.toJSONString(submission));
        submission.initOther();
        int insertNum = submissionDao.insert(submission);
        System.out.println("insertNum = " + insertNum);
        System.out.println("submissionId = " + submission.getId());
        SubmissionManager.putSubmission(RemoteOJ.HDU,submission);
        return "submit_page";
    }

    @RequestMapping(value = "/json",method = RequestMethod.GET)
    @ResponseBody
    public Problem getPJSON(String pid){
        System.out.println("pid = " + pid);
        Problem problem = HDUCrawler.crawl(pid);
        log.info(JSON.toJSONString(problem));
        return problem;
    }
}
