package liu_project.controller;

import liu_project.mapper.NewMapper;
import liu_project.tables.newShop.Address;
import liu_project.tables.newShop.new_User;
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
public class new_Controller {
    @Autowired
    NewMapper newMapper;
    @RequestMapping("/inform")
    @ResponseBody
    public List<new_User> getU(HttpSession session) {
        String name = (String) session.getAttribute("username");
        return newMapper.uu(name);
    }

    //修改密码
    @RequestMapping("/upp")
    @ResponseBody
    public String up (HttpSession session, @RequestParam("pass") String passs,@RequestParam("passNew") String passNew) {
       String name = (String) session.getAttribute("username");
       String pass = (String) session.getAttribute("password");
       if (pass.equals(passs)) {
            newMapper.up(passNew,name);
            return "1";
       }
       else {
           return "0";
       }

    }

    //修改手机号
    @RequestMapping("/uph")
    @ResponseBody
    public String uph (HttpSession session, @RequestParam("pass") String pass,@RequestParam("phoneNew") String phoneNew) {
       String name = (String) session.getAttribute("username");
       String passs = (String) session.getAttribute("password");
       if (pass.equals(passs)) {
            newMapper.uph(phoneNew,name);
            return "1";
       }
       else {
           return "0";
       }

    }

    //修改邮箱
    @RequestMapping("/ue")
    @ResponseBody
    public String ue (HttpSession session, @RequestParam("pass") String pass,@RequestParam("emailNew") String emailNew) {
       String name = (String) session.getAttribute("username");
       String passs = (String) session.getAttribute("password");
       if (pass.equals(passs)) {
            newMapper.ue(emailNew,name);
            return "1";
       }
       else {
           return "0";
       }

    }

    //获取用户名
    @RequestMapping("/getUU")
    @ResponseBody
    public String getUn (HttpSession session) {
        return (String) session.getAttribute("username");
    }

    //根据手机号找回密码
    @RequestMapping("/getP")
    @ResponseBody
    public String getP(HttpSession session,@RequestParam("phone") String phone) {
        try {
            String name = (String) session.getAttribute("username");
            return newMapper.getP(name,phone);
        }
        catch (Exception e) {
            return "0";
        }

    }

    //新增收货地址
    @RequestMapping("/addAddress")
    @ResponseBody
    public void getAddress(HttpSession session, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address_") String address_, HttpServletResponse httpServletResponse) throws IOException {
        String username = (String) session.getAttribute("username");
        newMapper.inAddress(username+"",name+"",phone+"",address_+"");
        httpServletResponse.sendRedirect("/new/person/address.html");
    }

    //获取收货地址
    @RequestMapping("/getAddress")
    @ResponseBody
    public List<Address> getAdd (HttpSession session) {
        String name = (String) session.getAttribute("username");
        return newMapper.getAddress(name);
    }
}
