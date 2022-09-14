package com.cdv.springbootb;

import com.cdv.springbootb.service.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootBApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("Starting SpringApplication 创建容器");
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootBApplication.class, args);
        TestService userService = (TestService) ctx.getBean("userService");
        //获取容器执行方法
        userService.hello("world");
    }

    @Override
    public void run(String... args) throws Exception {
        //创建容器后
        System.out.println("读取文件，读取数据库");
    }
}
