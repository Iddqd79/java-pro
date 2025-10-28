package pro.java.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pro.java.dto.ProductDTO;
import pro.java.exceptions.IntegrationException;
import pro.java.service.IIntegrationService;

import java.util.List;

@Service
@Slf4j
public class IntegrationService implements IIntegrationService {
    private final RestTemplate productRestTemplate;
    @Value("${integration.product-service}")
    private String productUrl;

    @Autowired
    public IntegrationService(@Qualifier("productRestTemplate") RestTemplate productRestTemplate) {
        this.productRestTemplate = productRestTemplate;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(productUrl)
                .pathSegment("api", "v1", "product")
                .build();
        ResponseEntity<List<ProductDTO>> exchange = productRestTemplate.exchange(uri.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDTO>>() {
        });
        return exchange.getBody();
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(productUrl)
                .pathSegment("api", "v1", "product", Long.toString(productId))
                .build();
        ResponseEntity<ProductDTO> exchange = productRestTemplate.exchange(uri.toUriString(), HttpMethod.GET, null, ProductDTO.class);
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return exchange.getBody();
        }
        throw new IntegrationException(exchange.getStatusCode());

    }
}
