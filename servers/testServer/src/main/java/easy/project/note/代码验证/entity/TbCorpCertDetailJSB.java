package easy.project.note.代码验证.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author Dee
 * @date 2024/1/5
 * <p>Description:
 */
@Data
public class TbCorpCertDetailJSB {
    private String certid;
    private String corpcode;
    private Integer ismaster;
    private String mark;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date notedate;
    private Integer titlelevelnum;
    private Integer tradeboundnum;
    private Integer tradetypenum;
    private String tradetypeboundchildmark;

    private String row_guid;
    private Integer id;
    private String zizhimark;
    private Integer isdelete;
    private String recordguid;
    private Integer version;

}
