package vip.zhguo.xier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class XierApplication {

    public static void main(String[] args) {
        SpringApplication.run(XierApplication.class, args);
    }

}
