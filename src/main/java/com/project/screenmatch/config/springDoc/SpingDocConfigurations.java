package com.project.screenmatch.config.springDoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpingDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ScreenMatch API")
                        .description("API de filmes e séries para consulta e filtragem de dados. ")
                        .contact(new Contact()
                                .name("Lucas Haddad")
                                .email("lucas.haddad0799@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://screenMatch/api/licenca")));
    }
}