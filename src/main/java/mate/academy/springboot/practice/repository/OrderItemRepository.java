package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi "
            + "LEFT JOIN FETCH oi.book "
            + "WHERE oi.id = :itemId")
    Optional<OrderItem> findById(@Param("itemId") Long itemId);
}
