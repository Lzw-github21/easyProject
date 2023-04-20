package cn.ecasoft.wechat.api.constant.enums;

/**
 * @author lizhiwei
 * @date 2021/12/23 13:50
 */
public enum WeChartMessageEnum {
    NEW_TASK_ASSIGN_MESSAGE("NEW_TASK_ASSIGN_MESSAGE", "新任务分配提醒通知"),
    ADVANCE_WARING_MESSAGE("ADVANCE_WARING_MESSAGE", "预警通知"),
    CHECK_RESULT_MESSAGE("CHECK_RESULT_MESSAGE", "检测结果通知"),
    ;
    private String value;
    private String msg;

    WeChartMessageEnum(String value, String msg) {
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

