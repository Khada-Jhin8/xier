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
        String msg = "(⊙o⊙)…夜深了，人静了，月亮婆婆都睡了，她还偷偷跟我说：\n" +
                "让你\n" +
                "多一点快乐.\n" +
                "少一点烦恼.\n" +
                "累了就睡觉.\n" +
                "醒了就微笑.\n" +
                "现在是凌晨"+time+"分\n"+
                "小王同学，是时候和婆婆一起入睡了.Good Night~\n" +
                "我是已经躺着了，就等你的晚安了~ლ(′◉o◉｀ლ)";
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
