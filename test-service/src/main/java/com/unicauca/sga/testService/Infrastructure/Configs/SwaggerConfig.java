package com.unicauca.sga.testService.Infrastructure.Configs;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Test")
                        .version("1.0")
                        .description("Documentación del microservicio de Test con Swagger y Springdoc OpenAPI"));
    }
}
