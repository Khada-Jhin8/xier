package vip.zhguo.xier.util;

import lombok.extern.slf4j.Slf4j;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/24 11:19]
 */
@Slf4j
public class WxUtil {
    private static StringBuilder AccessTokenUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
    public static String getAccessToken(String appid,String appsecret){
        AccessTokenUrl.append(appid);
        AccessTokenUrl.append("&secret="+appsecret);
        log.info(AccessTokenUrl.toString());

        return null;
    }

}
