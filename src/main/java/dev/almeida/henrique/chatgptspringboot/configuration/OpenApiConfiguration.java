package dev.almeida.henrique.chatgptspringboot.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "localhost/:8080", description = "localhost")
        }
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApiConfiguration() {
        return new OpenAPI()
                .info(new Info().title("Chat GPT Assistant").version("v1"))
                .servers(List.of(
                        new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8080").url("URL base"))
                );
    }
}
