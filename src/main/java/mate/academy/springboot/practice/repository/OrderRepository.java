package mate.academy.springboot.practice.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.springboot.practice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o "
            + "LEFT JOIN FETCH o.user "
            + "LEFT JOIN FETCH o.orderItems oi "
            + "LEFT JOIN FETCH oi.book "
            + "WHERE o.id = :id")
    Optional<Order> findById(@Param("id") Long id);

    @Query("SELECT o FROM Order o "
            + "LEFT JOIN FETCH o.user u "
            + "LEFT JOIN FETCH o.orderItems oi "
            + "LEFT JOIN FETCH oi.book "
            + "WHERE u.id = :userId")
    Optional<List<Order>> findByUserId(@Param("userId") Long id);
}
