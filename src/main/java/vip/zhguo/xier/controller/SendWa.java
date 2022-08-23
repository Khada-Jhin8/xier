package vip.zhguo.xier.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.Util;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WxSetting;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/23 10:37]
 */
@RestController
@Slf4j
public class SendWa {
    @Autowired
    TempValue tempValue;
    @Autowired
    WxSetting wxSetting;


    @GetMapping("/wa")
    public String goodNight(String msg) throws Exception {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis());
        //顶层json
        Map map = new HashMap<String, String>();
        //dataJson
        Map dataMap = new HashMap<String, String>();
        //value/colorJson
        Map contentVC = new HashMap<String, String>();
        List list = new ArrayList<String>();
//        String msg = "穿过挪威的森林\n" +
//                "让我走进你的梦里\n" +
//                "夕阳落在我的铠甲\n" +
//                "王子不一定骑着白马\n" +
//                "黑马王子四海为家\n" +
//                "现在是晚上" + time + "\n" +
//                "我是郑经人，美梦总相随。"+"\n" +
//                "叮~晚安啦";
        // 请求Accesstoken
        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxSetting.getAppId() + "&secret=" + wxSetting.getAppSecret();
        String resData = Util.doGet(getAccessTokenUrl);
        log.info(resData);
        Map resDataMap = (Map) JSONObject.parseObject(resData);
        String access_token = resDataMap.get("access_token").toString();
        log.info(access_token);
        String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        //封装变量数据
        contentVC.put("value",msg);
        contentVC.put("color","#CCFFFF");
        //封装外层格式
        map.put("touser",wxSetting.getToUser());
        map.put("template_id",wxSetting.getTemplate());
        map.put("topcolor","#33FF33");
        dataMap.put("content",contentVC);
        map.put("data",dataMap);
        String senData = JSONObject.toJSON(map).toString();
        log.info(senData);
        String result = Util.doPost(sendTemplateMsgUrl, senData);
        return result;
    }
    @GetMapping("/wa-t")
    public String goodNight_t(String msg) throws Exception {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis());
        //顶层json
        Map map = new HashMap<String, String>();
        //dataJson
        Map dataMap = new HashMap<String, String>();
        //value/colorJson
        Map contentVC = new HashMap<String, String>();
        List list = new ArrayList<String>();
        // 请求Accesstoken
        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxSetting.getAppId() + "&secret=" + wxSetting.getAppSecret();
        String resData = Util.doGet(getAccessTokenUrl);
        log.info(resData);
        Map resDataMap = (Map) JSONObject.parseObject(resData);
        String access_token = resDataMap.get("access_token").toString();
        log.info(access_token);
        String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        //封装变量数据
        contentVC.put("value",time);
        contentVC.put("color","#0099FF");
        //封装外层格式
        map.put("touser",wxSetting.getToUser());
        map.put("template_id",wxSetting.getTemplate());
        map.put("topcolor","#33FF33");
        dataMap.put("time",contentVC);
        map.put("data",dataMap);
        String senData = JSONObject.toJSON(map).toString();
        log.info(senData);
        String result = Util.doPost(sendTemplateMsgUrl, senData);
        return result;
    }
}
