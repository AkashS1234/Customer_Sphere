package com.customersphere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Configure CORS mapping for frontend-backend communication
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://localhost:3000") // Frontend URL (React app)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow methods
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(true); // Allow credentials (cookies, etc.)
    }
}
