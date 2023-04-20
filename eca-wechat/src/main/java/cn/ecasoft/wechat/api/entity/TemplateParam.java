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
public class TemplateParam {
    // 参数名称
    private String name;
    // 参数值
    private String value;
    // 颜色
    private String color;

}
