package vip.zhguo.xier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.pojo.WxSetting;
import vip.zhguo.xier.util.JodaTimeUtil;
import vip.zhguo.xier.util.NetUtil;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WeatherDTO;
import vip.zhguo.xier.util.WxUtil;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class Weather {
    @Autowired
    TempValue tempValue;
    @Autowired
    WxSetting wxSetting;

//    @Scheduled(cron = "15,30,45 * * * * ?")
    @GetMapping("/xier")
    public String getWeather() throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
        Map map = json.getJSONObject("result");
        Object today = map.get("today");
        log.info("json======"+json.toString());
        log.info("today======"+today.toString());
        //获取地理位置
        weatherDTO.setArea_3(map.get("area_3").toString());
        Map map1 = (Map)today;
//        /*天气(白天)
        weatherDTO.setWtNm1(map1.get("wtNm1").toString());
//       /*天气(夜间)
       weatherDTO.setWtNm2(map1.get("wtNm2").toString());
        /*温度(白天)*/
       weatherDTO.setWtTemp1(map1.get("wtTemp1").toString());
        /*温度(夜间)*/
       weatherDTO.setWtTemp2(map1.get("wtTemp2").toString());
        /*日出时间*/
       weatherDTO.setWtSunr(map1.get("wtSunr").toString());
        /*日落时间(24小时制)*/
        weatherDTO.setWtSuns(map1.get("wtSuns").toString());
        log.info("weatherDTO"+weatherDTO.toString());

        String msg ="(⊙o⊙)…溪儿的天气闹钟…(⊙o⊙)\n\n" +
                    "每天给自己一个希望，试着不为明天而烦恼\n" +
                    "不为昨天而叹息，只为今天更美好。早安～\n\n"+
                    "今天是\t\t"+JodaTimeUtil.getNowDate(new Date(),"yyyy-MM-dd HH:mm:ss")+"\t\t"+ JodaTimeUtil.getWeek(new Date())+"\n"+
                    "当前城市\t\t"+weatherDTO.getArea_3()+"\n"+
                    "白天:"+weatherDTO.getWtNm1()+"\t\t气温:"+weatherDTO.getWtTemp1()+
                    "\n夜间:"+weatherDTO.getWtNm2()+"\t\t气温:"+weatherDTO.getWtTemp2()+
                    "\n日出时间:"+weatherDTO.getWtSunr()+"\t\t日落时间:"+ weatherDTO.getWtSuns();

        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg,wxSetting);
//        System.out.println(s);
        return result;
    }

}

