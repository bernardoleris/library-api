package com.emakers.demo.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API documentation for Library Services with Java 17 and Spring Boot 3") //titulo da documentacao
                        .version("v1") //versao da documentacao
                        .description("API for book, people and book lend register.") //descricao da documentacao
                )
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080").description("Local server")
                ));
    }
}
