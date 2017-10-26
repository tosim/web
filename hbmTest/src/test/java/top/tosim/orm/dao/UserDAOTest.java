package top.tosim.orm.dao;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import top.tosim.orm.entity.Picture;
import top.tosim.orm.entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserDAOTest {
    private UserDAO userDAO = new UserDAO();

    @Test
    public void addUserTest(){
        User user = new User();
        user.setUsername("insertUser");
        user.setName("insertUser");
        user.setIntro("i am insertUser");
        user.setGender("male");
        user.setProvince("zhejiang");
        user.setCity("shaoxing");
        user.setRegist_time("2017/10/23");
        user.setType("2");
        user.setEmail("insertUser@qq.com");
        user.setMobile("12345678910");
        user.setQq("123456789");
        user.setStatus(1);
        userDAO.save(user);
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(9);
        user.setUsername("updateUser");
        user.setName("updateUser");
        user.setIntro("i am updateUser");
        user.setEmail("updateUser@qq.com");
        userDAO.update(user);
    }

    @Test
    public void deleteUserTest(){
        User user = new User();
        user.setId(9);
        userDAO.delete(user);
    }

    @Test
    public void listUserTest(){
        List<User> list = userDAO.findAll();
        for(User u:list){
            System.out.println(JSON.toJSONString(u));
        }
    }

    @Test
    public void addPicForUserTest(){
        Picture pic = new Picture();
        pic.setName("user3");
        pic.setFname("d:/user3.jpg");
        pic.setClick_num(0);
        pic.setIntro("adada");
        pic.setUpload_time(new Date());
        pic.setTags("adad");
        userDAO.addPicForUser(pic,1);
    }

    @Test
    public void concernUserTest(){
        userDAO.concernUser(24,2);
    }
    @Test
    public void DeleteConcernUserTest(){
        userDAO.deleteConcernUser(8,1);
    }
    @Test
    public void getFansTest(){
        Set<User> uset = userDAO.getFans(1);
        for(User u:uset){
            System.out.print(u.getId()+ ", ");
        }
    }
    @Test
    public void getConcernersTest(){
        Set<User> uset = userDAO.getConcerners(1);
        for(User u:uset){
            System.out.print(u.getId()+ ", ");
        }
    }

    @Test
    public void testtest(){
        userDAO.test();
    }

    @Test
    public void getBigVTest(){
        List<User> list = userDAO.getBigV();
        for(User u:list){
            System.out.println(u.getId());
        }
    }

    @Test
    public void getUserPicturesTest(){
        List<Picture> list = userDAO.getUserPictures(1,2,2);
        for(Picture pic:list){
            System.out.println(pic.getId());
        }
    }

    @Test
    public void getUserPicturesTest2(){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            String dstr="2017-10-20";
            Date start= sdf.parse(dstr);
            Date end = new Date();
            List<Picture> list = userDAO.getUserPictures(1,start,end,1,2);
            for(Picture pic:list){
                System.out.println(pic.getId());
            }
        }catch (Exception e){
        }
    }

    @Test
    public void getFollowsTest(){
        List<User> list = userDAO.getFollows(1);
        for(User u:list){
            System.out.println(u.getId());
        }
    }

    @Test
    public void getFollowPicturesTest(){
        List<Picture> list = userDAO.getFollowPictures(2,1,2);
        for(Picture pic:list){
            System.out.println(pic.getId());
        }
    }

    @Test
    public void getFollowPicturesTest2(){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            String dstr="2017-10-20";
            Date start= sdf.parse(dstr);
            Date end = new Date();
            List<Picture> list = userDAO.getFollowPictures(1,1,2);
            for(Picture pic:list){
                System.out.println(pic.getId());
            }
        }catch (Exception e){
        }
    }
    @Test
    public void getRecentPicturesTest(){
        List<Picture> list = userDAO.getRecentPictures(1,2);
        for(Picture pic:list){
            System.out.println(pic.getId());
        }
    }
    @Test
    public void findUsersByNameTest(){
        List<User> list = userDAO.findUsersByName("User");
        for(User user:list){
            System.out.println(user.getId());
        }
    }
}
