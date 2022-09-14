package com.cdv.springbootb.myservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//创建servlet
public class Myservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        //resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("text/html;");
        PrintWriter writer = resp.getWriter();
        writer.println("zhe核三的卡号是 charset=ISO-8859-1");
        writer.flush();
        writer.close();
    }
}
