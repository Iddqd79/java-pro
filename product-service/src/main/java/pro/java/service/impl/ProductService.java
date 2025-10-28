package pro.java.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.java.dto.ProductDTO;
import pro.java.entity.Product;
import pro.java.mapper.ProductMapper;
import pro.java.repository.ProductRepository;
import pro.java.service.IProductService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    @Value("${limit.value}")
    private BigDecimal limit;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.convertToEntity(dto);
        Product storedProduct = productRepository.saveAndFlush(product);
        return productMapper.convertToDTO(storedProduct);
    }


    @Override
    public void delete(ProductDTO dto) {
        Product product = productMapper.convertToEntity(dto);
        productRepository.delete(product);
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id).map(productMapper::convertToDTO);
    }

    @Override
    public Collection<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::convertToDTO).toList();
    }

    @Override
    public void reset() {
        productRepository.reset(limit);
    }
}
