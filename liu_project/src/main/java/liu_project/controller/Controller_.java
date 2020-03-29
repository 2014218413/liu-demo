package liu_project.controller;

import com.github.pagehelper.PageHelper;
import liu_project.image.ImgResult;
import liu_project.mapper.NewMapper;
import liu_project.mapper.UserMapper;
import liu_project.tables.Gongneng;
import liu_project.tables.User;
import liu_project.tables.gongNeng_One.XuanShang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

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
    Logger logger = LoggerFactory.getLogger(getClass());
    //Mybatis的操作
    @Autowired
    UserMapper userMapper;
    @Autowired
    NewMapper newMapper;
    //注册
    @ResponseBody
    @RequestMapping("/register")
    public void register(String username, String password, String email, String phone, String other, String grade,String banji,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        userMapper.insertUser(1,""+username,""+password,""+email,""+phone,""+other,"   ",""+grade,""+banji);
        newMapper.insertM(""+username,0);
        httpServletResponse.sendRedirect("/");
    }
    /*
    *验证登录
    * 可以设置一个专门的返回username的访问路径（使用session里面的）
    * 然后在网页的js里面通过ajax判断  把这个function放在render里面
    * 如果if里面判断的session的username为空，跳转.
    */
    @RequestMapping("/")
    public String init (HttpSession session) {
        session.setAttribute("username",null);
        return "index";
    }
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
                Cookie[] cookies = httpServletRequest.getCookies();
                for (int i = 0;i<cookies.length;i++) {
                    if (cookies[i].getName().equals("Car")) {
                        System.out.println("存在");
                        break;
                    };
                    if (i==cookies.length-1) {
                        Cookie cookie = new Cookie("Car","");
                        System.out.println("不存在Cookie，创建");
                        httpServletResponse.addCookie(cookie);
                    }
                }
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
    /*
    * 悬赏任务查询处
    * */
    @ResponseBody
    @RequestMapping("/xuan")
    public List<XuanShang> get03() {
//        PageHelper.startPage(pn,ps);
        return userMapper.getXuan();
    }
    @ResponseBody
    @RequestMapping("/xuanSize")
    public int get04() {
        return userMapper.getXuan().size();
    } /*
    * 二手货物查询处
    * */
    @ResponseBody
    @RequestMapping("/two")
    public List<XuanShang> getTwo() {
//        PageHelper.startPage(pn,ps);
        return userMapper.getTwoHand("twohand");
    }
    @ResponseBody
    @RequestMapping("/twoS")
    public int getTwoSize() {
        return userMapper.getTwoHand("twohand").size();
    }
    @ResponseBody
    @RequestMapping("/changeS")
    public int getChangeSize() {
        return userMapper.getTwoHand("changewu").size();
    }
    @ResponseBody
    @RequestMapping("/change")
    public List<XuanShang> getChange() {
//        PageHelper.startPage(pn,ps);
        return userMapper.getTwoHand("changewu");
    }

    @ResponseBody
    @RequestMapping("/loveProduct")
    public List<XuanShang> get05(HttpSession session) {
//        PageHelper.startPage(pn,ps);
        return userMapper.getLoveProduct((String) session.getAttribute("username"));
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
            return "index";
        }
        else {
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
        Map<String, String> dataMap;
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
            /*
            * 这里是将加入的图片保存到了项目中，所以启动项目后加入的图片访问时会显示404
            * 只有重新编译后才能访问的到
            * 这种情况将图片加入到服务器上就行了，**访问图片的路径写图片在服务器中的路径**
            *120.26.56.127:8088/1.png（把图片放到tomcat的web-inf下）
            * 这就要求项目运行的机器是台服务器
            * */
            String realPath = "D:\\Idea_mingbaile\\liu_project";
            // 5.保存图片
            desFilePath = realPath + "\\src\\main\\resources\\static\\personImages\\" + newName;
            File desFile = new File(desFilePath);
            //MultipartFile里面封装好的io操作
            file.transferTo(desFile);
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
    @RequestMapping(value="saveGoods")
    private String saveImgInfo(@RequestParam("smallTit") String smallTit, @RequestParam("modules") String modules, @RequestParam("imgUrls") String imgUrls, @RequestParam("user") String user, @RequestParam("number") int number, @RequestParam("date") String time) {
        String sa = smallTit.replace("<","&lt");
        try {
            switch (modules) {
                case "悬赏任务":
                    if (imgUrls == null || imgUrls == "" || imgUrls == " ") {
                        imgUrls = "wwwwww";
                        userMapper.insertOne01("rewardtask",user, sa, number, imgUrls, modules, time);
                        return "person";
                    } else {
                        userMapper.insertOne01("rewardtask",user, sa, number, imgUrls, modules, time);
                        return "person";
                    }

                case "二手货物出售":
                    if (imgUrls == null || imgUrls == "" || imgUrls == " ") {
                        imgUrls = "wwwwwwww";
                        userMapper.insertOne01("twohand",user, sa, number, imgUrls, modules, time);
                        return "person";
                    } else {
                        userMapper.insertOne01("twohand",user, sa, number, imgUrls, modules, time);
                        return "person";
                    }
                case "物品交换":
                    if (imgUrls == null || imgUrls == "" || imgUrls == " ") {
                        imgUrls = "wwwwwwwwww";
                        userMapper.insertOne01("changewu",user, sa, number, imgUrls, modules, time);
                        return "person";
                    } else {
                        userMapper.insertOne01("changewu",user, sa, number, imgUrls, modules, time);
                        return "person";
                    }
                case "人力资源":
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
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
    public String userName(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return "index";
        }
        return "/person/pullProduct";
    }
    //跳转悬赏任务
    @RequestMapping("美丽动人")
    public String goRewardTask (HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        else {
            return "moudel_OneByOne/RewardTask";
        }
    }
    //跳转二手商品出售
    @RequestMapping("行尸走肉")
    public String goTwoHand (HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        else {
            return "moudel_OneByOne/twoHand";
        }
    }
    //跳转物品交换
    @RequestMapping("丧钟为谁而鸣")
    public String goChangewu (HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        else {
            return "moudel_OneByOne/changeWu";
        }
    }

    @RequestMapping("lp")
    public String goLoveProduct (HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        else {
            return "/person/loveProduct";
        }
    }
    /*
    * 此处是购买处
    * */
    @ResponseBody
    @RequestMapping("del")
    public void del(HttpSession session,@RequestParam("id") String id) {
        String[] ss = id.split(":");
        String name = (String) session.getAttribute("username");
        switch (ss[0]){
            case "二手货物":
                userMapper.gaoumai("twohand",name,id);
                break;
            case "悬赏任务":
                userMapper.gaoumai("rewardtask",name,id);
                break;
            case "物品交换":
                userMapper.gaoumai("changewu",name,id);
                break;
        }



    } /*
    * 此处是撤銷处
    * */
    @ResponseBody
    @RequestMapping("remove")
    public void remove(HttpSession session,@RequestParam("id") String id) {
        String[] ss = id.split(":");
        switch (ss[0]){
            case "二手货物":
                userMapper.gaoumai("twohand"," ",id);
                break;
            case "悬赏任务":
                userMapper.gaoumai("rewardtask"," ",id);
                break;
        }

    }

    /*
    * 搜索自己发布的商品
    * */
    @ResponseBody
    @RequestMapping("myProduct")
    public List<XuanShang> myProduct (HttpSession session) {

        return userMapper.getMyProduct((String) session.getAttribute("username"));
    }
    /*
    * 转页面
    * */
    @RequestMapping("mp")
    public String mp () {
        return "person/myProduct";
    }

}
