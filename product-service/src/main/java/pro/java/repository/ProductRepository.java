package pro.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.java.entity.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "update service_product set limit=:defaultLimit",
            nativeQuery = true)
    void reset(BigDecimal defaultLimit);
}
