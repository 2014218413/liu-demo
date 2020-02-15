package liu_project.controller;

import com.github.pagehelper.PageHelper;
import liu_project.image.ImgResult;
import liu_project.mapper.UserMapper;
import liu_project.tables.Gongneng;
import liu_project.tables.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
    //Mybatis的操作
    @Autowired
    UserMapper userMapper;
    //注册
    @ResponseBody
    @RequestMapping("/register")
    public void register(String username, String password, String email, String phone, String other, String grade,String banji,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        UUID tidai = UUID.randomUUID();
        userMapper.insertUser(1,""+username,""+password,""+email,""+phone,""+other,""+tidai,""+grade,""+banji);

        httpServletResponse.sendRedirect("/");
    }
    /*
    *验证登录
    * 可以设置一个专门的返回username的访问路径（使用session里面的）
    * 然后在网页的js里面通过ajax判断  把这个function放在render里面
    * 如果if里面判断的session的username为空，跳转.
    */
    @ResponseBody
    @RequestMapping("/login")
    public void login(HttpSession session,String username, String password, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        try {
            int state = userMapper.selectUser(""+username,""+password);
            if (state == 1){
                //session
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setMaxInactiveInterval(60*60*2);
                //Cookie
                Cookie cookie = new Cookie("username",username);
                httpServletResponse.addCookie(cookie);
                httpServletResponse.sendRedirect("/p");
            }
            else {
                httpServletResponse.sendRedirect("/?error=1");
            }
        }
        catch (Exception e) {
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

    @ResponseBody
    @RequestMapping("/n")
    public String getName(HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("username"));
        return (String) httpSession.getAttribute("username");
    }


    @RequestMapping("/p")
    public String person (HttpSession session,HttpServletRequest httpServletRequest) {
        if (session.getAttribute("username") == null) {
            return "error/error";
        }
        else {
            Cookie[] cookies = httpServletRequest.getCookies();
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
            //String realPath = System.getProperty("user.dir");
            String realPath = "D:\\Idea_mingbaile\\liu_project";
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

    /*
    * 发布任务模块的
    * */
    @ResponseBody
    @RequestMapping(value="saveGoods")
    private void saveImgInfo(@RequestParam("smallTit") String smallTit, @RequestParam("modules") String modules, @RequestParam("imgUrls") String imgUrls, @RequestParam("user") String user, @RequestParam("number") int number, @RequestParam("date") String time){

        switch(modules) {
            case "悬赏任务":
                userMapper.insertOne01(user,smallTit,number,imgUrls,modules,time);
            break;
            case "二手货物出售":
            break;
            case "物品交换":
            break;
            case "人力资源":
            break;
        }
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
    public String userName(@RequestParam(value = "投机者") String userName,HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("username"));
        System.out.println(userName);
        return "/person/pullProduct";
    }
}
