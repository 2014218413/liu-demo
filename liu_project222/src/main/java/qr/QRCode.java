package qr;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大砍刀
 * @date
 **/
public class QRCode {
    @Test
        //生成一个二维码
    public void createCode() throws WriterException, IOException {
        //将一个json格式的字符串，使用fastJson
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("姓名","刘圣恩");
        jsonObject.put("年龄",22);
        jsonObject.put("班级","信息五班");
        String message = jsonObject.toString();
        //创建一个矩阵对象   (进入encode可以查看都需要传入什么格式的参数)
        Map<EncodeHintType,Object> map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE,200,200,map);
        //Path
        Path path = FileSystems.getDefault().getPath("D://","first.jpg");
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
        System.out.println("生成二维码完毕");
    }
}
