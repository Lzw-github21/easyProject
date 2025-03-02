package easy.project.note.WebSocket相关;

import easy.project.note.Result.R;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @program: tools
 */
@Slf4j
@RestController
@RequestMapping("/test/webSocket")
public class WebSocketController {
    @Autowired
    private WebSocketUtil webSocketUtil;

    /**
     * 获取在线用户信息
     *
     * @return ok
     */
    @GetMapping(value = "/getUser")
    public R getUser() {
        Map<String, WebSocket> users = webSocketUtil.getUsers();
        Set<String> ids = users.keySet();
        return R.ok(ids);
    }

    /**
     * 发送全体消息
     *
     * @param message 消息内容
     * @return ok
     */
    @GetMapping(value = "/sendMessageAll")
    public R sendMessageAll(@RequestParam String message) {
        try {
            webSocketUtil.sendMessageAll(message);
            log.info(DateUtil.now() + " | " + "admin" + " 全体消息-> " + message);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("消息推送失败!");
        }
        return R.ok("消息推送成功：" + message);
    }

    /**
     * 发送消息给某人
     *
     * @return ok
     */
    @GetMapping(value = "/sendMessageTo")
    public R sendMessageTo(@RequestParam String message, @RequestParam String userId) {
        try {
            webSocketUtil.sendMessageTo(message, userId);
            log.info(DateUtil.now() + " | " + "admin" + " 私人消息-> " + message, userId);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("消息推送失败!");
        }
        return R.ok();
    }
}

