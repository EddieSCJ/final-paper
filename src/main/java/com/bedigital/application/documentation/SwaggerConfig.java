package com.bedigital.application.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, defaultMessageFor500())
                .apiInfo(apiInfo());
    }

    public List<ResponseMessage> defaultMessageFor500() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error!")
                    .build());

            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Bad Request!")
                    .build());

            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Forbidden!")
                    .build());

            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found!")
                    .build());

            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("OK!")
                    .build());
        }};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Be Digital API Documentation")
                .description("Be Digital endpoints to test")
                .version("0.0.1")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Edcleidson Junior", "dev.to/eddiescj", "eddieprofessionalmail@gmail.com.br"))
                .build();
    }

}
