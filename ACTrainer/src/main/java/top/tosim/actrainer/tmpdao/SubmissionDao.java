package top.tosim.actrainer.tmpdao;

import top.tosim.actrainer.entity.Submission;

public class SubmissionDao {
    public static void saveSubmission(Submission submission){
        System.out.println(submission.getRealRunId() + " saved" + "status = " + submission.getStatus());
    }
}
