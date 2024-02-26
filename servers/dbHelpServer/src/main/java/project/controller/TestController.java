package project.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import project.service.DBhelper;
import project.service.impl.DbhelpSelectServer;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

import static project.controller.RSAUtil.decoder;

/**
 * @author 李志威
 * @Description
 * @date 2024/2/1
 */
@RestController
@RequestMapping("")
public class TestController {

    @Autowired
    DbhelpSelectServer dbhelpSelectServer;

    @GetMapping("/")
    private void test() {
        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(() -> {
                System.out.println(Thread.currentThread() + "3");
                try {
                    dbhelpSelectServer.test1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "t3");
            t1.start();
        }
    }

    @GetMapping("/test1")
    private void test1() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }

    @GetMapping("/test2")
    private void test2() {
        System.out.println("hahahahahah");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test3();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }

    @GetMapping("/test3")
    private void test3() {
        System.out.println("hahahahahah");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                dbhelpSelectServer.test4();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
    }
    @GetMapping("/test4")
    private void test4(ServletResponse servletResponse, ServletRequest servletRequest) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        DispatcherServlet dispatcherServlet;
        Map<String, String> responseMap = new HashMap<>();
        // 匹配到非法字符,立即返回
        responseMap.put("code", "999");
        responseMap.put("message","入参中有非法字符");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(JSON.toJSONString(responseMap));
//        response.getWriter().flush();
//        response.getWriter().close();
    }
    @GetMapping("/test5")
    private void test5(HttpServletResponse response, ServletRequest request) throws IOException, ServletException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8"); // 设置响应内容类型和字符编码

        PrintWriter out = response.getWriter(); // 获取PrintWriter对象

        try {
            out.println("<html><body>");
            out.println("<h1>Flushing Example</h1>");
            out.println("<p>This is some initial content.</p>");

            // 刷新缓冲区，确保到目前为止的内容被发送到客户端
            out.flush();

            // 模拟一些处理时间，比如数据库查询或复杂计算
            Thread.sleep(2000); // 等待2秒（仅作示例，实际情况下应避免在Servlet中阻塞）

            out.println("<p>This content appears after a delay and a flush.</p>");

            // 再次刷新（尽管在这种情况下可能不是必需的，因为close()也会刷新）
            out.flush();
            Thread.sleep(2000); // 等待2秒（仅作示例，实际情况下应避免在Servlet中阻塞）
            response.sendRedirect("https://www.baidu.com");

        } catch (InterruptedException e) {
            // 处理中断异常（在这个例子中，我们简单地重新抛出它）
            throw new ServletException("Servlet processing was interrupted", e);
        } finally {
            out.close(); // 关闭PrintWriter，释放资源并刷新任何剩余的输出
        }
    }
}
