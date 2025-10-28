package pro.java.dto;

import lombok.Getter;
import lombok.Setter;
import pro.java.enums.ProductType;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private ProductType type;
}
