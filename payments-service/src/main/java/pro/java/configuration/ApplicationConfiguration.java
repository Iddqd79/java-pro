package pro.java.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pro.java.errorhandler.CustomResponseErrorHandler;

@Configuration
@EnableConfigurationProperties({RestTemplateClientsProperties.class})
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final RestTemplateClientsProperties restTemplateClientsProperties;

    @Bean("paymentsExecutorClient")
    public RestTemplate executorClient() {
        RestTemplateProperties paymentsExecutorClient = restTemplateClientsProperties.getPaymentsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(paymentsExecutorClient.getUrl())
                .setConnectTimeout(paymentsExecutorClient.getConnectTimeout())
                .setReadTimeout(paymentsExecutorClient.getReadTimeout())
                .errorHandler(new CustomResponseErrorHandler())
                .build();
    }
}
