package mate.academy.springboot.practice.repository;

import java.util.Optional;
import mate.academy.springboot.practice.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "categories")
    Page<Book> findAllByCategoriesId(@Param("categoryId") Long categoryId, Pageable pageable);

    @EntityGraph(attributePaths = "categories")
    Page<Book> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "categories")
    Optional<Book> findById(@Param("id") Long id);
}
