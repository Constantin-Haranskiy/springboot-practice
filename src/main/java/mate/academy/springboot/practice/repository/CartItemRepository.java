package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT ci FROM CartItem ci "
            + "LEFT JOIN FETCH ci.shoppingCart "
            + "LEFT JOIN FETCH ci.book "
            + "WHERE ci.id = :itemId")
    Optional<CartItem> findById(@Param("itemId") Long itemId);
}
