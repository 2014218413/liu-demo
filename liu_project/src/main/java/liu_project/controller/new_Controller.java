package liu_project.controller;

import liu_project.mapper.NewMapper;
import liu_project.tables.newShop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    public String up(HttpSession session, @RequestParam("pass") String passs, @RequestParam("passNew") String passNew) {
        String name = (String) session.getAttribute("username");
        String pass = (String) session.getAttribute("password");
        if (pass.equals(passs)) {
            newMapper.up(passNew, name);
            return "1";
        } else {
            return "0";
        }

    }

    //修改手机号
    @RequestMapping("/uph")
    @ResponseBody
    public String uph(HttpSession session, @RequestParam("pass") String pass, @RequestParam("phoneNew") String phoneNew) {
        String name = (String) session.getAttribute("username");
        String passs = (String) session.getAttribute("password");
        if (pass.equals(passs)) {
            newMapper.uph(phoneNew, name);
            return "1";
        } else {
            return "0";
        }

    }

    //修改邮箱
    @RequestMapping("/ue")
    @ResponseBody
    public String ue(HttpSession session, @RequestParam("pass") String pass, @RequestParam("emailNew") String emailNew) {
        String name = (String) session.getAttribute("username");
        String passs = (String) session.getAttribute("password");
        if (pass.equals(passs)) {
            newMapper.ue(emailNew, name);
            return "1";
        } else {
            return "0";
        }

    }

    //获取用户名
    @RequestMapping("/getUU")
    @ResponseBody
    public String getUn(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    //根据手机号找回密码
    @RequestMapping("/getP")
    @ResponseBody
    public String getP(HttpSession session, @RequestParam("phone") String phone) {
        try {
            String name = (String) session.getAttribute("username");
            return newMapper.getP(name, phone);
        } catch (Exception e) {
            return "0";
        }

    }

    //新增收货地址
    @RequestMapping("/addAddress")
    @ResponseBody
    public void getAddress(HttpSession session, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address_") String address_, HttpServletResponse httpServletResponse) throws IOException {
        String username = (String) session.getAttribute("username");
        newMapper.inAddress(username + "", name + "", phone + "", address_ + "");
        httpServletResponse.sendRedirect("/new/person/address.html");
    }

    //获取收货地址
    @RequestMapping("/getAddress")
    @ResponseBody
    public List<Address> getAdd(HttpSession session) {
        String name = (String) session.getAttribute("username");
        return newMapper.getAddress(name);
    }

    //获取食品名字
    @RequestMapping("/getfn")
    @ResponseBody
    public List<Food_Name> getfn(@RequestParam("name") String name) {
        return newMapper.getfn1(name);
    }

    //加入购物车
    @RequestMapping("/addCar")
    @ResponseBody
    public String addCar (HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, @RequestParam("huopinlei") String huopinlei,@RequestParam("huopinming") String huopinming,@RequestParam("jiage") String jiage,@RequestParam("jiage") String zongjia,@RequestParam("cunhuo") String cunhuo) throws UnsupportedEncodingException {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("Car")) {
                String cookieValue = cookie.getValue();
                String cv = URLDecoder.decode(cookieValue,"UTF-8");
                String message = huopinlei+","+huopinming+","+jiage+","+"1"+","+zongjia+","+cunhuo+"--";
                String mm = cv+message;
                String mm2 = URLEncoder.encode(mm,"UTF-8");
                Cookie cookieOk = new Cookie("Car",mm2);
                cookieOk.setMaxAge(60*60*24*30);
                httpServletResponse.addCookie(cookieOk);
                return "1";
            }
        }
        return "0";

    }
    //获取购物车
    @RequestMapping("/getCar")
    @ResponseBody
    public List<Car_> getCar (HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        Cookie[] cookies = httpServletRequest.getCookies();
        List<Car_> list = new ArrayList<>();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Car")) {
                String value = URLDecoder.decode(cookie.getValue(),"UTF-8");
                String[] jige = value.split("--");

                for (String string : jige) {
                    String[] lei = string.split(",");
                    if (lei.length==6) {
                        Car_ car_ = new Car_();
                        car_.setHuopinlei(lei[0]);
                        car_.setHuopinming(lei[1]);
                        car_.setJiage(lei[2]);
                        car_.setShumu(lei[3]);
                        car_.setZongjia(lei[4]);
                        car_.setCunhuo(lei[5]);
                        list.add(car_);
                    }

                }
                return list;
            }
        }
        return list;
    }
    //删除购物车中的内容
    @RequestMapping("/deleteCar")
    @ResponseBody
    public String deleteCar (HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestParam("name") String name) throws UnsupportedEncodingException {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Car")) {
                String value = URLDecoder.decode(cookie.getValue(),"UTF-8");
                String vl = value.replace(name,",");
                System.out.println(name);
                System.out.println(vl);
                String vo = URLEncoder.encode(vl,"UTF-8");
                Cookie cookie1 = new Cookie("Car",vo);
                httpServletResponse.addCookie(cookie1);
                return "1";

            }
        }
        return "0";
    }
    //置空Cookie
    @RequestMapping("/zhikong")
    @ResponseBody
    public String setCookieNull (HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Car")) {
                Cookie cookie1 = new Cookie("Car","");
                httpServletResponse.addCookie(cookie1);
                return "1";
            }
        }
        return "0";
    }

    //检查Cookie用的
    @RequestMapping("/jiancha")
    @ResponseBody
    public void jiancha (HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Car")) {
                System.out.println(URLDecoder.decode(cookie.getValue(),"UTF-8"));
            }
        }
    }

    //将订单信息加入session（这里会在实际结算的时候置空，这里只是起到一个记录的作用）
    @ResponseBody
    @RequestMapping("/Pmoney")
    public void pm (HttpServletResponse httpServletResponse,HttpSession httpSession,@RequestParam("message") String message,@RequestParam("zhifu") double zhifu) throws IOException {
       httpSession.setAttribute("message",message);
       httpSession.setAttribute("money",zhifu);
        System.out.println("加入session成功");
    }

    //提交订单  用于支付成功页面获取信息
    @RequestMapping("/getM")
    @ResponseBody
    public MoneySuccess jine (HttpSession httpSession) {
        double zhifu = (double) httpSession.getAttribute("money");
        String shouhuoren = (String) httpSession.getAttribute("shouhuoren");
        String lianxidianhua = (String) httpSession.getAttribute("lianxidianhua");
        String shouhuodizhi = (String) httpSession.getAttribute("shouhuodizhi");
        MoneySuccess moneySuccess = new MoneySuccess();
        moneySuccess.setZhifu(zhifu);
        moneySuccess.setShouhuoren(shouhuoren);
        moneySuccess.setShouhuodizhi(shouhuodizhi);
        moneySuccess.setLianxidianhua(lianxidianhua);
        return moneySuccess;
    }

    //这里是显示在pay.html   用于显示将要支付的金额
    @RequestMapping("/gem")
    @ResponseBody
    public double jinee (HttpSession httpSession) {
        double zhifu = (double) httpSession.getAttribute("money");
        return zhifu;
    }

    //真正的提交订单
    @RequestMapping("/addMoney")
    @ResponseBody
    public String addMoney (@RequestParam("phone") String phone,@RequestParam("name") String name,@RequestParam("address") String address, HttpSession httpSession,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        String dizhi = "收货人:"+name+",联系电话:"+phone+",收货地址:"+address;
        String user = (String) httpSession.getAttribute("username");
        double zhifu = (double) httpSession.getAttribute("money");
        newMapper.uyu(user,zhifu);
        String message = (String) httpSession.getAttribute("message");
        try {
            newMapper.user_jilu(user,message,zhifu,dizhi);
            httpSession.setAttribute("shouhuoren",name);
            httpSession.setAttribute("lianxidianhua",phone);
            httpSession.setAttribute("shouhuodizhi",address);

            return "1";
        }
        catch (Exception e) {
            return "0";
        }

    }

    //返回账户余额
    @RequestMapping("/getYu")
    @ResponseBody
    public double getYu (HttpSession httpSession) {
        return newMapper.seM((String) httpSession.getAttribute("username"));
    }

    //获取订单信息
    @RequestMapping("/getD")
    @ResponseBody
    public List<DingDanJilu> getD (HttpSession httpSession) {
        return newMapper.getD((String) httpSession.getAttribute("username"));
    }
    //客户删除订单
    @RequestMapping("/dddd")
    @ResponseBody
    public String dD (HttpSession httpSession,@RequestParam("id") String id) {
        int a = Integer.parseInt(id);
        newMapper.dD((String) httpSession.getAttribute("username"),a);
        return "1";
    }
}