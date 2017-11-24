package top.tosim.actrainer.remote.provider.hdu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import top.tosim.actrainer.config.init.SubmissionManager;
import top.tosim.actrainer.dao.SubmissionDao;
import top.tosim.actrainer.entity.Submission;
import top.tosim.actrainer.httpclient.DedicatedHttpClient;
import top.tosim.actrainer.remote.RemoteOJ;
import top.tosim.actrainer.remote.RemoteStatusType;
import top.tosim.actrainer.remote.Submitter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HDUSubmitter implements Submitter,Runnable{
    private DedicatedHttpClient client = HDUHelper.getClient(); //HDU网络请求对象
    private String loginCookie;                                 //提交者对应的cookie，失效自动更新
    private String userName;                                    //提交者账号
    private String password;                                    //提交者密码
    private Integer failedSubmitCount;                          //连续提交失败次数，用于是否重新登录
    private SubmissionDao submissionDao;                        //数据库操作对象

    public HDUSubmitter(String userName,String password,SubmissionDao submissionDao) {
        this.submissionDao = submissionDao;
        this.failedSubmitCount = 0;
        this.userName = userName;
        this.password = password;
        this.loginCookie = HDUHelper.login(userName,password);
    }

    public Integer submitCode(Submission submission) {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("check","0"));
        formParams.add(new BasicNameValuePair("problemid",submission.getRemoteProblemId()));
        formParams.add(new BasicNameValuePair("language",submission.getLanguage()));
        formParams.add(new BasicNameValuePair("usercode",submission.getSource()));
        if(null == client.postForm(HDUHelper.getSubmitUrl(),formParams,loginCookie) ){ return -1;}
        //考虑在这边加上延迟，删去下面数据库查重操作，但是这样不准确哦
        /*try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        String html = client.get(HDUHelper.getStatusPageUrl()+"?first=&pid="+submission.getRemoteProblemId()+"&user="+this.userName+"&lang="+submission.getLanguage());

        if(null == html){ return -1; }
        Matcher matcher = Pattern.compile("<td height=22px>(\\d+)").matcher(html);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : -1;
    }

    public void run() {
        while(true){
            try{
                Submission submission = SubmissionManager.takeSubmission(RemoteOJ.HDU);
                Integer runId = this.submitCode(submission);
                //如果数据库中已经有这个OJ的realRunId的记录,说明前面查询的runId有误
                //如果提交失败
                //这里对于每次提交至少检查一遍数据库，性能考虑
                //防止没有提交上去，但是查询到了这个远程用户对于这道题的另一个用户之前的提交记录
                while(runId == -1 || null != submissionDao.selectByOJAndRealRunId(RemoteOJ.HDU.name(),runId) ){
                    System.out.println("submit failed once");
                    this.failedSubmitCount++;
                    if(this.failedSubmitCount >= 3){
                        this.loginCookie = HDUHelper.login(this.userName,this.password);
                        failedSubmitCount = 0;  //重新登录后置连续提交失败次数为0
                    }
                    if(submission.getFailedSubmitCount() < 3){//如果尝试了三次以下，就重新尝试提交
                        submission.setFailedSubmitCount(submission.getFailedSubmitCount() + 1);
                        runId = this.submitCode(submission);
                    }else{//提交失败，保存到数据库
                        submission.setStatus(RemoteStatusType.SUBMIT_FAILED_PERM.name());
                        //top.tosim.actrainer.tmpdao.SubmissionDao.saveSubmission(submission);//
                        submissionDao.updateByPrimaryKeySelective(submission);
                        break;
                    }
                }
                if(runId == -1) continue;
                submission.setRemoteOj(RemoteOJ.HDU.name());
                submission.setRemoteAccountName(this.userName);
                submission.setRealRunId(runId);
                System.out.println("submitted by " + this.userName+" and realRunId = " + runId);
                SubmissionManager.putQuerySubmission(RemoteOJ.HDU,submission);
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
