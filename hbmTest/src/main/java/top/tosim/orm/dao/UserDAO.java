package top.tosim.orm.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tosim.orm.entity.Picture;
import top.tosim.orm.entity.User;
public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	//添加用户
	public void save(User transientInstance) {
		getSession().beginTransaction();
		getSession().save(transientInstance);
		getSession().getTransaction().commit();
	}
	//修改用户
	public void update(User updateInstance){
		getSession().beginTransaction();
		getSession().update(updateInstance);
		getSession().getTransaction().commit();
	}
	//删除用户
	public void delete(User persistentInstance) {
		getSession().beginTransaction();
		getSession().delete(persistentInstance);
		getSession().getTransaction().commit();
	}
	//列出所有用户
	public List findAll() {
		String queryString = "from User";
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}
	//为用户添加图片
	public void addPicForUser(Picture pic,Integer userId){
		getSession().beginTransaction();
		User user = (User) getSession().get(User.class,userId);
		pic.setUser(user);
		getSession().save(pic);
		getSession().getTransaction().commit();
	}
	//列出用户的图片
	public Set listPicOfUser(Integer userId){
		User user = (User) getSession().get(User.class,userId);
		return user.getPictures();
	}
	//关注用户
	public void concernUser(Integer concernerId,Integer concernedId){
		User concerner = (User)getSession().get(User.class,concernerId);
		User concerned = (User)getSession().get(User.class,concernedId);
		getSession().beginTransaction();
		//获取我关注的人，添加进去
		concerner.getConcerners().add(concerned);
		getSession().getTransaction().commit();
	}
	//取消关注用户
	public void deleteConcernUser(Integer concernerId,Integer concernedId){
		User concerner = (User)getSession().get(User.class,concernerId);
		User concerned = (User)getSession().get(User.class,concernedId);
		getSession().beginTransaction();
		//获取我关注的人，添加进去
		concerner.getConcerners().remove(concerned);
		getSession().getTransaction().commit();
	}
	//列出用户粉丝
	public Set getFans(Integer userId){
		User user = (User)getSession().get(User.class,userId);
		return user.getConcerneds();
	}
	//列出用户关注对象
	public Set getConcerners(Integer userId){
		User user = (User)getSession().get(User.class,userId);
		return user.getConcerners();
	}
	//测试代码
	public void test(){
		getSession().beginTransaction();
		User user = new User();
		Picture picture = new Picture();
		picture.setUser(user);
		getSession().save(picture);
		getSession().getTransaction().commit();
	}

	//	1.获取粉丝最多的前10人，根据原生sql查询
	public List getBigV(){
		String sql = "select * " +
				     "from user,(select count(*) count,concern.concerned_id from concern group by concern.concerned_id) fans_count " +
					 "where fans_count.concerned_id = user.id " +
					 "order by fans_count.count desc";
		return  getSession().createSQLQuery(sql).addEntity(User.class).setFirstResult(0).setMaxResults(10).list();
	}

	public List<Picture> getUserPictures(int userId){
		Query query = getSession().createQuery("from Picture pic where pic.user.id=?");
		query.setInteger(0,userId);
		return query.list();
	}
	//	2.分页获取用户图片，使用HQL查询
	public List<Picture> getUserPictures(int userId, int pageNo, int pageSize){
		Query query = getSession().createQuery("from Picture pic where pic.user.id=? order by pic.id");
		query.setInteger(0,userId);
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}

	//	3.分页获取指定用户指定时间段内发布的照片
	public List<Picture> getUserPictures(int userId, Date start_time, Date end_time, int pageNo, int pageSize){
		Query query = getSession().createQuery("from Picture pic where 1=1 and pic.user.id=? and pic.upload_time>? and pic.upload_time<=? order by pic.id");
		query.setInteger(0,userId);
		query.setTimestamp(1,start_time);
		query.setTimestamp(2,end_time);
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}
	//	4.获取指定用户关注的用户
	public List<User> getFollows(int userId){
		Query query = getSession().createQuery("select user from User user,Concern con where con.concerner.id=? and user.id=con.concerned.id");
		query.setInteger(0,userId);
		return query.list();
	}
	//5.分页获取指定用户关注的用户的照片
	public List<Picture> getFollowPictures(int userId,int pageNo,int pageSize){
		Query query = getSession().createQuery("select pic from Picture pic,Concern con where con.concerner.id=? and con.concerned.id=pic.user.id order by pic.id");
		query.setInteger(0,userId);
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}

	//6、分页获取指定用户关注的用户在制定时间段的照片
	public List<Picture> getFollowPictures(int userId,Date start_time,Date end_time,int pageNo,int pageSize){
		Query query = getSession().createQuery("select pic from Picture pic,Concern con where con.concerner.id=? and con.concerned.id=pic.user.id " +
				"and pic.upload_time>? and pic.upload_time<=? order by pic.id");
		query.setInteger(0,userId);
		query.setTimestamp(1,start_time);
		query.setTimestamp(2,end_time);
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}

	//7、分页获取系统中最新上传的照片
	public List<Picture> getRecentPictures(int pageNo, int pageSize){
		Query query = getSession().createQuery("from Picture pic order by pic.upload_time desc ");
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}

	//8、根据用户名模糊查询用户
	public List<User> findUsersByName(String userName){
		Query query = getSession().createQuery("from User user where user.username like ?");
		query.setString(0,"%"+userName+"%");
		return query.list();
	}
	//9、根据照片名模糊查询照片信息
	public List<Picture> findPictureByName(String pictureName){
		Query query = getSession().createQuery("from Picture pic where pic.name like ?");
		query.setString(0,"%"+pictureName+"%");
		return query.list();
	}
	//10、根据照片的标签信息查询照片
	public List<Picture> findPictureByTag(String tagName){
		Query query = getSession().createQuery("from Picture pic where pic.tags like ?");
		query.setString(0,"%"+tagName+"%");
		return query.list();
	}

}