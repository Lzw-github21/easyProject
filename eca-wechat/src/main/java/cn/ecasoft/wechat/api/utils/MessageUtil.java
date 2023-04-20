package cn.ecasoft.wechat.api.utils;

/**
 * 封装微信消息类型，有一个解析微信发过的xml消息的工具
 */

import cn.ecasoft.wechat.api.constant.enums.MessageType;
import cn.ecasoft.wechat.api.entity.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MessageUtil {

    /**
     * 将xml文件转换为map
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {

        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 从request中得到输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到XML的根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        // 判断又没有子元素列表
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getText());
        } else {
            for (Element e : elementList)
                map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }

    /**
     * 回复关注
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String subscribeForText(String toUserName, String fromUserName,String messageContext){

        TextMessage textMessage = new TextMessage();
        //必填
        textMessage.setFromUserName(toUserName);
        //必填
        textMessage.setToUserName(fromUserName);
        //必填
        textMessage.setMsgType(MessageType.REQ_MESSAGE_TYPE_TEXT.getValue());
        //必填
        textMessage.setContent(messageContext);
        textMessage.setCreateTime( LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());

        String xmlString = MessageUtil.textMessageToXml(textMessage);
        return xmlString;

    }

    /**
     * 回复文本消息,暂时不用
     * @param toUserName
     * @param fromUserName
     * @param msg
     * @return
     */
    public static String replyForText(String toUserName, String fromUserName, String msg){

        TextMessage textMessage = new TextMessage();
        //必填
        textMessage.setFromUserName(toUserName);
        //必填
        textMessage.setToUserName(fromUserName);
        //必填
        textMessage.setMsgType(MessageType.REQ_MESSAGE_TYPE_TEXT.getValue());
        //必填
        textMessage.setContent(msg);
        textMessage.setCreateTime( LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());

        String xmlString = MessageUtil.textMessageToXml(textMessage);
        log.info(xmlString);
        return xmlString;

    }

    /**
     * @param textMessage
     * @return xml
     * @Description: 文本消息对象转换成xml
     * @date 2016-12-01
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 对象到xml的处理
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    }
    );

}

