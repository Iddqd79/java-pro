package pro.java.dto;

import pro.java.enums.OperationStatus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link pro.java.entity.Operation}
 */
public record OperationDTO(Long id, BigDecimal amount, OperationStatus status,
                           Long userId) implements Serializable {
}