package com.unicauca.sga.testService.Infrastructure.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permite cualquier header
                .allowedHeaders("*")
                // Permite el uso de cookies y credenciales
                .allowCredentials(true)
                // Tiempo que el navegador puede cachear la pre-petición OPTIONS (en segundos)
                .maxAge(3600);
    }
}
