package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi "
            + "JOIN FETCH oi.order o "
            + "WHERE oi.id = :itemId AND o.id = :orderId AND o.user.id = :userId")
    Optional<OrderItem> findByIdAndOrderIdAndUserId(
            @Param("itemId") Long id,
            @Param("orderId") Long orderId,
            @Param("userId") Long userId
    );
}
