package vip.zhguo.xier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WeatherDTO;
import vip.zhguo.xier.pojo.WxSetting;
import vip.zhguo.xier.util.HttpUtils;
import vip.zhguo.xier.util.JodaTimeUtil;
import vip.zhguo.xier.util.NetUtil;
import vip.zhguo.xier.util.WxUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@SpringBootTest
class XierApplicationTests {

    @Autowired
    TempValue tempValue;
    @Autowired
    WxSetting wxSetting;

    @Test
    void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("America/Whitehorse"));
        String format = df.format(System.currentTimeMillis());
        System.out.println(format);

    }
    @Test
    void contextLoads1() {
        DateTime currentTime = JodaTimeUtil.getCurrentTime();
        String nowDate = JodaTimeUtil.getNowDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = JodaTimeUtil.getDateTime(new Date());
        System.out.println(currentTime);
        System.out.println(nowDate);
        System.out.println(dateTime);
        System.out.println(JodaTimeUtil.getShanghaiDateTime());
        String[] s = JodaTimeUtil.getShanghaiDateTime().split(" ");
        System.out.println(s[1]);

    }
    @Test
    void test1() throws Exception {
        WeatherDTO weatherDTO = new WeatherDTO();
        String res = NetUtil.doGet(tempValue.getHuoqutianqi());
        JSONObject json = JSON.parseObject(res);
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
        wxSetting.setFlag("1");
        String msg = "(⊙o⊙)…每天给自己一个希望，试着不为明天而烦恼\n" +
                "不为昨天而叹息，只为今天更美好。早安～";
//                "今天是\t\t"+JodaTimeUtil.getNowDate(new Date(),"yyyy-MM-dd HH:mm:ss")+"\t\t"+ JodaTimeUtil.getWeek(new Date())+"\n"+
//                "当前城市\t\t"+weatherDTO.getArea_3()+"\n"+
//                "白天:"+weatherDTO.getWtNm1()+"\t\t气温:"+weatherDTO.getWtTemp1()+
//                "\n夜间:"+weatherDTO.getWtNm2()+"\t\t气温:"+weatherDTO.getWtTemp2()+
//                "\n日出时间:"+weatherDTO.getWtSunr()+"\t\t日落时间:"+ weatherDTO.getWtSuns();

        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg,wxSetting,weatherDTO);

    }
}
