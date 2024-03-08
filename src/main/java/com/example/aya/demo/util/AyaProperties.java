package com.example.aya.demo.util;

import org.springframework.beans.factory.annotation.Value;

public class AyaProperties {
    @Value("${com.example.aya.title}")
    private String title;
    @Value("${com.example.aya.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
