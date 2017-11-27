package top.tosim.actrainer.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import top.tosim.actrainer.dao.UserDao;
import top.tosim.actrainer.dto.UserPageSelectDto;
import top.tosim.actrainer.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;

    /*
    * 获取用户数量
    *   accountName //like this accountName
    * */
    public Map<String,Integer> getUserTotalCount(UserPageSelectDto pageSelectDto){
        Map<String,Integer> totalCount = new HashMap<String, Integer>();
        totalCount.put("totalCount",userDao.selectTotalCount(pageSelectDto));
        return totalCount;
    }

    /*
    * 获取用户排名
    * type:url params
    * page
    * size
    * accountName
    * */
    public List<Map<String,Object>> getUserRankList(UserPageSelectDto pageSelectDto){
        pageSelectDto.validateAndCalculateStart(10);
        List<Map<String,Object>> ret = userDao.selectPartByPage(pageSelectDto);
        log.info(JSON.toJSONString(ret));
        return ret;
    }

    /*
    * 获取用户详情
    * id
    * */
    public Map<String,User> getUserById(HttpServletRequest request,int id){
        User user = (User)request.getSession(false).getAttribute("user");
        Map<String,User> ret = new HashMap<String,User>();
        if(user == null || user.getId() != id){
            ret.put("user",null);
        }
        ret.put("user",user);
        return ret;
    }

    /*
    * 更新用户
    * */
    public Map<String,Integer> updateUserById(HttpServletRequest request,int id,User putUser){
        log.info("path id = " + id + " and user = " + JSON.toJSONString(putUser));
        User user = (User)request.getSession(false).getAttribute("user");
        Map<String,Integer> ret = new HashMap<String,Integer>();
        if(user == null || user.getId() != id ){
            ret.put("success",0);
            return ret;
        }
        userDao.updateByPrimaryKeySelective(putUser);
        ret.put("success",1);
        return ret;
    }

    /*
    * 用户注册
    * 注册完成之后不保存这个用户的登录状态
    * 需要用户手动登录
    * */
    public Map<String,Integer> regist(User postUser){
        Map<String,Integer> ret = new HashMap<String,Integer>();
        int result = userDao.insertSelective(postUser);
        if(result < 1){
            ret.put("success",0);
            return ret;
        }
        ret.put("success",1);
        return ret;
    }
}
