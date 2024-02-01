package project.entity.basic;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;

/**
 * @Description： 用户实体类
 * @Date：Created in 11:44 2019/10/30
 * @Modified By：
 */
public class UserInfo {
    private int UserId;


    private String UserName;

    private String LoginName;

    private String password;

    /**
     * 手机号
     */
    private String MobileTel;
    /**
     * 身份证号或社会统一信用代码
     */
    private String IDCARD;
    /**
     * 政府组织机构代码
     */
    private String ORGCODE;
    /**
     * 账户类型（个人0、企业1、政府用户2、...）
     */
    private String StaffType;

    /**
     * 主部门编号
     */
    private String MainDeptID;
    /**
     * 主部门名称
     */
    private String MainDeptName;

    /**
     * 所属角色
     */
    private HashMap<String, String> Roles;
    /**
     * 所属部门
     */
    private HashMap<String, String> Depts;

    /**
     * 登录时间
     */
    private String NowTime;

    /**
     * 刷新时间
     */
    private String RefreshTime;

    /**
     * 是否新注册用户
     */
    private Boolean isNewUser;
    /**
     * 认证名称
     */
    private String identityName;
    /**
     * 认证code
     */
    private String identityCode;
    /**
     * 认证角色 0-个人,1-法人
     */
    private String identityRole;

    /**
     * 市区划代码
     */
    private String cityNum;

    /**
     * 区县代码
     */
    private String countyNum;

    /**
     * 职务代码
     */
    private String staffStatus;
    /**
    * 浙政钉登录名
    * */
    private String ZZDLOGINNAME;
    /**
     * ZZDACCOUNTID
     * */
    private String ZZDACCOUNTID;
    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getCountyNum() {
        return countyNum;
    }

    public void setCountyNum(String countyNum) {
        this.countyNum = countyNum;
    }

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public Boolean getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(Boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshTime() {
        return RefreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        RefreshTime = refreshTime;
    }

    public String getNowTime() {
        return NowTime;
    }

    public void setNowTime(String nowTime) {
        NowTime = nowTime;
    }


    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getMobileTel() {
        return MobileTel;
    }

    public void setMobileTel(String mobileTel) {
        MobileTel = mobileTel;
    }

    @JSONField(name = "idcard")
    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    @JSONField(name = "orgcode")
    public String getORGCODE() {
        return ORGCODE;
    }

    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }

    public String getStaffType() {
        return StaffType;
    }

    public void setStaffType(String staffType) {
        StaffType = staffType;
    }

    public String getMainDeptID() {
        return MainDeptID;
    }

    public void setMainDeptID(String mainDeptID) {
        MainDeptID = mainDeptID;
    }

    public String getMainDeptName() {
        return MainDeptName;
    }

    public void setMainDeptName(String mainDeptName) {
        MainDeptName = mainDeptName;
    }

    public HashMap<String, String> getRoles() {
        return Roles;
    }

    public void setRoles(HashMap<String, String> roles) {
        Roles = roles;
    }

    public HashMap<String, String> getDepts() {
        return Depts;
    }

    public void setDepts(HashMap<String, String> depts) {
        Depts = depts;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getIdentityRole() {
        return identityRole;
    }

    public void setIdentityRole(String identityRole) {
        this.identityRole = identityRole;
    }

    public String getZZDLOGINNAME() {
        return ZZDLOGINNAME;
    }

    public void setZZDLOGINNAME(String ZZDLOGINNAME) {
        this.ZZDLOGINNAME = ZZDLOGINNAME;
    }
    public String getZZDACCOUNTID() {
        return ZZDACCOUNTID;
    }

    public void setZZDACCOUNTID(String ZZDACCOUNTID) {
        this.ZZDACCOUNTID = ZZDACCOUNTID;
    }
}
