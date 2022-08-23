package vip.zhguo.xier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.zhguo.xier.pojo.TempValue;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class XierApplicationTests {

    @Autowired
    TempValue tempValue;

    @Test
    void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(System.currentTimeMillis());
        System.out.println(format);
    }

}
