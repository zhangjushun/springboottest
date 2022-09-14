package com.cdv.springbootb.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
    private byte[] body;


    public MyHttpServletRequest(HttpServletRequest request, byte[] newbody) {
        super(request);
        try {
            body=newbody;
        } catch (Exception ex) {
            body = new byte[0];
        }

    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final  ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private void replaceParams(byte[] replaceByte) throws IOException {
        body=replaceByte;
    }

}
