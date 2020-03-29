package liu_project.MvcConfig;

import liu_project.interceptor.HandlerInterCeptor_;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 大砍刀
 * 回首向来萧瑟处，也无风雨也无晴。
 * @date
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //设置静态资源访问路径


    //这个可以使所有的都能生效
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/new/home/home.html").setViewName("new/home/home");
                registry.addViewController("/ssss").setViewName("new/home/ss");
                registry.addViewController("/new/home/success.html").setViewName("new/home/success");
                registry.addViewController("/new/home/pay.html").setViewName("new/home/pay");
                registry.addViewController("/new/home/shopcart.html").setViewName("new/home/shopcart");
                registry.addViewController("/new/person/password.html").setViewName("new/person/password.html");
                registry.addViewController("/new/person/index.html").setViewName("new/person/index");
                registry.addViewController("/new/person/information.html").setViewName("new/person/information");
                registry.addViewController("/new/person/safety.html").setViewName("new/person/safety.html");
                registry.addViewController("/new/person/setpay.html").setViewName("new/person/setpay.html");
                registry.addViewController("/new/person/bindphone.html").setViewName("new/person/bindphone.html");
                registry.addViewController("/new/person/email.html").setViewName("new/person/email.html");
                registry.addViewController("/new/person/address.html").setViewName("new/person/address.html");
                registry.addViewController("/new/person/order.html").setViewName("new/person/order.html");
            }

//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\personImages\\";
//                System.out.println(path);
//                registry.addResourceHandler("/static/personImages/**").addResourceLocations("file"+path);
//            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                        .maxAge(3600 * 24);
            }

//            @Override     设置拦截的
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new HandlerInterCeptor_()).addPathPatterns("/**");
//            }
        };
        return adapter;
    }


}
