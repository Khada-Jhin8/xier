package vip.zhguo.xier.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zhguo.xier.entity.ReplyWaMessage;
import vip.zhguo.xier.entity.WaMessage;
import vip.zhguo.xier.pojo.WaAndReply;
import vip.zhguo.xier.service.IReplyWaMessageService;
import vip.zhguo.xier.service.IWaMessageService;

import java.util.ArrayList;
import java.util.List;

/**
 * [获取晚安和回复的数据]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2023/2/10 17:28]
 */
@RestController
@RequestMapping("/list")
public class GetList {
    @Autowired
    IWaMessageService waMessageService;
    @Autowired
    IReplyWaMessageService replyWaMessageService;

    @GetMapping("/waAndReply")
    public List<WaAndReply> getwaAndReply() {
        //返回列表
        List<WaAndReply> waAndReplies = new ArrayList<WaAndReply>();
        //根据时间降序获取晚安信息列表。
        List<WaMessage> walist = waMessageService.list(new QueryWrapper<WaMessage>().orderByDesc("create_time"));
        //根基晚安lsh迭代封装回复晚安的数据
        for (int i = 0; i < walist.size(); i++) {

            QueryWrapper<ReplyWaMessage> replyWa = new QueryWrapper<>();

            WaMessage waMessage = walist.get(i);

            replyWa.eq("lsh", waMessage.getLsh());
            replyWa.orderByDesc("create_time");

            List<ReplyWaMessage> replyWalist = replyWaMessageService.list(replyWa);

            WaAndReply waAndReply = new WaAndReply();
//
            waAndReply.setLsh(waMessage.getLsh());

            waAndReply.setContent(waMessage.getContent());

            waAndReply.setCreate_time(waMessage.getCreate_time());

            waAndReply.setReply(replyWalist);

            waAndReplies.add(waAndReply);
        }
        return waAndReplies;
    }
}
