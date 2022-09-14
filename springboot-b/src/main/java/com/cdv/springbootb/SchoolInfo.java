package com.cdv.springbootb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "school")
public class SchoolInfo {
    private String name;
    private String web;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "name='" + name + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
