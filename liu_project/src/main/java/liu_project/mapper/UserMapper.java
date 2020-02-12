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

    @Insert("insert into user_ values(#{id},#{username},#{password},#{email},#{phone},1,#{other},sysdate,#{tidai})")
    void insertUser(int id, String username, String password, String email, String phone,String other,String tidai);
    @Select("select tidaiusername from user_ where username=#{username} and password=#{password}")
    String selectUser(String username,String password);
    @Select("select * from user_")
    List<User> findAll();
    @Select("select * from Gongneng")
    List<Gongneng> findGongneng();
}
