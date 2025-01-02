package ecay.project.note.Result;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 *
 * @param <T>
 */
//@ToString //生成toString方法
//@NoArgsConstructor  //生成无参构造方法
//@AllArgsConstructor  //生成全参构造方法
@Accessors(chain = true) //链式调用
//@Data 包含：@ToString， @EqualsAndHashCode，@Getter / @Setter / @RequiredArgsConstructor
public class R<T> // implements Serializable
                                              {

//        private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> R<T> ok() { //静态方法，可以直接通过类名调用
        return restResult(null, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDescription());
    }

    public static <T> R<T> ok(T data) { //静态方法，可以直接通过类名调用
        return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDescription());
    }

    public static <T> R<T> ok(T data, String msg) {//静态方法，可以直接通过类名调用
        return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, ErrorCodeEnum.SYSTEM_ERROR_B0001.getCode(), ErrorCodeEnum.SYSTEM_ERROR_B0001.getDescription());
    }

    public static <T> R<T> failed(ErrorCodeEnum errorCodeEnum) {
        return restResult(null, errorCodeEnum.getCode(), errorCodeEnum.getDescription());
    }

    public static <T> R<T> failed(T data, ErrorCodeEnum errorCodeEnum) {
        return restResult(data, errorCodeEnum.getCode(), errorCodeEnum.getDescription());
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ErrorCodeEnum.SYSTEM_ERROR_B0001.getCode(), msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, ErrorCodeEnum.SYSTEM_ERROR_B0001.getCode(), ErrorCodeEnum.SYSTEM_ERROR_B0001.getDescription());
    }

    private static <T> R<T> restResult(T data, String code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
