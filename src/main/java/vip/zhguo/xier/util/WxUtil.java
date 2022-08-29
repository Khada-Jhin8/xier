package vip.zhguo.xier.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import vip.zhguo.xier.pojo.WeatherDTO;
import vip.zhguo.xier.pojo.WxSetting;

import java.util.*;

/**
 * [关于微信相关操作]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/24 11:19]
 */
@Slf4j

public class WxUtil {
    public static String getAccessToken(String appid, String appsecret) throws Exception {
        StringBuilder AccessTokenUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        AccessTokenUrl.append(appid);
        AccessTokenUrl.append("&secret=" + appsecret);
        log.info("AccessTokenUrl============" + AccessTokenUrl.toString());
        String resData = NetUtil.doGet(AccessTokenUrl.toString());
        log.info("resData==========" + resData);
        Map resDataMap = (Map) JSONObject.parseObject(resData);
        String access_token = resDataMap.get("access_token").toString();
        log.info("access_token===========" + access_token);
        log.info(access_token.toString());
        return access_token;
    }

    public static String sendMsg(String accessToken, String msg, WxSetting wxSetting, WeatherDTO weatherDTO) throws Exception {
        //顶层json
        Map map = new HashMap<String, String>();
        //dataJson
        Map dataMap = new HashMap<String, String>();
        //value/colorJson
        Map contentVC = new HashMap<String, String>();
        Map cityVC = new HashMap<String,String>();
        Map timeVC = new HashMap<String,String>();
        Map baitianVC = new HashMap<String,String>();
        Map yewanVC = new HashMap<String,String>();
        Map richuVC = new HashMap<String,String>();

        List list = new ArrayList<String>();
        StringBuilder sendMsgUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=");
        sendMsgUrl.append(accessToken);
        contentVC.put("value", msg);
        contentVC.put("color", "#CCFFFF");
        //获取当前日期时间星期
        String time = JodaTimeUtil.getNowDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "\t\t" + JodaTimeUtil.getWeek(new Date());
        //封装外层格式
        map.put("touser", wxSetting.getToUser());
        if (wxSetting.getFlag().isEmpty())
            return "消息类别未指定：Flag";
        if (wxSetting.getFlag().equals("2")) {

            dataMap.put("content", contentVC);
            map.put("template_id", wxSetting.getWatemplate());

        } else if (wxSetting.getFlag().equals("1")) {
            //封装msg
            dataMap.put("msg", contentVC);
            //封装time
            timeVC.put("value", "今天是"+time);
            timeVC.put("color", "#CCFFFF");
            dataMap.put("time", timeVC);
            //封装地区
            cityVC.put("value", "当前城市：" + weatherDTO.getArea_3());
            cityVC.put("color", "#CCFFFF");
            dataMap.put("city", cityVC);
            //封装白天天气
            baitianVC.put("value", "白天:" + weatherDTO.getWtNm1() + "\t\t气温:" + weatherDTO.getWtTemp1());
            baitianVC.put("color", "#CCFFFF");
            dataMap.put("baitian", baitianVC);
            //封装夜晚天气
            yewanVC.put("value", "夜间:" + weatherDTO.getWtNm2() + "\t\t气温:" + weatherDTO.getWtTemp2());
            yewanVC.put("color", "#CCFFFF");
            dataMap.put("yewan", yewanVC);
            //封装日出日落
            richuVC.put("value", "日出时间:" + weatherDTO.getWtSunr() + "\t\t日落时间:" + weatherDTO.getWtSuns());
            richuVC.put("color", "#CCFFFF");
            dataMap.put("richu", richuVC);

            map.put("template_id", wxSetting.getZatemplate());
        }
        log.info(dataMap.toString());
        map.put("topcolor", "#33FF33");
        map.put("data", dataMap);
        log.info(map.toString());
        String senData = JSONObject.toJSON(map).toString();
        log.info(senData);
        String result = NetUtil.doPost(sendMsgUrl.toString(), senData);
        return result;
    }

}
