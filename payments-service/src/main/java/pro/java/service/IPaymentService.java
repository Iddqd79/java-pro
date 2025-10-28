package pro.java.service;

import pro.java.dto.ExecutorResponse;

import java.math.BigDecimal;

public interface IPaymentService {
    ExecutorResponse checkTransaction(Long productId, BigDecimal amount);
}
