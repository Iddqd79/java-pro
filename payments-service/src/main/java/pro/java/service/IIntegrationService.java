package pro.java.service;

import pro.java.dto.ProductDTO;

import java.util.List;

public interface IIntegrationService {


    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Long productId);

    void resetLimit();
}
