package br.com.andre.order.msorder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("dreeh.silva@hotmail.com");
        contact.setName("Andrê Reges");
        contact.setUrl("https://www.linkedin.com/in/andr%C3%AA-reges-38b746a9/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Order API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to get order information.")
                .termsOfService("")
                .license(mitLicense);

        return new OpenAPI()
                .info(info);
    }

}
