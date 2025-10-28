package pro.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.java.clients.ExecutorFeignClient;
import pro.java.dto.ProductDTO;
import pro.java.service.IIntegrationService;

import java.util.List;

@Service
public class IntegrationService implements IIntegrationService {
    private final RestTemplate productRestTemplate;
    private final ExecutorFeignClient executorFeignClient;


    @Autowired
    public IntegrationService(@Qualifier("paymentsExecutorClient") RestTemplate productRestTemplate, ExecutorFeignClient executorFeignClient) {
        this.productRestTemplate = productRestTemplate;
        this.executorFeignClient = executorFeignClient;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return executorFeignClient.getAllProducts();
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        ResponseEntity<ProductDTO> exchange = productRestTemplate.exchange("product/{id}", HttpMethod.GET, null, ProductDTO.class, productId);
        return exchange.getBody();
    }

    @Override
    public void resetLimit() {
        executorFeignClient.resetLimit();
    }
}
