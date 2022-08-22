package vip.zhguo.xier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XierApplicationTests {

    @Autowired
    TempValue tempValue;

    @Test
    void contextLoads() {
        System.out.println(tempValue);

    }

}
