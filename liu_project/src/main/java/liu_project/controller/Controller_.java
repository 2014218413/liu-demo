package liu_project.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.CharsetMapping;
import liu_project.image.ImgResult;
import liu_project.mapper.UserMapper;
import liu_project.tables.Gongneng;
import liu_project.tables.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
/*
* @Controller只是定义了一个控制器类，而使用@RequestMapping注解的方法才是处理请求的处理器。
 * */
@Controller
public class Controller_ {
    static String LOCK_;
    //Mybatis的操作
    @Autowired
    UserMapper userMapper;
    //注册
    @ResponseBody
    @RequestMapping("/register")
    public void register(String username, String password, String email, String phone, String other, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        UUID tidai = UUID.randomUUID();
        userMapper.insertUser(1,""+username,""+password,""+email,""+phone,""+other,""+tidai);

        httpServletResponse.sendRedirect("/");
    }
    //验证登录
    @ResponseBody
    @RequestMapping("/login")
    public void login(String username,String password,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws IOException, InterruptedException {
        String tidai = userMapper.selectUser(""+username,""+password);
        if (tidai != null){
            LOCK_ = UUID.randomUUID().toString();
            System.out.println(LOCK_);
            httpServletResponse.sendRedirect("/p?ID="+username);
        }
        else {
            httpServletResponse.sendRedirect("/?error=1");
        }
    }
    /*
    * 实现了分页
    * */
    @ResponseBody
    @RequestMapping("/demo")
    public List<User> get(@RequestParam("page") int pn,@RequestParam("limit") int ps) {
        PageHelper.startPage(pn,ps);
        return userMapper.findAll();
    }
    @ResponseBody
    @RequestMapping("/demo02")
    public int get02() {
        return userMapper.findAll().size();
    }


    @RequestMapping("/p")
    public String person () {
        if (LOCK_ == null) {
            return "error/error";
        }
        else {
            LOCK_ = null;
            return "person";
        }
    }
    /*
    * 文件上传
    * MultipartFile  是spring封装的文件上传
    * */
    @RequestMapping("/upload")
    @ResponseBody
    public ImgResult uplpad(MultipartFile file, HttpServletRequest request) {
        String desFilePath = "";
        String oriName = "";
        ImgResult result = new ImgResult();
        Map<String, String> dataMap = new HashMap<>();
        ImgResult imgResult = new ImgResult();
        try {
            // 1.获取原文件名
            oriName = file.getOriginalFilename();
            // 2.获取原文件图片后缀，以最后的.作为截取(.jpg)
            String extName = oriName.substring(oriName.lastIndexOf("."));
            // 3.生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + extName;
            // 4.获取要保存的路径文件夹
            String realPath = System.getProperty("user.dir");
            // 5.保存图片
            desFilePath = realPath + "\\src\\main\\resources\\static\\personImages\\" + newName;
            File desFile = new File(desFilePath);
            //MultipartFile里面封装好的io操作
            file.transferTo(desFile);
            System.out.println(desFilePath);
            // 6.返回保存结果信息
            //https://blog.csdn.net/qq_26570353/article/details/79599303
            result.setCode(0);
            dataMap = new HashMap<>();
            dataMap.put("src",newName);
            result.setData(dataMap);
            result.setMsg(oriName + "上传成功！");
            return result;
        } catch (IllegalStateException e) {
            imgResult.setCode(1);
            System.out.println(desFilePath + "图片保存失败");
            return imgResult;
        } catch (IOException e) {
            imgResult.setCode(1);
            System.out.println(desFilePath + "图片保存失败--IO异常");
            return imgResult;
        }
    }


    @ResponseBody
    @RequestMapping(value="saveGoods")
    private void saveImgInfo(@RequestParam("smallTit") String smallTit,@RequestParam("modules") String modules,@RequestParam("imgUrls") String imgUrls){
        System.out.println(smallTit+""+modules+""+imgUrls);
    }
    /*
    * 可以选择的任务列表
    * */
    @ResponseBody
    @RequestMapping("findGongneng")
    public List<Gongneng> findGongneng() {
        return userMapper.findGongneng();
    }

    @RequestMapping("pull")
    public String userName(@RequestParam(value = "投机者") String userName) {
        System.out.println(userName);
        return "/person/pullProduct";
    }
}
