package com.cdv.springbootb.intercepter;


import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class LoginIntercepter implements HandlerInterceptor {
    /**
     *
     * @param request
     * @param response
     * @param handler 被拦截的控制器对象
     * @return  true 能被处理
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //获取请求的方法
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();

        String st = request.getHeader("X-Ticket");
        String remoteHost = request.getRemoteHost();
        //校验
        //String s = "this.vaildST(st, remoteHost)";
        String s = "";
        if("error".equals(s)){
            Map ajaxResult = new HashMap();
            ajaxResult.put(HttpStatus.UNAUTHORIZED.value(), "未授权");
            ajaxResult.put("msg", "未授权你错了");
            PrintWriter writer = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(response.getOutputStream(),
                        "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = JSONObject.toJSONString(ajaxResult);
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
            } catch (UnsupportedEncodingException e) {
                log.error("过滤器返回信息失败:" + e.getMessage(), e);
            } catch (IOException e) {
                log.error("过滤器返回信息失败:" + e.getMessage(), e);
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != osw) {
                    osw.close();
                }
            }
            return true;
        }
        log.debug("放开成功！");
        //检查有没有需要用户权限的注解
        return true;
    }
}
