package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc "
            + "LEFT JOIN FETCH sc.user "
            + "LEFT JOIN FETCH sc.cartItems ci "
            + "LEFT JOIN FETCH ci.book "
            + "WHERE sc.id = :id")
    Optional<ShoppingCart> findById(@Param("id") Long id);
}
