package com.nrkt.springbootkafkalog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Spring Boot Logging Example",
                version = "1.0",
                description = "Spring Boot Logging Example with Kafka and Log4j2"))
public class OpenApiConfig {
}
