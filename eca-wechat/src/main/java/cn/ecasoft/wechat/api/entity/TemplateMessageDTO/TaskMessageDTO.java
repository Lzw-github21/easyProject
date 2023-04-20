package cn.ecasoft.wechat.api.entity.TemplateMessageDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新任务分配提醒通知模板
 * @author lizhiwei
 * @date 2021/12/16 12:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMessageDTO {
    private String firstData;
    private String projectName;
    private String taskName;
    private String director;
    private String endTime;
    private String remark;
}
