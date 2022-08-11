package com.project.skb.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("Access_Token")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:3000","http://172.30.1.55:3000","http://23.23.240.178:3000","http://192.168.0.192:3000")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
