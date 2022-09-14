package com.cdv.springboota;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class User {
    @Bean
    public Student create(){
        Student student = new Student();
        student.setAge("18");
        student.setId("ddd");
        student.setName("ss");
        return student;
    }
}
