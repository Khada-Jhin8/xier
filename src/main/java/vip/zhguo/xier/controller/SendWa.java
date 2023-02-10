package vip.zhguo.xier.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.entity.WaMessage;
import vip.zhguo.xier.pojo.TempValue;
import vip.zhguo.xier.pojo.WxSetting;
import vip.zhguo.xier.service.IWaMessageService;
import vip.zhguo.xier.util.WxUtil;

import java.util.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2022/8/23 10:37]
 */
@Api(value = "晚安", tags = "晚安")
@RestController
@Slf4j
public class SendWa {
    @Autowired
    TempValue tempValue;
    @Autowired
    WxSetting wxSetting;

    @Autowired
    IWaMessageService waMessageService;


    @ApiOperation(value = "1.默认")
    @GetMapping("/wa")
    public String goodNight() throws Exception {
        wxSetting.setFlag("2");
        //获取当前时间
//        String time = JodaTimeUtil.getShanghaiDateTime();
        String msg = "穿过挪威的森林，让我走进你的梦里。夕阳落在我的铠甲，王子不一定骑着白马，黑马王子四海为家";
        // 请求Accesstoken
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg, wxSetting, null);
        //记录到库
        WaMessage waMessage = new WaMessage();
        waMessage.setContent(msg);
        waMessage.setCreate_time(new Date());
        waMessageService.save(waMessage);
        return result;
    }

    @ApiOperation(value = "2.get带参")
    @GetMapping("/wa-g")
    public String goodNight(String msg) throws Exception {
        wxSetting.setFlag("2");
        // 请求Accesstoken
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg, wxSetting, null);
        //记录到库
        WaMessage waMessage = new WaMessage();
        waMessage.setContent(msg);
        waMessage.setCreate_time(new Date());
        waMessageService.save(waMessage);
        return result;
    }

    @ApiOperation(value = "3.post带参")
    @PostMapping("/wa-p")
    public String goodNightp(@RequestBody Map msg) throws Exception {
        wxSetting.setFlag("2");
        String accessToken = WxUtil.getAccessToken(wxSetting.getAppId(), wxSetting.getAppSecret());
        //发送消息
        String result = WxUtil.sendMsg(accessToken, msg.get("msg").toString(), wxSetting, null);
        //记录到库
        WaMessage waMessage = new WaMessage();
        waMessage.setContent(msg.get("msg").toString());
        waMessage.setCreate_time(new Date());
        waMessageService.save(waMessage);
        return result;
    }


}
