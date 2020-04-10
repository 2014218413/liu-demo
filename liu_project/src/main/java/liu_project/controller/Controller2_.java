package liu_project.controller;

import liu_project.mapper.AdminMapper;
import liu_project.mapper.UserMapper;
import liu_project.tables.PersonLi;
import liu_project.tables.admin.*;
import liu_project.tables.gongNeng_One.Message_;
import liu_project.tables.gongNeng_One.YiMai;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
@Controller
public class Controller2_ {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;
    @RequestMapping("/另起炉灶")
    public String goPersonLi(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        return "/moudel_TwoByTwo/personLi";
    }

    @RequestMapping("/bp")
    public String goYiMai(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        return "/person/yiMaiPriduct";
    }
    @RequestMapping("/st")
    public String pullPersonLi(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        return "/person/pullPersonLi";
    }
    @RequestMapping("/savePersonLi")
    public String save(HttpSession session, @RequestParam("grade") String grade, @RequestParam("class_")String class_, @RequestParam("name_")String name_, @RequestParam("phone_")String phone_, @RequestParam("weixin")String wexin, @RequestParam("gangwei")String gangwei, @RequestParam("danjia")int danjia, @RequestParam("ganshijian")int ganshijian,@RequestParam("didian") String didian, @RequestParam("churiqi")String churiqi, @RequestParam("renshu")int renshu, @RequestParam("sex")String yaoqiu,@RequestParam("jiezhishijian") String jiezhishijian) {
        String user_ = (String) session.getAttribute("username");
        userMapper.pullPersonLi( user_, grade, class_, name_, phone_, wexin, gangwei, danjia, ganshijian, didian, churiqi, renshu, yaoqiu,jiezhishijian);
        return "person";
    }
    @RequestMapping("/getPersonLi")
    @ResponseBody
    public List<PersonLi> liPersonLi() {
        return userMapper.getPersonLi();
    }
    //返回用户名
    @RequestMapping("/getU")
    @ResponseBody
    public String getUser_(HttpSession session) {
        return (String) session.getAttribute("username");
    }
    //联系卖家
    @RequestMapping("/getMessage")
    @ResponseBody
    public List<Message_> getMessage_(@RequestParam("message") String message) {
        return userMapper.mess(message);
    }
    //查看自己卖出的
    @RequestMapping("/getYiMai")
    @ResponseBody
    public List<YiMai> getYiMai_(HttpSession session) {
        return userMapper.getYiMai((String) session.getAttribute("username"));
    }
    //修改人力资源
    @RequestMapping("/upl")
    @ResponseBody
    public void upPersonLi(HttpSession session,@RequestParam("me") String me,@RequestParam("shu") int shu) {
        String user_ = (String) session.getAttribute("username");
        userMapper.updatePerLi(shu,1,me,user_);
    }

    //撤销人力资源
    @RequestMapping("/upl2")
    @ResponseBody
    public void upPersonLi2(HttpSession session,@RequestParam("me") String me) {
        String user_ = (String) session.getAttribute("username");
        userMapper.updatePerLi(0,0,me,user_);
    }

    //撤销人力资源
    @RequestMapping("/自食其力")
    public String maiMai(HttpSession session) {
       if (session.getAttribute("username")==null) {
           return "index";
       }
       else {
           return "/new/index";
       }
    }

    //管理员页面获取用户
    @RequestMapping("/setU")
    @ResponseBody
    public List<User> seU () {
        return adminMapper.seu();
    }

    //管理员注销用户
    @RequestMapping("/deU")
    @ResponseBody
    public String deU (HttpSession httpSession,@RequestParam("id") String id) {
        int a = Integer.parseInt(id);
        adminMapper.upu((String) httpSession.getAttribute("username"),a);
        System.out.println("------------------------------------");
        return "1";
    }

    //管理员恢复用户
    @RequestMapping("/huiU")
    @ResponseBody
    public String huiU (HttpSession httpSession,@RequestParam("id") String id) {
        int a = Integer.parseInt(id);
        adminMapper.upu2((String) httpSession.getAttribute("username"),a);
        System.out.println("*********************************");
        return "1";
    }

    //金额充值
    @RequestMapping("/chongzhi")
    @ResponseBody
    public String chongzhi (@RequestParam("user") String user,@RequestParam("message") String message) {
        double jjj = Double.parseDouble(message);
        try {
            adminMapper.chongzhi(user,jjj);
            adminMapper.chongzhi2(user,jjj);
            return "1";
        }
        catch (Exception e) {
            return "0";
        }

    }

    //插入举报内容
    @RequestMapping("/jubao")
    @ResponseBody
    public String jubao (HttpSession httpSession,@RequestParam("user") String user,@RequestParam("message") String message) {
        try {
            adminMapper.jubao((String) httpSession.getAttribute("username"),user,message);
            return "1";
        }
        catch (Exception e) {
            return "0";
        }
    }

    //管理员查询举报内容
    @RequestMapping("/seJ")
    @ResponseBody
    public List<JuBao> seJ () {
        return adminMapper.seJ();
    }

    //管理员查询充值记录
    @RequestMapping("/seC")
    @ResponseBody
    public List<ChongZhiJiLu> seC () {
        return adminMapper.seC();
    }

    //管理员查询订单记录
    @RequestMapping("/seDD")
    @ResponseBody
    public List<DingDan> seDD () {
        return adminMapper.seDD();
    }
    //管理员作废订单记录
    @RequestMapping("/upDD")
    @ResponseBody
    public String upDD (@RequestParam("id") int id) {
        try {
            adminMapper.upDD(id);
            return "1";
        }
        catch (Exception e) {
            return "0";
        }

    }
    //管理员恢复订单记录
    @RequestMapping("/upDDDD")
    @ResponseBody
    public String upDDDD (@RequestParam("id") int id) {
        try {
            adminMapper.upDDDD(id);
            return "1";
        }
        catch (Exception e) {
            return "0";
        }

    }

    //管理员登录
    @RequestMapping("/guanliyuan")
    @ResponseBody
    void guanliyuan (@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse httpServletResponse,HttpSession httpSession) throws IOException {
        int a = adminMapper.seAdmin(username,password);
        if (a<1) {
            httpSession.setAttribute("admin","123");
            httpServletResponse.sendRedirect("/");

        }
        else {
            httpSession.setAttribute("admin",username);
            httpServletResponse.sendRedirect("/new/admin/index.html");
        }
    }

    //管理员中的那个账单明细里面的充值记录
    @RequestMapping("/scb")
    @ResponseBody
    List<ChongzhiJilu_Biao> scb (HttpSession httpSession) {
        return adminMapper.scb((String) httpSession.getAttribute("username"));
    }

    //管理员中的那个账单明细里面的充值记录
    @RequestMapping("/sxb")
    @ResponseBody
    List<Xiaofei_Biao> sxb (HttpSession httpSession) {
        return adminMapper.sxb(((String) httpSession.getAttribute("username")));
    }

    //管理员注册
    @RequestMapping("/guanz")
    @ResponseBody
    public int guanz (@RequestParam("value") String value) {
        return adminMapper.guanz(value);
    }

    //管理员注册真正的
    @ResponseBody
    @RequestMapping("/guanzz")

    public void guanzz (@RequestParam("username") String username,@RequestParam("password") String password,HttpServletResponse httpServletResponse) throws IOException {

            adminMapper.insertguan(username,password);
           httpServletResponse.sendRedirect("/");

    }

}
