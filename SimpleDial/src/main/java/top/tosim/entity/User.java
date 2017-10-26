package top.tosim.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer uid;

    @Column(name = "uname")
    private String uname;

    @Column(name = "upass")
    private String upass;
}
