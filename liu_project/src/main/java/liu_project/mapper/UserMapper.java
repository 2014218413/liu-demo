package liu_project.mapper;

import liu_project.tables.Gongneng;
import liu_project.tables.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
@Service
@Mapper
public interface UserMapper {
    /*
    * 注册
    * */
    @Insert("insert into user_ values(#{id},#{username},#{password},#{email},#{phone},1,#{other},sysdate,#{tidai},#{grade},#{banji})")
    void insertUser(int id, String username, String password, String email, String phone,String other,String tidai,String grade,String banji);
    /*
    * 验证账号密码是否正确
    * */
    @Select("select state from user_ where username=#{username} and password=#{password}")
    int selectUser(String username,String password);
    @Select("select * from user_")
    List<User> findAll();
    /*
    * 所有功能
    * */
    @Select("select * from Gongneng")
    List<Gongneng> findGongneng();
    /*
    * 往各个不同的表中插入数据
    * */
    @Insert("insert into rewardtask values(null,#{promulgator},#{message},1,#{price},#{imageurl},#{title},to_timestamp(#{ToTime},'YYYY-MM-DD HH24:MI:SS'))")
    void insertOne01(String promulgator,String message,int price,String imageurl,String title,String ToTime);
}
