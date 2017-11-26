package top.tosim.actrainer.entity;

import java.util.List;

public class Contest {
    private Integer id;

    private String title;

    private Long startTime;

    private Long duration;

    private Integer userId;

    private List<List<Object>> problemList;

    public List<List<Object>> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<List<Object>> problemList) {
        this.problemList = problemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}