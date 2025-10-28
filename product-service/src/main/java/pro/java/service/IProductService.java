package pro.java.service;

import pro.java.dto.ProductDTO;

import java.util.Collection;
import java.util.Optional;

public interface IProductService {
    ProductDTO create(ProductDTO user);

    void delete(ProductDTO user);

    Optional<ProductDTO> findById(Long id);

    Collection<ProductDTO> findAll();

}
