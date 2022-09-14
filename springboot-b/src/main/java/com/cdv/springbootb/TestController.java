package com.cdv.springbootb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Autowired
    SchoolInfo schoolInfo;
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping("/test")
    public String tt(){
        return schoolInfo.toString();
    }
    @GetMapping("/test1")
    public String ttr(){
        redisTemplate.opsForValue().set("aaa","111");
        return "ok1";
    }
    @GetMapping("/test2")
    public String ttr2(){
        String aaa = (String)redisTemplate.opsForValue().get("aaa");
        return aaa;
    }

}
