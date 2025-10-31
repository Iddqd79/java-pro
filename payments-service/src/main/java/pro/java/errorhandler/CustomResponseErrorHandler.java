package pro.java.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import pro.java.exceptions.IntegrationException;

import java.io.IOException;

@Slf4j
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            // Handle 4xx client errors
            log.error("Client error: {} - {}", response.getStatusCode(), response.getStatusText());
        } else if (response.getStatusCode().is5xxServerError()) {
            // Handle 5xx server errors
            log.error("Server error: {} - {}", response.getStatusCode(), response.getStatusText());
        }
        throw new IntegrationException(response.getStatusCode());
    }
}