package EcbProject.cn.controller.Transactional;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum StatusEnum {
    ENABLED(1, "已启用"),
    DISABLED(0, "已禁用");


    private final int code;
//    @EnumValue// 标记此字段的值对应数据库中的存储值
    private final String description;

    StatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}