package pro.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.java.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("update User u set u.username = :username where u.id = :id")
    void updateName(@Param("username") String username, @Param("id") Long id);

    User getUserById(Long id);

    @Modifying
    @Transactional
    @Query("delete User u  where u.id = :id")
    void deleteUserById(Long id);
}
