package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    Optional<User> findByEmail(String email);
}
