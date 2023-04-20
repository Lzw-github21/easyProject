package cn.ecasoft.wechat.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lizhiwei
 * @date 2021/12/3 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage extends BaseMessage {

    private String Content;// 文本消息内容

    private String MsgId;// 消息id，64位整型

}

