package easy.project.springbootFilter.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//只指定拦截路径，不设置filterName一样可以注入  实现Filter接口，用@WebFilter注解，指定拦截路径以及一些参数，同时需要在启动类使用@ServletComponentScan扫描带@WebFilter、@WebServlet、@WebListener并将帮我们注入bean
//配置拦截路径
//@WebFilter({"/test"})
//配置拦截路径
@WebFilter(filterName = "testFilter",urlPatterns = {"/test"})
//@ServletComponentScan
//@Component
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("TestFilter,"+request.getRequestURI());
        String token = servletRequest.getParameter("token");
        if(token == null){
            throw new RuntimeException("鉴权参数缺失！");
        }
        //filterChain.doFilter(servletRequest, servletResponse);
        if(!token.equals("##!@##")){
            //执行
            response.setStatus(401);
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
    // 在服务器正常关闭后，Filter对象被销毁，会执行destroy方法，只执行一次，用于释放资源.
    //仅限于正常销毁，测试通过端口号终止进程，此方法并不会打印
    @Override
    public void destroy() {
        System.out.println("过滤器已销毁");
    }
}
