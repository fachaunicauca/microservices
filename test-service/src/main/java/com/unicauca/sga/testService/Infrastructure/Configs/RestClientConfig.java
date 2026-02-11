package com.unicauca.sga.testService.Infrastructure.Configs;

import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .requestInterceptor((request, body, execution) -> {

                    String token = getTokenFromSecurityContext();

                    if (token != null) {
                        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    }

                    return execution.execute(request, body);
                })
                .build();
    }

    private String getTokenFromSecurityContext() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getTokenValue();
        }

        return null;
    }
}
