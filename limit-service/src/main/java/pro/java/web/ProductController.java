package pro.java.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.java.dto.LimitDTO;
import pro.java.service.ILimitService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/operation")
public class ProductController {
    private final ILimitService limitService;

    @GetMapping("/limit/{user-id}")
    public LimitDTO get(@PathVariable("user-id") Long userId) {
        return limitService.get(userId);
    }

    @PutMapping("/hold/{user-id}")
    public void hold(@PathVariable("user-id") Long userId, @RequestParam(name = "amount") BigDecimal amount) {
        limitService.hold(userId, amount);
    }

    @PutMapping("/decline/{operation-id}")
    public void decline(@PathVariable("operation-id") Long operationId) {
        limitService.decline(operationId);
    }

    @PutMapping("/accept/{operation-id}")
    public void accept(@PathVariable("operation-id") Long operationId) {
        limitService.accept(operationId);
    }


}
