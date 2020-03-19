package liu_project.mapper;

import liu_project.tables.newShop.Address;
import liu_project.tables.newShop.new_User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
}
