package vip.zhguo.xier.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * [晚安消息实体类]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2023/2/10 12:40]
 */
@Data
@TableName("t_wa_message")
public class WaMessage {
    @TableId(type = IdType.ASSIGN_UUID)
    String lsh;
    String content;
    Date create_time;
//    Date replay_time;
    String fromUser;

}
