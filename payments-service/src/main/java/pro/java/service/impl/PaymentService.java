package pro.java.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.java.dto.ExecutorResponse;
import pro.java.dto.ProductDTO;
import pro.java.enums.ValidationStatus;
import pro.java.service.IIntegrationService;
import pro.java.service.IPaymentService;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final IIntegrationService integrationService;

    @Override
    public ExecutorResponse checkTransaction(Long productId, BigDecimal amount) {
        ProductDTO product = integrationService.getProduct(productId);
        return amount.compareTo(product.getBalance()) > 0 ?
                new ExecutorResponse(ValidationStatus.INVALID)
                : new ExecutorResponse(ValidationStatus.VALID);
    }
}
