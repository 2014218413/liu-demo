package liu_project.mapper;

import liu_project.tables.admin.*;
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

    //充值金额(插入的是记录的表)
    @Insert("insert into chongzhijilu values (#{name},#{jine},sysdate)")
    void chongzhi (String name,double jine);
    //充值(增加到余额)
    @Update("update user_money set yue=yue+#{jine},shijian=sysdate where user_=#{name}")
    void chongzhi2 (String name,double jine);

    //插入举报内容
    @Insert("insert into jubao values (#{name},#{user},#{message})")
    void jubao (String name,String user,String message);

    //管理员查询举报信息
    @Select("select * from jubao")
    List<JuBao> seJ ();

    //查询充值记录
    @Select("select * from chongzhijilu")
    List<ChongZhiJiLu> seC ();

    //查询订单记录
    @Select("select * from user_money_jilu")
    List<DingDan> seDD();

    //作废订单
    @Update("update user_money_jilu set state='0' where id=#{id}")
    void upDD (int id);

    //恢复订单
    @Update("update user_money_jilu set state='1' where id=#{id}")
    void upDDDD (int id);

    //管理员登录
    @Select("select count(*) from admin_ where username=#{username} and password=#{password}")
    int seAdmin (String username,String password);

    //账单中的那个充值记录
    @Select("select jine,shijian from chongzhijilu where user_=#{name}")
    List<ChongzhiJilu_Biao> scb (String name);

    //账单中的那个消费记录
    @Select("select message,jine,shi from user_money_jilu where user_=#{name}")
    List<Xiaofei_Biao> sxb (String name);

    //管理员注册查看账号是否重复
    @Select("select count(*) from admin_ where username=#{name}")
    int guanz (String name);

    //管理员插入
    @Insert("insert into admin_ values (#{username},#{password})")
    void insertguan (String username,String password);
}
