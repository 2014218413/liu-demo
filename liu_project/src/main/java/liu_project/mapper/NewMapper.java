package liu_project.mapper;

import liu_project.tables.newShop.Address;
import liu_project.tables.newShop.DingDanJilu;
import liu_project.tables.newShop.Food_Name;
import liu_project.tables.newShop.new_User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
@Service
@Mapper
public interface NewMapper {
    @Select("select username,email,phone,banji from user_ where username=#{name}")
    List<new_User> uu(String name);

    //修改密码
    @Update("update user_ set password=#{pass} where username=#{name}")
    void up(String pass,String name);

    //修改手机号
    @Update("update user_ set phone=#{phone} where username=#{name}")
    void uph(String phone,String name);

    //修改邮箱
    @Update("update user_ set email=#{email} where username=#{name}")
    void ue(String email,String name);

    //根据手机号找回密码
    @Select("select distinct password from user_ where username=#{name} and phone=#{phone}")
    String getP(String name,String phone);

    //获取收货地址等信息
    @Select("select name,phone,address_,first from user_address where username=#{name} and state_='1'")
    List<Address> getAddress(String name);

    //插入地址信息
    @Insert("insert into user_address values (#{username},#{name},#{phone},#{address_},'1','0')")
    void inAddress(String username,String name,String phone,String address_);

    //获取食品1名字
    @Select("select name_,price,total,image_ from ${name} where state='1'")
    List<Food_Name> getfn1(@Param("name") String name);

    //注册完成后创建一个账户信息存入 余额
    @Insert("insert into user_money values(#{name},#{money})")
    void user_yue (String name,double money);

    //订单记录
    @Insert("insert into user_money_jilu values (#{name},#{message},#{jine},sysdate,#{dizhi},'1',null)")
    void user_jilu (String name,String message,double jine,String dizhi);

    //从余额扣除金额
    @Update("update user_money set yue=yue-#{yue},shijian=sysdate where user_=#{name}")
    void uyu(String name,double yue);

    //注册成功 的同时设置好余额 0
    @Insert("insert into user_money values (#{name},#{money},sysdate)")
    void insertM(String name,double money);

    //查询余额
    @Select("select distinct yue from user_money where user_=#{name}")
    double seM(String name);

    //获取订单信息
    @Select("select message,jine,shi,dizhi,id from user_money_jilu where user_=#{name} and state='1'")
    List<DingDanJilu> getD(String name);

    //删除订单
    @Update("update user_money_jilu set state='0' where user_=#{name} and id=#{id}")
    void dD (String name,int id);
}
