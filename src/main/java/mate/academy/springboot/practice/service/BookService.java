package mate.academy.springboot.practice.service;

import java.util.List;
import mate.academy.springboot.practice.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
