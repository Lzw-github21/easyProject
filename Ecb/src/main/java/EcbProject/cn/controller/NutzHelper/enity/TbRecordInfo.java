package EcbProject.cn.controller.NutzHelper.enity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


/**
 * 备案表(用于企业变更)
 */
@Data
@Table("tbrecordinfo")
public class TbRecordInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Name()
    @Column("row_guid")
    private String rowGuid;
    @Id(auto = false)
    private Integer id;
    @Column
    private Integer recordType;
    @Column
    private Integer recordStatus;
    @Column
    private String corpName;
    @Column
    private String corpCode;
    @Column
    private String recordDateTime;
    @Column
    private String passDateTime;
    @Column
    private Integer provinceNum;
    @Column
    private Integer cityNum;
    @Column
    private Integer countyNum;
    @Column
    private String upDateTm;
    @Column("ECA_FullTextField")
    private String ecaFullTextField;
    @Column
    private Integer isDelete;
    @Column
    private Integer isUpload;
    @Column
    private String createTime;
    @Column
    private String upDateTime;
    @Column
    private String auditStatus;
    @Column
    private String auditOption;
    @Column
    private String manageUserName;
    @Column
    private String manageDeptName;
    @Column
    private String auditTime;
    @Column
    private String overTime;

}