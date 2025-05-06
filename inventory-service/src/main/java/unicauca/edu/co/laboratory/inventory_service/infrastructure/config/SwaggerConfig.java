package unicauca.edu.co.laboratory.inventory_service.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Inventory Service API",
                version = "1.0",
                description = "Documentation Inventory Service v1.0"
), security = @SecurityRequirement(name = "keycloak.local"))
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "keycloak.local";
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8081"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Keycloak OAuth2")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("http://keycloak.local:8181/realms/microservices-realm/protocol/openid-connect/auth")
                                                .tokenUrl("http://keycloak.local:8181/realms/microservices-realm/protocol/openid-connect/token")
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID Connect scope"))))))
                .security(List.of(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("keycloak.local")));

    }
}
