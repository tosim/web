package top.tosim.orm.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer id;
    private String username;
    private String name;
    private String intro;
    private String gender;
    private String province;
    private String city;
    private String regist_time;
    private String type;
    private String email;
    private String mobile;
    private String qq;
    private Integer status;

    private Set<Picture> pictures = new HashSet<Picture>();
    private Set<User> concerners = new HashSet<User>();
    private Set<User> concerneds = new HashSet<User>();

    public Set<User> getConcerners() {
        return concerners;
    }

    public void setConcerners(Set<User> concerners) {
        this.concerners = concerners;
    }

    public Set<User> getConcerneds() {
        return concerneds;
    }

    public void setConcerneds(Set<User> concerneds) {
        this.concerneds = concerneds;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegist_time() {
        return regist_time;
    }

    public void setRegist_time(String regist_time) {
        this.regist_time = regist_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
