package pro.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity

@Table(name = "operation_limit")
public class Limit {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "limit")
    private BigDecimal limit;
}