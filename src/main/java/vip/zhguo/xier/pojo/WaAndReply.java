package vip.zhguo.xier.pojo;

import lombok.Data;
import vip.zhguo.xier.entity.ReplyWaMessage;

import java.util.Date;
import java.util.List;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2023/2/10 17:30]
 */
@Data
public class WaAndReply {
    String lsh;
    String content;
    Date create_time;
    List<ReplyWaMessage> reply;
}
