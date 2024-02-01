package project.entity.basic;

/**
 * @author mhd
 * @date 2021/10/13/ 10:34:00
 * @Description TODO
 */
public class OperLogEntity {
    private String operType;
    private int userId;
    private String userName;
    private String reqIp;
    private JsonData param;
    private int mobileFlag;
    private UserInfo userInfo;

    public OperLogEntity(String operType, int userId, String userName, String reqIp, JsonData param, int mobileFlag) {
        this.operType = operType;
        this.userId = userId;
        this.userName = userName;
        this.reqIp = reqIp;
        this.param = param;
        this.mobileFlag = mobileFlag;
    }


    public String getOperType() {
        return operType;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public JsonData getParam() {
        return param;
    }

    public void setParam(JsonData param) {
        this.param = param;
    }

    public int getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(int mobileFlag) {
        this.mobileFlag = mobileFlag;
    }
}
