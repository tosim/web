package top.tosim.actrainer.remote;

public enum RemoteStatusType {
    PENDING(false), // to submit to remote OJ

    SUBMIT_FAILED_TEMP(false), // failed submitting to remote OJ, due to unknown reason
    SUBMIT_FAILED_PERM(true), // failed submitting to remote OJ, due to known reason

    SUBMITTED(false), // submitted to remote OJ

    QUEUEING(false), // queuing in remote OJ
    COMPILING(false), // compiling in remote OJ
    JUDGING(false), // judging in remote OJ

    AC(true),
    PE(true),
    WA(true),
    TLE(true),
    MLE(true),
    OLE(true),
    RE(true),
    CE(true),
    FAILED_OTHER(true),
    ;
    private boolean finalized;
    RemoteStatusType(boolean finalized) {
        this.finalized = finalized;
    }
    public boolean isFinal(){//判断这个状态是否完成
        return this.finalized;
    }
}