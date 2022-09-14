package com.cdv.springboota;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mytest {
    @Test
    public void test02(){
        ApplicationContext ctx=new AnnotationConfigApplicationContext(User.class);
        Student create = (Student)ctx.getBean("create");
        System.out.println("----"+create);
    }
}
