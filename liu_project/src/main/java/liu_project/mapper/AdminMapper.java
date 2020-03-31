package liu_project.mapper;

import liu_project.tables.admin.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
@Mapper
@Component
public interface AdminMapper {

    //列出人员
    @Select("select id,username,password,state,shijian from user_ ")
    List<User> seu ();

    //注销人员
    @Update("update user_ set state=0 where id=#{id}")
    void upu (String name,int id);

    //恢复人员
    @Update("update user_ set state=1 where id=#{id}")
    void upu2 (String name,int id);

    //充值金额
    @Insert("insert into chongzhijilu values (#{name},#{jine},sysdate)")
    void chongzhi (String name,double jine);
}
