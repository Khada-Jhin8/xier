package vip.zhguo.xier.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.util.NetUtil;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WxSetting;
import vip.zhguo.xier.util.WxUtil;

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
    public String goodNight() throws Exception {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat(" HH:mm");
        String time = df.format(System.currentTimeMillis());
        String msg = "(⊙o⊙)…溪儿的私人助理…(⊙o⊙)\n\n" +
                " ━┉… ●━━……━━● …┉━ \n"+
               "你的状态，就是你最好的风水\n" +
                "不用活的太复杂，把心当做屋子\n" +
                "定期打扫一下，该换的换，该丢的丢\n" +
                "不动声色的变得越来越好\n"+
                " ━┉… ●━━……━━● …┉━ \n\n"+
                "现在是北京时间深夜"+time+"分\n" +
                "我是郑经人儿\n" +
                "zu美美滴你....周末愉快，晚安~ლ(′◉o◉｀ლ)";
        // 请求Accesstoken
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg,wxSetting);
        return result;
    }

    @GetMapping("/wa-m")
    public String goodNight(String msg) throws Exception {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis());

        // 请求Accesstoken
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg,wxSetting);
        return result;
    }


}
