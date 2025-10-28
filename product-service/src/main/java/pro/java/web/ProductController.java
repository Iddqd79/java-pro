package pro.java.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.java.dto.ProductDTO;
import pro.java.service.IProductService;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final IProductService productService;

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) {
        return productService.create(product);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        Optional<ProductDTO> dto = productService.findById(id);
        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(dto.get());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
        return productService.findById(id).map(userDTO -> ResponseEntity.ok().body(userDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Collection<ProductDTO> getAll() {
        return productService.findAll();
    }

}
