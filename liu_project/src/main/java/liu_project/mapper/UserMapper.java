package liu_project.mapper;

import liu_project.tables.Gongneng;
import liu_project.tables.PersonLi;
import liu_project.tables.User;
import liu_project.tables.gongNeng_One.Message_;
import liu_project.tables.gongNeng_One.XuanShang;
import liu_project.tables.gongNeng_One.YiMai;
import org.apache.ibatis.annotations.*;
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
    @Select("select distinct state from user_ where username=#{username} and password=#{password}")
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
    //各种任务发布
    @Insert("insert into ${target} values(null,#{promulgator},#{message},1,#{price},#{imageurl},#{title},to_timestamp(#{ToTime},'YYYY-MM-DD HH24:MI:SS'),null,null)")
    void insertOne01(String target,String promulgator,String message,int price,String imageurl,String title,String ToTime);

    //悬赏任务获取
    @Select("select id,promulgator,message,price,imageurl,totime,goumaishijian from rewardtask where state=1")
    List<XuanShang> getXuan();
    //二手货物出售获取  在这里表设置为了变量，就不用在写好多查询select的语句了

    /*注意这里用的name 是以 # 的方式引用的 所以要使用@param
    * 为什么呢： 因为如果使用的是 $ 它会自动将其要引入的那个变为字符串类型 “ ”（防注入）
    *    但是 # 不会对要引入的那个变量进行修改，直接将其当做一个对象来处理
    *    所以要使用它‘找它，就要给它打上标记指定好找具体的哪个
    * */
    @Select("select id,promulgator,message,price,imageurl,totime,goumaishijian from ${name_} where state=1")
    List<XuanShang> getTwoHand(@Param("name_") String name_);

    //购买
    @Update("update ${who} set state = 0,goumaizhe=#{name},goumaishijian=sysdate where id=#{id}")
    void gaoumai(String who,String name,String id);

    //获取自己发布的商品
    @Select("select id,promulgator,message,price,imageurl,totime,goumaishijian from changewu where promulgator=#{name} and state=1 union select id,promulgator,message,price,imageurl,totime,goumaishijian from REWARDTASK where promulgator=#{name} and state=1 union select id,promulgator,message,price,imageurl,totime,goumaishijian from twohand where promulgator=#{name} and state=1")
    List<XuanShang> getMyProduct(String name);

    //获取自己购买的商品
    @Select("select id,promulgator,message,price,imageurl,totime,goumaishijian from changewu where goumaizhe=#{name} and state=0 union select id,promulgator,message,price,imageurl,totime,goumaishijian from REWARDTASK where goumaizhe=#{name} and state=0 union select id,promulgator,message,price,imageurl,totime,goumaishijian from twohand where goumaizhe=#{name} and state=0")
    List<XuanShang> getLoveProduct(String name);

    //获取自己卖出的商品
    @Select("select id,message,price,title_,goumaishijian,goumaizhe from changewu where goumaizhe !=' ' and state=0 and promulgator=#{name} union select id,message,price,title_,goumaishijian,goumaizhe from REWARDTASK where goumaizhe !=' ' and state=0 and promulgator=#{name} union select id,message,price,title_,goumaishijian,goumaizhe from twohand where goumaizhe != ' ' and state=0 and promulgator=#{name}")
    List<YiMai> getYiMai(String name);

    //插入发布的人力资源信息
    @Insert("insert into personli values (#{user_},#{grade},#{class_},#{name_},#{phone_},#{weixin},#{gangwei},#{danjia},#{ganshijian},#{didian},to_timestamp(#{churiqi},'YYYY-MM-DD HH24:MI:SS'),#{renshu},#{yaoqiu},1,null,to_timestamp(#{jiezhishijian},'YYYY-MM-DD HH24:MI:SS'))")
    void pullPersonLi(String user_,String grade,String class_,String name_,String phone_,String weixin,String gangwei,int danjia,int ganshijian,String didian,String churiqi,int renshu,String yaoqiu,String jiezhishijian);

    @Select("select * from personli where state_=1")
    List<PersonLi> getPersonLi();

    @Select("select distinct email,phone from user_ where username=#{message}")
    List<Message_> mess(String message);

    //修改发布的人力资源
    @Update("update personli set renshu=${shu},state_=${sta} where id_=#{id} and user_=#{user_}")
    void updatePerLi(int shu,int sta,String id,String user_);
}
