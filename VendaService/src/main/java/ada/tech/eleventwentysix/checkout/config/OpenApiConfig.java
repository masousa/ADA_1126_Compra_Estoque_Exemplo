package ada.tech.eleventwentysix.checkout.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI defaultOpenApiConfig(){
        return new OpenAPI()
                .info(new Info().title("Exemplo de serviço de venda")
                        .description("Default description ")
                        .version("0.0.1"));
    }
}
