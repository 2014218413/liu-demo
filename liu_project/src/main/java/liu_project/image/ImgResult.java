package liu_project.image;

import java.util.Map;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
public class ImgResult {
    private String msg;
    private int code;

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    private Map data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
