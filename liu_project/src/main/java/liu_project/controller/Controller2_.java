package liu_project.controller;

import liu_project.mapper.UserMapper;
import liu_project.tables.PersonLi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
@Controller
public class Controller2_ {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/另起炉灶")
    public String goPersonLi(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "index";
        }
        return "/moudel_TwoByTwo/personLi";
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
}
