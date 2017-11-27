package top.tosim.actrainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.tosim.actrainer.dao.UserDao;
import top.tosim.actrainer.dto.PicConfirmData;
import top.tosim.actrainer.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/files")
@PropertySource({"classpath:global.properties"})
public class FileController {
    @Autowired
    UserDao userDao;

    @Value("${pic_save_dir}")
    String picSaveDirs;

    @RequestMapping(value = "/pic",method = RequestMethod.GET)
    public String getUploadTestView(){
        return "showUser";
    }

    @RequestMapping(value="/pic",method = RequestMethod.POST)
    @ResponseBody
    public List<PicConfirmData> submitPic(@RequestParam(value = "userIcon",required = false) MultipartFile[] fileImage,
                            HttpServletRequest request){
        if(fileImage == null || request.getSession(true).getAttribute("user") == null){
            return new ArrayList<PicConfirmData>();
        }
        return savePic(fileImage,request);
    }

    private List<PicConfirmData> savePic(MultipartFile[] fileImage,HttpServletRequest request){
        User user = (User)request.getSession(true).getAttribute("user");
        //为图片改名
        String oldName = "";
        String newName = "";
        String extension = "";
        //图片按照上传时间命名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //存储每张图片的信息
        List<PicConfirmData> resultList = new ArrayList<PicConfirmData>();
        //获取配置文件中图片的存储路径
        String contextPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath("/") + picSaveDirs;  //服务器存储的路径
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+contextPath+"/";   //网上可访问的资源路径

        //依次将图片存储到path路径下
        for(int i=0;i<fileImage.length;i++){
            System.out.println(fileImage[i].getOriginalFilename());
            oldName = fileImage[i].getOriginalFilename();
            extension = oldName.substring(oldName.lastIndexOf("."));
            newName = sdf.format(new Date())+extension;
            File target = new File(realPath,newName);
            if(!target.exists()){
                target.mkdirs();
            }
            try {
                fileImage[i].transferTo(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //记录图片存储信息
            PicConfirmData pic = null;
            try {
                //只存名称，路径已知，从而节省数据库空间
                //pic = new PicConfirmData(URLEncoder.encode(oldName, "utf-8"), path+newName);
                String srcPath = basePath + picSaveDirs + "/" + newName;
                String relativePath = "/" + picSaveDirs + "/" + newName;
                System.out.println(relativePath);
                pic = new PicConfirmData(URLEncoder.encode(oldName, "utf-8"), newName,relativePath);
                //request.getSession(true).setAttribute("iconPath",relativePath);
                user.setIcon(relativePath);
                userDao.updateIconByPrimaryKey(user.getId(),user.getIcon());
                resultList.add(pic);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}


