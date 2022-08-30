package vip.zhguo.xier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
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

@Api(value = "早安", tags = "早安")
@RestController
@Slf4j
public class Weather {
    @Autowired
    TempValue tempValue;
    @Autowired
    WxSetting wxSetting;

    @Scheduled(cron = "0 30 17 * * ?",zone="GMT+8")
    @ApiOperation(value = "1.默认")
    @GetMapping("/za")
    public String za() throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
        Map map = json.getJSONObject("result");
        Object today = map.get("today");
        log.info("json======" + json.toString());
        log.info("today======" + today.toString());
        //获取地理位置
        weatherDTO.setArea_3(map.get("area_3").toString());
        Map map1 = (Map) today;
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
        log.info("weatherDTO" + weatherDTO.toString());
        wxSetting.setFlag("1");
        String msg = "(⊙o⊙)…每天给自己一个希望，试着不为明天而烦恼\n" +
                "不为昨天而叹息，只为今天更美好。早安～";
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg, wxSetting, weatherDTO);
//        System.out.println(s);
        return result;
    }

    @ApiOperation(value = "2.get带参")
    @GetMapping("/za-g")
    public String za(String msg) throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
        Map map = json.getJSONObject("result");
        Object today = map.get("today");
        log.info("json======" + json.toString());
        log.info("today======" + today.toString());
        //获取地理位置
        weatherDTO.setArea_3(map.get("area_3").toString());
        Map map1 = (Map) today;
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
        log.info("weatherDTO" + weatherDTO.toString());
        wxSetting.setFlag("1");
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg, wxSetting, weatherDTO);
//        System.out.println(s);
        return result;
    }

    @ApiOperation(value = "3.post带参")
    @PostMapping("/za-p")
    public String zap(@RequestBody Map msg) throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
        Map map = json.getJSONObject("result");
        Object today = map.get("today");
        log.info("json======" + json.toString());
        log.info("today======" + today.toString());
        //获取地理位置
        weatherDTO.setArea_3(map.get("area_3").toString());
        Map map1 = (Map) today;
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
        log.info("weatherDTO" + weatherDTO.toString());
        wxSetting.setFlag("1");
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg.get("msg").toString(), wxSetting, weatherDTO);
//        System.out.println(s);
        return result;
    }
}

