package cn.huanzi.qch.springbootwebsocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李志威
 * @Description
 * @date 2023/4/19
 */
@RestController
@RequestMapping("/test")
public class AjaxTest {
    /**
     * 登录
     */
    @RequestMapping("/ajaxTest")
    public ModelAndView ajaxTest(HttpServletRequest request) {
        return new ModelAndView("跨域测试页面.html");
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();

        // 添加示例数据
        list.add(Map.of("groupId", 1, "name", "Alice", "score", 90));
        list.add(Map.of("groupId", 1, "name", "Bob", "score", 85));
        list.add(Map.of("groupId", 2, "name", "Charlie", "score", 95));
        list.add(Map.of("groupId", 2, "name", "David", "score", 88));

        // 使用Stream API进行分组
        Map<Integer, List<Map<String, Object>>> grouped = list.stream()
                .collect(Collectors.groupingBy(
                        map -> (Integer) map.get("groupId"),
                        Collectors.toList()
                ));

        // 输出分组结果
        grouped.forEach((groupId, group) -> {
            System.out.println("Group ID: " + groupId);
            group.forEach(System.out::println);
        });
    }
}
