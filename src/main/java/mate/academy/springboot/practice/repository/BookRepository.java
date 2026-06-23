package mate.academy.springboot.practice.repository;

import mate.academy.springboot.practice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
