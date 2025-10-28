package pro.java.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pro.java.errorhandler.CustomResponseErrorHandler;

@Configuration
public class RestTemplateConfiguration {
    @Bean("productRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .errorHandler(new CustomResponseErrorHandler())
                .build();
    }
}
