package liu_project.tables.gongNeng_One;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
public class XuanShang {
    private String promulgator;
    private String message;
    private int price;
    private String imageurl;
    private String totime;
    private String id;

    public String getGoumaishijian() {
        return goumaishijian;
    }

    public void setGoumaishijian(String goumaishijian) {
        this.goumaishijian = goumaishijian;
    }

    private String goumaishijian;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(String promulgator) {
        this.promulgator = promulgator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }
}
