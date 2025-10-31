package pro.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.java.entity.Operation;
import pro.java.enums.OperationStatus;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Modifying
    @Query(value = "update  Operation set status =:status")
    public void updateOperation(@Param("status") OperationStatus status);
}
