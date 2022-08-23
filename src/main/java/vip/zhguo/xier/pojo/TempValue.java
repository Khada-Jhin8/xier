package vip.zhguo.xier.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
public class TempValue {
    @Value("${huoqutianqi}")
    private String huoqutianqi;
    @Value("${huoquchengshi}")
    private String huoquchengshi;
    @Value("${ServerJiang.url}")
    private String serverjiangurl;
    @Value("${ServerJiang.title}")
    private String serverjiangtitle;
    @Value("${ServerJiang.desp}")
    private String serverjiangdesp;
}
