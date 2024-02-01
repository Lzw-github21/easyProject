package project.entity.basic;

/**
 * 见GB 32100-2015 法人和其他组织统一社会信用代码编码规则.pdf
 * <p>
 * 社会统一信用代码 18位 阿拉伯数字或大写英文字母(不使用I、O、Z、S、V)组成
 * 第1位 ：登记管理部门代码
 * 第2位 ：机构类别代码  (医疗卫生机构类别代码)
 * 第3-8位 ：登记管理机关行政区划码
 * 第9-17位 ： 主体标识码（组织机构代码）
 * 第18位 : 校验码
 *
 * @author caizhigang
 * date 2022/7/30
 */

/**
 *
 */
public class UnifiedSocialCreditCode {
    /**
     * 统一社会信用代码
     */
    private String uscc;

    /**
     * 第1位 登记管理部门代码
     * 1 机构编制 2 外交；3司法行政；4 文化；5 民政；6旅游；7 宗教；8 工会；9 工商；
     * A中央军委改革和编制办公室；N农业；Y其他。
     */
    private String managementDepartmentCode;

    /**
     * 第2位 机构类别代码
     * 机构编制 1： 机关 1 事业单位 2 中央编办直接管理机构编制的群众团体 3 其他 9
     * 民政 5： 社会团体 1 民办非企业单位 2 基金会 3 其他 9
     * 工商 9： 企业 1 个体工商户 2 农民专业合作社 3
     * 其他 Y： 1
     */
    private String orgType;
    /**
     * 第3位-第8位 登记管理机关 行政区划代码
     * 参照 GB/T 2260 编码
     */
    private String admdvsCode;
    /**
     * 第9位-第17位 主体标识码(组织机构代码)
     * 参照GB 11714
     */
    private String orgCode;

    /**
     * 第18位 校验码
     */
    private String keyCode;

    public UnifiedSocialCreditCode() {
    }

    public UnifiedSocialCreditCode(String uscc) {
        this.uscc = uscc;
    }

    public String getUscc() {
        return uscc;
    }

    public void setUscc(String uscc) {
        this.uscc = uscc;
    }

    public String getManagementDepartmentCode() {
        return managementDepartmentCode;
    }

    public void setManagementDepartmentCode(String managementDepartmentCode) {
        this.managementDepartmentCode = managementDepartmentCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getAdmdvsCode() {
        return admdvsCode;
    }

    public void setAdmdvsCode(String admdvsCode) {
        this.admdvsCode = admdvsCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

}
