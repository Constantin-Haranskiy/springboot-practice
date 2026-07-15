package mate.academy.springboot.practice.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.springboot.practice.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findAllByUserId(Long userId);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
