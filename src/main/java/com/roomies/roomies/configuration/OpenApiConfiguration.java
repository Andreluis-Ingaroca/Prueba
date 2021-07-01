package com.roomies.roomies.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "roomiesOpenApi")
    public OpenAPI roomiesOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Roomies Application API")
                        .description("Roomies API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));

    }
}
