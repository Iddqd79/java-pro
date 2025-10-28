package pro.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link pro.java.entity.Limit}
 */
public record LimitDTO(Long userId, BigDecimal limit) implements Serializable {
}