package ecay.project.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description： 认证过滤器
 * @Date：Created in 17:43 2019/10/30
 * @Modified By：
 */
@Slf4j
public class AuthorticationFilter extends HttpServlet implements Filter {

    //配置放行路径
    private String expires = null;
    private String signIn = null;
    private String HttpPre = null;
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    protected Logger logger = LoggerFactory.getLogger(AuthorticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("-----------------认证服务开始--------------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long start = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Cache-Control", "no-cache");
        res.setHeader("x-frame-options", "SAMEORIGIN");
        String access_token = "";
        String urlParameter = "";
        //获取url
        String servletPath = req.getServletPath().toLowerCase();
        //获取token
        if ("GET".equals(req.getMethod())) {//GET请求时的参数
            urlParameter = req.getQueryString();//网址中的参数
            if (urlParameter != null && !"".equals(urlParameter)) {
                try {
                    urlParameter = URLDecoder.decode(urlParameter, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.info("GET请求获取token异常");
                    throw e;
                }
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (urlParameter != null && urlParameter.contains("access_token")) {
            String[] arr = urlParameter.split("&");
            for (int i = 0; i < arr.length; i++) {
                String key = arr[i].substring(0, arr[i].indexOf("="));
                String value = arr[i].substring(arr[i].indexOf("=") + 1);
                map.put(key, value);
            }
        }
        access_token = (String) map.get("access_token");
        if (StringUtils.isEmpty(access_token)) {
            String authorization = req.getHeader("Authorization");
            if (authorization != null && !"".equals(authorization)) {
                access_token = authorization;
            }
        }
        //修改密码,退出访问必须得有token，否则提示登录超时
        if (expires != null && expires.trim().length() > 0) {
            for (String excludedPath : expires.split(",")) {
                String uriPattern = excludedPath.trim();
                // 支持ANT表达式
                if (antPathMatcher.match(uriPattern, servletPath)) {
                    //判断token是否有效
                    boolean access = judgeToken(access_token);
                    if (access) {
                        //有效
                        chain.doFilter(request, response);
                        return;
                    } else {
                        //无效
//                        String redirectUrl = req.getParameter("redirectUrl");
                        res.setStatus(401, "登录超时，请重新登录");
//                        res.sendRedirect(redirectUrl);
                        return;
                    }
                }
            }
        }

        //登录页面
        if (signIn != null && signIn.trim().length() > 0) {

            for (String excludedPath : signIn.split(",")) {
                String uriPattern = excludedPath.trim();
                if (antPathMatcher.match(uriPattern, servletPath)) {
                    //获取参数
                    Map<String, String[]> parameterMap = req.getParameterMap();
                    LinkedCaseInsensitiveMap<String[]> linkedMap = new LinkedCaseInsensitiveMap<>();
                    linkedMap.putAll(parameterMap);
                    String[] appKey = linkedMap.get("appKey");
                    String[] redirecturl = linkedMap.get("redirecturl");
                    //判断是否有appkey和redireckurl，如果没有返回非法访问
                    if (appKey == null
                            || redirecturl == null
                            || "".equals(appKey[0].trim())
                            || "".equals(redirecturl[0].trim())) {
                        //返回非法登录
                        res.sendRedirect(HttpPre + "/authserver/auth/Illegality");
                        return;
                    }

                    //判断token是否有效
                    boolean access = judgeToken(access_token);
                    if (access) {
                        //有效重定向到redirecturl,并带上重新生成的code
                        //判断是否有其他参数
                        boolean hasOther = redirecturl[0].contains("?");
                        if (hasOther) {
                            res.sendRedirect(redirecturl[0] + "&code=");
                        } else {
                            res.sendRedirect(redirecturl[0] + "?code=");
                        }
                        return;
                    } else {
                        //无效放行
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        logger.info("------执行时间为 :" + (System.currentTimeMillis() - start) + "毫秒------->>>");
        //其余一律放行
        chain.doFilter(request, response);
        return;

    }

    @Override
    public void destroy() {
        logger.info("-----------------认证服务结束--------------------");
    }


    public boolean judgeToken(String access_token) {
        //判断token是否有效，无效返回登录超时
        return true;

    }
}
