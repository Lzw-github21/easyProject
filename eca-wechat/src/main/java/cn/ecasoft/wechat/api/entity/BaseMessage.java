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
public class BaseMessage {

    protected String ToUserName;
    protected String FromUserName;
    protected Long CreateTime;
    protected String MsgType;

}
