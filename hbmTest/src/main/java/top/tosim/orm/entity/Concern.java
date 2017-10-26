package top.tosim.orm.entity;

import java.util.Date;

public class Concern {
    private Integer id;
    private User concerner;
    private User concerned;
    private Date concern_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getConcerner() {
        return concerner;
    }

    public void setConcerner(User concerner) {
        this.concerner = concerner;
    }

    public User getConcerned() {
        return concerned;
    }

    public void setConcerned(User concerned) {
        this.concerned = concerned;
    }

    public Date getConcern_time() {
        return concern_time;
    }

    public void setConcern_time(Date concern_time) {
        this.concern_time = concern_time;
    }
}
