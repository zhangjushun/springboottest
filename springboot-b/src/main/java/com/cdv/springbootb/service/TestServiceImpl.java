package com.cdv.springbootb.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class TestServiceImpl implements TestService {
    @Override
    public void hello(String message) {
        System.out.println("执行"+message);
    }
}
