package top.tosim.orm.dao;

import org.hibernate.Query;
import top.tosim.orm.entity.Picture;

import java.util.List;

public class PictureDao extends BaseHibernateDAO{
    //7、分页获取系统中最新上传的照片
    public List<Picture> getRecentPictures(int pageNo, int pageSize){
        Query query = getSession().createQuery("from Picture pic order by pic.upload_time desc");
        return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
    }
}
