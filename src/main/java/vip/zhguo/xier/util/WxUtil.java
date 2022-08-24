package vip.zhguo.xier.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import vip.zhguo.xier.pojo.WxSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [关于微信相关操作]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/24 11:19]
 */
@Slf4j

public class WxUtil {
    public static String getAccessToken(String appid,String appsecret) throws Exception {
        StringBuilder AccessTokenUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        AccessTokenUrl.append(appid);
        AccessTokenUrl.append("&secret="+appsecret);
        log.info("AccessTokenUrl============"+AccessTokenUrl.toString());
        String resData = NetUtil.doGet(AccessTokenUrl.toString());
        log.info("resData=========="+resData);
        Map resDataMap = (Map) JSONObject.parseObject(resData);
        String access_token = resDataMap.get("access_token").toString();
        log.info("access_token==========="+access_token);
        log.info(access_token.toString());
        return access_token;
    }
    public static String sendMsg(String accessToken, String msg, WxSetting wxSetting) throws Exception {
        //顶层json
        Map map = new HashMap<String, String>();
        //dataJson
        Map dataMap = new HashMap<String, String>();
        //value/colorJson
        Map contentVC = new HashMap<String, String>();
        List list = new ArrayList<String>();
        StringBuilder sendMsgUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=");
        sendMsgUrl.append(accessToken);
        contentVC.put("value", msg);
        contentVC.put("color", "#CCFFFF");
        //封装外层格式
        map.put("touser", wxSetting.getToUser());
        map.put("template_id", wxSetting.getTemplate());
        map.put("topcolor", "#33FF33");
        dataMap.put("content", contentVC);
        map.put("data", dataMap);
        String senData = JSONObject.toJSON(map).toString();
        log.info(senData);
        String result = NetUtil.doPost(sendMsgUrl.toString(), senData);
        return result;
    }

}
