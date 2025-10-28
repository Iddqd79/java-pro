package pro.java.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.java.dto.ProductDTO;
import pro.java.service.IIntegrationService;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
@RequestMapping("integration/api/v1/product")
public class IntegrationController {
    private final IIntegrationService integrationService;

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Long id) {
        return integrationService.getProduct(id);
    }

    @GetMapping
    public Collection<ProductDTO> getAll() {
        return integrationService.getAllProducts();
    }

}
