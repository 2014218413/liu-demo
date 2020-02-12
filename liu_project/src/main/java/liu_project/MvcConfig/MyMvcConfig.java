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
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使用这个只能生效一个
//        registry.addViewController("").setViewName("");
    }

    //设置静态资源访问路径


    //这个可以使所有的都能生效
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/h").setViewName("hello");
                registry.addViewController("/d").setViewName("demo");
            }
            /*
            * 这里一般不拦截/**  这样就算访问static下的js css 也会判断的，浪费资源是处理缓慢
            * */
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                        .maxAge(3600 * 24);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterCeptor_()).addPathPatterns("/**");
            }
        };
        return adapter;
    }


}
