package cn.ecasoft.wechat.api.service.impl;

import cn.ecasoft.basic.datatable.DataTable;
import cn.ecasoft.redis.RedisService;
import cn.ecasoft.utils.DBhelper;
import cn.ecasoft.utils.StringUtils;
import cn.ecasoft.wechat.api.config.WeChatProperties;
import cn.ecasoft.wechat.api.constant.enums.WeChartMessageEnum;
import cn.ecasoft.wechat.api.entity.Template;
import cn.ecasoft.wechat.api.entity.TemplateMessageDTO.CheckMessageDTO;
import cn.ecasoft.wechat.api.entity.TemplateMessageDTO.TaskMessageDTO;
import cn.ecasoft.wechat.api.entity.TemplateMessageDTO.WaringMessageDTO;
import cn.ecasoft.wechat.api.entity.TemplateParam;
import cn.ecasoft.wechat.api.service.WeChatService;
import cn.ecasoft.wechat.api.utils.HttpClient;
import cn.ecasoft.wechat.api.utils.SHA1;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lizhiwei
 * @date 2021/12/3 16:14
 */
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    WeChatProperties weChatProperties;
    @Autowired
    HashMap<String, WeChatProperties.Template> weChatTemplateMap;
    @Autowired
    DBhelper dbHelper;
    @Autowired
    RedisService redisService;

    @Override
    public boolean wxMsgConsumptionSuccess(String userId, String jsonMessage, WeChartMessageEnum messageType) throws Exception {
        // 需要跳转的url----点击消息推送时需要跳转的地址
        String url = "";
        // 获取消费成功通知模板信息
        List<TemplateParam> paras = new ArrayList<>();
        switch (messageType) {
            //新任务分配提醒
            case NEW_TASK_ASSIGN_MESSAGE:
                TaskMessageDTO taskMessageDTO = JSONObject.parseObject(jsonMessage, TaskMessageDTO.class);
                String firstData = Optional.ofNullable(taskMessageDTO.getFirstData()).orElse(weChatTemplateMap.get(messageType.getValue()).getFirst());
                String remark = Optional.ofNullable(taskMessageDTO.getRemark()).orElse(weChatTemplateMap.get(messageType.getValue()).getRemark());
                paras.add(new TemplateParam("first", firstData, "#808080"));
                paras.add(new TemplateParam("keyword1", taskMessageDTO.getProjectName(), "#000000"));
                paras.add(new TemplateParam("keyword2", taskMessageDTO.getTaskName(), "#000000"));
                paras.add(new TemplateParam("keyword3", taskMessageDTO.getDirector(), "#000000"));
                paras.add(new TemplateParam("keyword4", taskMessageDTO.getEndTime(), "#000000"));
                paras.add(new TemplateParam("remark", remark, "#000000"));
                return sendWXTmplMsg(weChatProperties.getTemplates().get(0).getId(), userId, url, paras);
            //检测结果通知
            case CHECK_RESULT_MESSAGE:
                CheckMessageDTO checkMessageDTO = JSONObject.parseObject(jsonMessage, CheckMessageDTO.class);
                String checkFirst = Optional.ofNullable(checkMessageDTO.getFirstData()).orElse(weChatTemplateMap.get(messageType.getValue()).getFirst());
                String checkRemark = Optional.ofNullable(checkMessageDTO.getRemark()).orElse(weChatTemplateMap.get(messageType.getValue()).getRemark());
                paras.add(new TemplateParam("first", checkFirst, "#808080"));
                paras.add(new TemplateParam("keyword1", checkMessageDTO.getCheckProject(), "#000000"));
                paras.add(new TemplateParam("keyword2", checkMessageDTO.getCheckUtil(), "#000000"));
                paras.add(new TemplateParam("keyword3", checkMessageDTO.getResultCheck(), "#000000"));
                paras.add(new TemplateParam("keyword4", checkMessageDTO.getCheckTime(), "#000000"));
                paras.add(new TemplateParam("remark", checkRemark, "#000000"));
                return sendWXTmplMsg(weChatProperties.getTemplates().get(2).getId(), userId, url, paras);
            //预警消息通知
            case ADVANCE_WARING_MESSAGE:
                WaringMessageDTO waringMessageDTO = JSONObject.parseObject(jsonMessage, WaringMessageDTO.class);
                String warnFirst = Optional.ofNullable(waringMessageDTO.getFirstData()).orElse(weChatTemplateMap.get(messageType.getValue()).getFirst());
                String warnRemark = Optional.ofNullable(waringMessageDTO.getRemark()).orElse(weChatTemplateMap.get(messageType.getValue()).getRemark());
                paras.add(new TemplateParam("first", warnFirst, "#808080"));
                paras.add(new TemplateParam("keyword1", waringMessageDTO.getWaringContent(), "#000000"));
                paras.add(new TemplateParam("keyword2", waringMessageDTO.getWaringTime(), "#000000"));
                paras.add(new TemplateParam("remark", warnRemark, "#000000"));
                return sendWXTmplMsg(weChatProperties.getTemplates().get(1).getId(), userId, url, paras);
            default:
                return false;
        }
    }

    /**
     * 消息发送通知
     *
     * @param templateId 模板id
     * @param userId     userId
     * @param url        详情url
     * @param paras      模板样式
     * @return
     */
    @Override
    public boolean sendWXTmplMsg(String templateId, String userId, String url, List<TemplateParam> paras) throws Exception {
        boolean flag = false;
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("ST_ID", userId);
        Map map = dbHelper.QueryMap("", "wechat-select-isBindWeChart", parameter);
        String openid;
        try {
            openid = map.get("ST_OPENID").toString();
        } catch (Exception e) {
            throw new Exception("该人员尚未绑定浙里建公众号，无法发送公众号消息通知");
        }
        Template tem = new Template();
        // 模板id
        tem.setTemplateId(templateId);
        // openid
        tem.setToUser(openid);
        // 地址
        tem.setUrl(url);
        tem.setTemplateParamList(paras);
        String accessToken = "";
        try {
            accessToken = getAccessToken();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        String TemplateMqUrl = weChatProperties.getUrlConfig().getSendTemplateMq() + accessToken;
        String json = HttpClient.doPost(TemplateMqUrl, tem.toJSON());
        JSONObject jsonResult = JSON.parseObject(json);
        if (jsonResult != null) {
            // 状态码
            int errorCode = jsonResult.getInteger("errcode");
            // 状态信息
            String errorMessage = jsonResult.getString("errmsg");
            if (errorCode == 0) {
                flag = true;
                log.info("成功向" + userId + "用户浙里建公众号发送模板消息");
            } else {
                log.error("sendTemplateMsg===>模板消息发送失败:" + errorCode + "," + errorMessage);
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public String getAccessToken() throws Exception {
        String accessToken = "";
        try {
            accessToken = redisService.getString("weChatAccessToken");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (StringUtils.isBlank(accessToken)) {
            String url = weChatProperties.getUrlConfig().getGetAccessToken() + weChatProperties.getConfigs().get(0).getAppId() + "&secret=" + weChatProperties.getConfigs().get(0).getSecret();
            try {
                String responseEntity = new HttpClient().doGetOne(url);
                JSONObject jsonObj = JSON.parseObject(responseEntity);
                accessToken = (String) jsonObj.get("access_token");
                redisService.set("weChatAccessToken", accessToken, 60 * 90);
            } catch (Exception e) {
                log.error("公众号accessToken获取失败");
            }
        }
        if (StringUtils.isBlank(accessToken)) {
            log.error("公众号accessToken获取失败,accesstoken值为null");
        }
        return accessToken;
    }

    @Override
    public String getTemporaryTicket(String sceneId) throws Exception {
        String ticket = "";
        String responseEntity = "";
        String accessToken = getAccessToken();
        String tickerUrl = weChatProperties.getUrlConfig().getGetTemporaryTicket() + accessToken;
        JSONObject data = new JSONObject();
        data.put("action_name", "QR_STR_SCENE");
        data.put("expire_seconds", 3600);//一小时
        JSONObject scene = new JSONObject();
        scene.put("scene_str", sceneId);
        JSONObject actionInfo = new JSONObject();
        actionInfo.put("scene", scene);
        data.put("action_info", actionInfo);
        try {
            responseEntity = HttpClient.doPost(tickerUrl, data.toJSONString());
        } catch (Exception e) {
            log.error("公众号获取二维码时ticket获取失败");
        }
        JSONObject jsonObj = JSON.parseObject(responseEntity);
        ticket = (String) jsonObj.get("ticket");
        if (ticket == null) {
            log.error("公众号获取二维码时ticket获取失败");
        }
        return ticket;
    }

    @Override
    public String showTemporaryQrCode(String sceneId) throws Exception {
        String ticket = getTemporaryTicket(sceneId);
        String url = weChatProperties.getUrlConfig().getShowQrCode() + ticket;
        return url;
    }

    @Override
    public boolean judgeIsBind(String userId) throws Exception {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("ST_ID", userId);
        Map map = dbHelper.QueryMap("", "wechat-select-isBindWeChart", parameter);
        if (map != null) {
            if (map.get("ST_OPENID") != null) {
                if (map.get("ST_OPENID").toString().isEmpty() || map.get("ST_OPENID").toString().equals("")) {
                    return false;
                } else return true;
            } else return false;
        } else return false;
    }

    @Override
    public DataTable bingStOpenid(String userId, String openId) throws Exception {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("ST_ID", userId);
        parameter.put("ST_OPENID", openId);
        return dbHelper.QueryDataTable("", "wechat-update-stUserOpenid", parameter);
    }

    /**
     * 校验签名
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {

        String[] str = new String[]{weChatProperties.getConfigs().get(0).getToken(), timestamp, nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }

}
