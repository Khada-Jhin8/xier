package vip.zhguo.xier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("vip.zhguo.xier.mapper")
public class XierApplication {

    public static void main(String[] args) {
        SpringApplication.run(XierApplication.class, args);
    }

}
