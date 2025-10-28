package pro.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.java.entity.Limit;

import java.math.BigDecimal;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    @Modifying
    @Query(value = "update  Limit l set l.limit = :defaultLimit")
    void resetAll(@Param("defaultLimit") BigDecimal defaultLimit);

    @Modifying
    @Query(value = "update Limit l set l.limit = l.limit - :value  where l.userId = :userId")
    void decreaseLimit(@Param("value") BigDecimal value, @Param("userId") Long userId);

}
