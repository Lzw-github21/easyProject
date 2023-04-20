package cn.ecasoft.wechat.api.entity.TemplateMessageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 *  检测结果通知模板
 * @author lizhiwei
 * @date 2021/12/16 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckMessageDTO {
    private String firstData;
    private String checkProject;
    private String checkUtil;
    private String resultCheck;
    private String checkTime;
    private String remark;
}
