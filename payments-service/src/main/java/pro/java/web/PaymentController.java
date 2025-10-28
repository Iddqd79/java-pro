package pro.java.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.java.dto.ExecutorResponse;
import pro.java.service.IPaymentService;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PaymentController {
    private final IPaymentService paymentService;

    @GetMapping("/validate")
    public ExecutorResponse validate(@RequestParam Long productId, @RequestParam BigDecimal amount) {
        return paymentService.checkTransaction(productId, amount);
    }

}
