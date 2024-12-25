package ecay.project.note.代码验证.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author Dee
 * @date 2024/1/5
 * <p>Description:
 */
@Data
public class TbCorpCertInfoJSB {
    // 此处注意, 这是建设部的certid, 中间库叫certnum
    private String certid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enddate;
    private String organname;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date organdate;
    private String legalman;
    private String legalmanduty;
    private String legalmanprotitle;
    private String corpcode;
    private String corpname;
    private Integer certtypenum;
    private Integer isvalid;
    private String unitman;
    private String unitmanduty;
    private String unitmanprotitle;
    private String techman;
    private String techmanduty;

    private Integer isdelete;
    private String torowguid;
    private Integer id;
    private String row_guid;
    private Integer version;

}
