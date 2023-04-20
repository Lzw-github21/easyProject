package cn.ecasoft.wechat.api.service;

import cn.ecasoft.basic.datatable.DataTable;
import cn.ecasoft.wechat.api.constant.enums.WeChartMessageEnum;
import cn.ecasoft.wechat.api.entity.TemplateParam;

import java.util.List;

/**
 * @author lizhiwei
 * @date 2021/12/3 16:12
 */
public interface WeChatService {
    /**
     * 发送模板消息
     */
    boolean wxMsgConsumptionSuccess(String userId, String messageJson, WeChartMessageEnum messageType) throws Exception;
    /**
     * 转换为模板消息固定格式
     */
    boolean sendWXTmplMsg(String templateId, String openid, String url, List<TemplateParam> paras) throws Exception;
    /**
     * 获取公众号acessToken
     */
    String getAccessToken() throws Exception;
    /**
     * 创建临时二维码ticket
     */
    String getTemporaryTicket(String sceneId) throws Exception;
    /**
     * 根据sceneId获取获取二维码展示地址
     */
    String showTemporaryQrCode(String sceneId) throws Exception;
    /**
     * 判断是否绑定了微信公众号
     */
    boolean judgeIsBind(String userId) throws Exception;
    /**
     * 绑定人员st_openid
     */
    DataTable bingStOpenid(String userId,String openId)throws Exception;

}
