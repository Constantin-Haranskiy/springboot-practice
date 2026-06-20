package mate.academy.springboot.practice.repository;

import java.util.List;
import mate.academy.springboot.practice.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
