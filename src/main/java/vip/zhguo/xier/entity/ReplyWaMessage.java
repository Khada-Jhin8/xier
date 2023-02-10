package vip.zhguo.xier.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhenghg
 * @since 2023-02-10
 */
@Data
@TableName("t_reply_wa_message")
@ApiModel(value = "ReplyWaMessage对象", description = "")
public class ReplyWaMessage implements Serializable {

    private static final long serialVersionUID = 1L;

      private String replyId;

    private String lsh;

    private String content;

    private LocalDateTime create_time;

    private String fromUser;

}
