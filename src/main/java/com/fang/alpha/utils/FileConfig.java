package com.fang.alpha.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

@Configuration
public class FileConfig extends WebMvcConfigurationSupport {
    @Value("${project.storePath}")
    private String filePath;

//    @Value("${project.temp}")
//    private String temp;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        File dic = new File("");
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("file:"+temp);
                .addResourceLocations("file:"+dic.getAbsolutePath()+filePath);
        super.addResourceHandlers(registry);
    }
}
