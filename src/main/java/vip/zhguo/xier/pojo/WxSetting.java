package vip.zhguo.xier.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/23 10:54]
 */
@Component
@Data
@ToString
public class WxSetting {
    @Value("${wx.appid}")
    private String appId;
    @Value("${wx.appsecret}")
    private String appSecret;
    @Value("${wx.touser}")
    private String toUser;
    @Value("${wx.template}")
    private String template;

}
