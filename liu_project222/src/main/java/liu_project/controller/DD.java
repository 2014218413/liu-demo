package liu_project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
public class DD {

    public static void main(String[] args) {
        Map<String,String> s = new HashMap();
        s.put("sss","dddddd");
        System.out.println(s);
        int b = 0;
        String[] bs = new String[5];
        bs[0] = "sss";
        System.out.println(bs);
        String[] a = {"dsd","sdsd"};
        List<String> list = new ArrayList<>();
        list.add("sssss");
        list.add("sasas");
        list.add(0,"op");
        System.out.println(list);
        System.out.println(list.toString());
//        list.remove(1);
        list.remove("sssss");
        System.out.println(list.toString());
        while (10>b) {
            System.out.println("-----------------------");
            b+=1;
        }
    }
}
