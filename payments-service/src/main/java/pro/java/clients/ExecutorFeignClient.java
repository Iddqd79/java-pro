package pro.java.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pro.java.configuration.ExecutorFeignClientConfiguration;
import pro.java.dto.ProductDTO;

import java.util.List;

@FeignClient(name = "executor-feign-client", url = "${integration.clients.payments-executor-client.url}", configuration = ExecutorFeignClientConfiguration.class)
public interface ExecutorFeignClient {
    @GetMapping()
    List<ProductDTO> getAllProducts();

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable String id);

    @PatchMapping("/reset")
    void resetLimit();

}
