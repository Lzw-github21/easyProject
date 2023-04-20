package cn.ecasoft.wechat.api.constant.enums;

/**
 * @author lizhiwei
 * @date 2021/12/23 14:57
 */
public enum MessageType {

    REQ_MESSAGE_TYPE_TEXT("text", "请求消息类型：文本"),
    REQ_MESSAGE_TYPE_IMAGE("image", "请求消息类型：图片"),
    REQ_MESSAGE_TYPE_VOICE("voice", "请求消息类型：语音"),
    REQ_MESSAGE_TYPE_VIDEO("video", "请求消息类型：视频"),
    REQ_MESSAGE_TYPE_LINK("link", "请求消息类型：链接"),
    REQ_MESSAGE_TYPE_LOCATION("location", "请求消息类型：地理位置"),
    REQ_MESSAGE_TYPE_SHORTVIDEO("shortvideo", "请求消息类型：小视频"),
    REQ_MESSAGE_TYPE_EVENT("event", "请求消息类型：事件推送"),
    RESP_MESSAGE_TYPE_TEXT("text", "返回消息类型：文本"),
    RESP_MESSAGE_TYPE_IMAGE("image", "返回消息类型：图片"),
    RESP_MESSAGE_TYPE_VOICE("voice", "返回消息类型：语音"),
    RESP_MESSAGE_TYPE_MUSIC("music", "返回消息类型：音乐"),
    RESP_MESSAGE_TYPE_NEWS("news", "返回消息类型：图文"),
    RESP_MESSAGE_TYPE_VIDEO("video", "返回消息类型：视频"),
    EVENT_TYPE_SUBSCRIBE("subscribe", "事件类型：订阅"),
    EVENT_TYPE_UNSUBSCRIBE("unsubscribe", "事件类型：取消订阅"),
    EVENT_TYPE_SCAN("SCAN", "事件类型：关注用户扫描带参二维码"),
    EVENT_TYPE_LOCATION("location", "事件类型：上报地理位置"),
    EVENT_TYPE_CLICK("SCAN", "事件类型：点击菜单拉取消息"),
    EVENT_TYPE_VIEW("VIEW", "事件类型：自定义菜单URl视图"),
    EVENT_TYPE_TEMPLATESENDJOBFINISH("TEMPLATESENDJOBFINISH", "事件类型：模板消息送达情况提醒"),
    ;
    private String value;
    private String msg;

    MessageType(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String code) {
        this.value = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
