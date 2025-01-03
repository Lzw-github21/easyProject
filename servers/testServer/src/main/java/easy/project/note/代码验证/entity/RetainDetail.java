package easy.project.note.代码验证.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dee
 * @date 2024/1/17
 * <p>Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetainDetail {
    private String certid;
    private String row_guid;
    private String certtypenum;
    private Integer tradetypenum;
    private Integer tradetypeboundtypenum;
    private Integer titlelevelnum;
    private String recordguid;
    private String mark;
}
