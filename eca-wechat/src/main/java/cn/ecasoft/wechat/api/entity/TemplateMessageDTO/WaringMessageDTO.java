package cn.ecasoft.wechat.api.entity.TemplateMessageDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预警通知模板
 * @author lizhiwei
 * @date 2021/12/16 13:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaringMessageDTO {
    private String firstData;
    private String waringContent;
    private String waringTime;
    private String remark;
}
