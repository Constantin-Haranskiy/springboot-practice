package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByName(Role.RoleName name);
}
