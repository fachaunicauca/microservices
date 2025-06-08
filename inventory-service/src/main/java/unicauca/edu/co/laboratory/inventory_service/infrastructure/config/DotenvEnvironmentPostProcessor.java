package unicauca.edu.co.laboratory.inventory_service.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            String parentDirPath = Paths.get("./").toAbsolutePath().normalize().toString();

            Dotenv dotenv = Dotenv.configure()
                    .directory(parentDirPath)
                    .ignoreIfMissing()
                    .load();

            Map<String, Object> props = new HashMap<>();
            dotenv.entries().forEach(entry -> props.put(entry.getKey(), entry.getValue()));

            environment.getPropertySources().addFirst(new MapPropertySource("dotenv", props));
        } catch (Exception e) {
            LoggerFactory.getLogger(DotenvEnvironmentPostProcessor.class)
                    .error("Error cargando .env: {}", e.getMessage(), e);
        }
    }
}