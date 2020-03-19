package qr;

import java.util.Random;
import java.util.UUID;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
/*
* 毕设中的接收任务  发布任务  需要输入交易密码
* user一直在网址中保存，输入交易密码后  去数据库一一对应
*
*
* 若按登录步骤走 则username一直跟随网址
* 若企图跳着走且手动填写跟着的username也要保证交易密码正确（有交易密码则说明确实有这个人）
*
* 若既想跳着走还不手动写username则错误
* */
public class Demo {
    static UUID uuid = new UUID(1,10);
    static String message;
    public static void f() {
            uuid = UUID.randomUUID();
            message = uuid.toString();

    }

    public static void main(String[] args) {
        f();
        System.out.println(uuid);
        System.out.println(message);
    }
}
