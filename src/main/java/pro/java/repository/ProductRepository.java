package pro.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.java.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
