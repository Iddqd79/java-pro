package pro.java.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pro.java.dto.ProductDTO;
import pro.java.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public Product convertToEntity(ProductDTO source) {
        return modelMapper.map(source, Product.class);
    }

    public ProductDTO convertToDTO(Product source) {
        return modelMapper.map(source, ProductDTO.class);
    }

}
