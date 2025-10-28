package pro.java.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.java.dto.ProductDTO;
import pro.java.service.IIntegrationService;

import java.util.List;

@Service
@Slf4j
public class IntegrationService implements IIntegrationService {
    private final RestTemplate productRestTemplate;

    @Autowired
    public IntegrationService(@Qualifier("paymentsExecutorClient") RestTemplate productRestTemplate) {
        this.productRestTemplate = productRestTemplate;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        ResponseEntity<List<ProductDTO>> exchange = productRestTemplate.exchange("product", HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDTO>>() {
        });
        return exchange.getBody();
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        ResponseEntity<ProductDTO> exchange = productRestTemplate.exchange("product/{id}", HttpMethod.GET, null, ProductDTO.class, productId);
        return exchange.getBody();
    }
}
