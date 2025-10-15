package pro.java.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pro.java.enums.ProductType;

import java.math.BigDecimal;


@Entity
@Table(name = "service_product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
