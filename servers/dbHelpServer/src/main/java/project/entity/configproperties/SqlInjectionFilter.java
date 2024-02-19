package project.entity.configproperties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.utils.CustomRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: LUOYUNHENG
 * @date: 2023/9/12
 * @description:sql注入过滤器
 */
@Slf4j
@RefreshScope
@Service
public class SqlInjectionFilter implements Filter {

    // 总开关： 0：关闭 1：打开   在nacos中配置
    @Value("${third-part.sqlInjectionAllSwitch:1}")
    private Integer sqlInjectionAllSwitch;

    // 特殊字符的开关： 0：关闭 1：打开 在nacos中配置
    @Value("${third-part.sqlInjectionKeySwitch:1}")
    private Integer sqlInjectionKeySwitch;

    /**
     * 校验的关键词
     **/
    private static final String SQL_REG_EXP = ".*(\\b(and|exec|execute|insert|into|create|drop|table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
            "chr|mid|master|truncate|char|declare|or|like)\\b).*";

    /**
     * 根据开关是否校验字段的开头和结尾有特殊字符
     **/
    private static final List<String> keys = Arrays.asList(";","--",",","//","%","#","'","*");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(sqlInjectionAllSwitch.equals(0) || ("POST".equals(request.getMethod().toUpperCase()) && (request.getHeader("content-type") != null && request.getHeader("content-type").startsWith("multipart/form-data")))){              // 总开关关闭  或 文件上传不sql过滤
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        CustomRequestWrapper requestWrapper = new CustomRequestWrapper(request);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap =getParameterMap(parameterMap, request, requestWrapper);
        // 正则校验是否有SQL关键字
        for (Object obj : parameterMap.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            if (value != null) {
                boolean isValid = isSqlInject(value.toString(), servletResponse);
                if (!isValid) {
                    return;
                }
            }
        }
        filterChain.doFilter(requestWrapper, servletResponse);
    }
    private Map<String, Object> getParameterMap(Map<String, Object> paramMap, HttpServletRequest request, CustomRequestWrapper requestWrapper) {
        // 1.POST请求获取参数
        if ("POST".equals(request.getMethod().toUpperCase())) {
            String body = requestWrapper.getBody();
            if(body.equals("")){
                body = "{}";
            }
            paramMap = JSONObject.parseObject(body, HashMap.class);
        } else {
            Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
            //普通的GET请求
            if (parameterMap != null && parameterMap.size() > 0) {
                Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
                for (Map.Entry<String, String[]> next : entries) {
                    paramMap.put(next.getKey(), next.getValue()[0]);
                }
            } else {
                //GET请求,参数在URL路径型式,比如server/{var1}/{var2}
                String afterDecodeUrl = null;
                try {
                    //编码过URL需解码解码还原字符
                    afterDecodeUrl = URLDecoder.decode(request.getRequestURI(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paramMap.put("pathVar", afterDecodeUrl);
            }
        }
        return paramMap;
    }
    private boolean isSqlInject(String value, ServletResponse servletResponse) throws IOException {
        if ((null != value && value.toLowerCase().matches(SQL_REG_EXP)) || isKey(value)) {
            //  if (null != value && Pattern.compile(SQL_REG_EXP).matcher(value.toLowerCase()).find()) {
            log.info("入参中有非法字符: " + value);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Map<String, String> responseMap = new HashMap<>();
            // 匹配到非法字符,立即返回
            responseMap.put("code", "999");
            responseMap.put("message","入参中有非法字符");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(JSON.toJSONString(responseMap));
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;
    }

    /**
     * @desc   : 校验是否以关键字开头或结尾
     * @author : LUOYUNHENG
     * @create: 2023/09/12 15:38:00
     **/
    private  Boolean isKey(String value){
        // 不开启关键字校验
        if(sqlInjectionKeySwitch.equals(0)){
            return Boolean.FALSE;
        }
        for (String key : keys) {
            if(value.startsWith(key) || value.endsWith(key)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
