package EcbProject.cn.controller.NutzHelper.Enum;

/**
 * @author SaddyFire
 * @date 2022/4/3
 */
public enum AppHttpCodeEnum {

    ERRROR_PARAM(-99,"传入的参数有误，请重试！"),

    SUCCESS(200,"操作成功"),

    TOKEN_REQUIRE(201,"TOKEN是必须的"),
    TOKEN_EXPIRE(202,"TOKEN已过期"),
    ERRROR_REFERER(203,"错误来源"),
    NO_DATA(204,"暂无数据"),

    SERVER_ERROR(500,"服务器内部错误"),
    FREQUENT_ACCESS(501,"频繁访问,稍后重试"),
    ACCOUNT_FROZEN(503, "账户已临时禁用"),

    REQUEST_MAX(601, "已达最大访问量"),
    REQUEST_FREQUENCY(602,"您的操作太快了,请稍后重试"),


    INVALID_IP(21502,"无效IP"),
    IP_IS_NULL(21503,"IP是空值"),

    /**
     * 保函-银行回函响应
     */
    BH_OK(1000, "请求成功"),
    OSS_UPLOAD_FAILED(1996, "OSS上传文件失败"),
    OSS_DOWNLOAD_FAILED(1997, "OSS下载文件失败"),
    OSS_FILE_NO_EXIST(1998, "文件不存在"),
    BH_ERROR(1999,"发生异常"),
    BH_NO_DATA(19999, "保函信息未找到"),
    BH_ERROR_PARAM(1002, "必要参数缺失"),
    BH_DATA_REPEAT(1003, "数据重复"),
    BH_DATA_NOTEXIST(1004, "数据不存在"),
    BH_BAD_PARAM(1005, "非法参数"),
    /**
     * 保函-远端调用银行接口响应
     */
    BH_REMOTE_ERROR(1990, "远端调用失败"),
    BH_REQUEST_ERROR(1991, "操作失败"),

    /**
     * 数据库异常
     */
    DB_ERROR(5236,"发生异常"),
    DB_NO_DATA(5230, "暂无数据"),
    DB_BAD_DATASOURCE(5201, "没有配置数据库链接！"),
    ;


    int code;
    String errorMessage;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
