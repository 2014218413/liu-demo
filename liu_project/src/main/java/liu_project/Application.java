package liu_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 大砍刀
 * 回首向来萧瑟处，也无风雨也无晴。
 * @date
 **/
@SpringBootApplication
@ComponentScan(basePackages={"liu_project.*"})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
