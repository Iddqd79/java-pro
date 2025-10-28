package pro.java.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "integration.clients")
public class RestTemplateClientsProperties {
    private final RestTemplateProperties paymentsExecutorClient;

    public RestTemplateClientsProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }

}
