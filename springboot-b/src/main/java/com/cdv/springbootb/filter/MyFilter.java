package com.cdv.springbootb.filter;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//@Component
//@WebFilter(urlPatterns = "/api/*", filterName = "logFilter")
public class MyFilter implements Filter {

//    @Override
//    public void init(FilterConfig config) throws ServletException {
//    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        String header = "";
        if (cookies == null) {
            System.out.println("从request中未读取到cookie");
        } else {
            Boolean isUUID= false;
            System.out.println("cookie数量: " + cookies.length);
            for (Cookie aCookie : cookies) {
                String name = aCookie.getName();
                String value = aCookie.getValue();
                System.out.println(name + " = " + value);
                if("UUID".equals(aCookie.getName())){
                    isUUID=true;
                    header = aCookie.getValue();
                }
            }
            if(!isUUID) {
                // 创建一个 cookie对象
                Cookie cookie = new Cookie("UUID", "UUID.randomUUID().toString()");
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                cookie.setSecure(false);
                //cookie.setDomain("amsp.link");
                //将cookie对象加入response响应
                response.addCookie(cookie);
            }
        }

        //String header = request.getHeader("X-UUID");
        String st = request.getHeader("X-Ticket");
        String userAgent = request.getHeader("User-Agent");
        String ipAddr = "IpUtils.getIpAddr(request)";
        String url = request.getServerName() + ":" + request.getServerPort()+request.getRequestURI();
        String method = request.getMethod();
        String param= "";
        if (HttpMethod.PUT.name().equals(method) || HttpMethod.POST.name().equals(method)|| HttpMethod.DELETE.name().equals(method))
        {
            ServletInputStream inputStream = request.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bfReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bfReader.readLine()) != null){
                sb.append(line);
            }
            String str = sb.toString();
            //这里可以对字符串进行检查检查是否有敏感字。
            byte[] result=str.getBytes("UTF-8");
            //处理后的字符串在转成byte塞回request的流里面去
            servletRequest =new MyHttpServletRequest((HttpServletRequest)servletRequest,result);
            param= str;
        }
        String queryurl=request.getQueryString();
        if(null != queryurl){
            url += "?"+queryurl;
        }
        int status = response.getStatus();
        //AsyncManager.me().execute(TimerTaskUtils.recordOper(header, st, ipAddr, url, method, param, String.valueOf(status),userAgent));
        chain.doFilter(servletRequest, servletResponse);
    }
}
