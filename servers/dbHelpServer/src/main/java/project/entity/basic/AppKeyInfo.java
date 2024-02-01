package project.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: CaiZhiGang
 * @Title: AppKeyInfo
 * @ProjectName: servers
 * @Description: TODO
 * @date: 2021/6/3 20:58
 */
@Data
public class AppKeyInfo {
    @JSONField(name = "ID")
    private Integer ID;
    @JSONField(name = "APPNAME")
    private String APPNAME;
    @JSONField(name = "APPKEY")
    private String APPKEY;
    @JSONField(name = "APPSECRET")
    private String APPSECRET;
    @JSONField(name = "USERID")
    private String USERID;
    @JSONField(name = "ACCESSRESOURCES")
    private String ACCESSRESOURCES;
    @JSONField(name = "APPEXPLAIN")
    private String APPEXPLAIN;
    @JSONField(name = "TOKEN_TIMEOUT")
    private Long TOKEN_TIMEOUT;
}
