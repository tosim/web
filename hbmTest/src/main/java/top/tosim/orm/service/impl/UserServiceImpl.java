package top.tosim.orm.service.impl;

import org.hibernate.Query;
import top.tosim.orm.dao.UserDAO;
import top.tosim.orm.entity.Picture;
import top.tosim.orm.entity.User;
import top.tosim.orm.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDAO userDAO = new UserDAO();
    public List<User> getBigV() {

        return userDAO.getBigV();
    }

    public List<Picture> getUserPictures(int userId, int pageNo, int pageSize) {
        return userDAO.getUserPictures(userId,pageNo,pageSize);
    }

    public List<Picture> getUserPictures(int userId, Date start_time, Date end_time, int pageNo, int pageSize) {
        return userDAO.getUserPictures(userId,start_time,end_time,pageNo,pageSize);
    }

    public List<User> getFollows(int userId) {
        return userDAO.getFollows(userId);
    }

    public List<Picture> getFollowPictures(int userId, int pageNo, int pageSize) {
        return userDAO.getFollowPictures(userId,pageNo,pageSize);
    }

    public List<Picture> getFollowPictures(int userId, Date start_time, Date end_time, int pageNo, int pageSize) {
        return userDAO.getFollowPictures(userId,start_time,end_time,pageNo,pageSize);
    }

    public List<Picture> getRecentPictures(int pageNo, int pageSize) {
        return userDAO.getRecentPictures(pageNo,pageSize);
    }

    public List<User> findUsersByName(String userName) {
        return userDAO.findUsersByName(userName);
    }

    public List<Picture> findPictureByName(String pictureName) {
        return userDAO.findPictureByName(pictureName);
    }

    public List<Picture> findPictureByTag(String tagName) {
        return userDAO.findPictureByTag(tagName);
    }
}
