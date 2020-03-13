package liu_project.interceptor;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 忌冷忌热的怕热不怕冷的吃不胖少年
 * 回首向来萧瑟处，也无风雨也无晴。
 **/
/*
* 目前没有使用这个功能
* */
public class HandlerInterCeptor_ implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(HandlerInterCeptor_.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url =request.getServletPath();
        if (url.indexOf("sssssss") > -1) {
            log.warn("拦截--------------------------");
            return false;
        }
        else {
            log.warn("未拦截*****************************");
            return true;
        }
    }
}
