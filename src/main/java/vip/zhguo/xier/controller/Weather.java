package vip.zhguo.xier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.util.NetUtil;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WeatherDTO;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Weather {
    @Autowired
    TempValue tempValue;

    @GetMapping("/xier")
    public String getWeather() throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
//        Object today = json.get("result");
//        System.out.println(today);
        Map map = json.getJSONObject("result");
        Object today = map.get("today");
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
        System.out.println(weatherDTO);
//        String param = URLEncoder.encode("?title="+tempValue.getServerjiangtitle(),"UTF-8")+"&desp="+URLEncoder.encode(tempValue.getServerjiangdesp(),"UTF-8");
        Map map2 = new HashMap();
        map2.put("title",URLEncoder.encode(tempValue.getServerjiangtitle(),"UTF-8"));
        map2.put("desp",URLEncoder.encode(tempValue.getServerjiangdesp(),"UTF-8"));
//        String s = Util.senMsgGet(tempValue.getServerjiangurl(), map2.toString());
//        System.out.println(s);
        return today.toString();
    }

}

