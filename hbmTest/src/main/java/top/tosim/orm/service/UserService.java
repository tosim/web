package top.tosim.orm.service;

import top.tosim.orm.entity.Picture;
import top.tosim.orm.entity.User;

import java.util.Date;
import java.util.List;


public interface UserService {
	//1、大V排行榜，粉丝最多的10个人
	List<User> getBigV();
	//2、分页获取指定用户的照片
	List<Picture> getUserPictures(int userId, int pageNo, int pageSize);
	//3、分页获取指定用户指定时间段内发布的照片，当start_time为空时表示开始时间不限，
	//   end_time为空时表示到当前时间
	List<Picture> getUserPictures(int userId,Date start_time,Date end_time,int pageNo,int pageSize);
	//4、获取指定用户关注的用户
	List<User> getFollows(int userId);
	//5、分页获取指定用户关注的用户的照片
	List<Picture> getFollowPictures(int userId,int pageNo,int pageSize);
	//6、分页获取指定用户关注的用户在制定时间段的照片
	List<Picture> getFollowPictures(int userId,Date start_time,Date end_time,int pageNo,int pageSize);
	//7、分页获取系统中最新上传的照片
	List<Picture> getRecentPictures(int pageNo,int pageSize);
	//8、根据用户名模糊查询用户
	List<User> findUsersByName(String userName);
	//9、根据照片名模糊查询照片信息
	List<Picture> findPictureByName(String pictureName);
	//10、根据照片的标签信息查询照片
	List<Picture> findPictureByTag(String tagName);
}
 