package com.fczx.tq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 通过读取配置项获取的文件上传路径
    @Value("${file.uploadFolder}")
    private String basePath;

    @Value("${file.staticAccessPath}")
    private String visualPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
         * 资源映射路径
         * addResourceHandler:访问映射路径
         * addResourceLocations:资源绝对路径
         */
        registry.addResourceHandler(visualPath).addResourceLocations("file:" + basePath);
    }
}