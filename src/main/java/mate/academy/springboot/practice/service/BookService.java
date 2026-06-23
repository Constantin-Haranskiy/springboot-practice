package mate.academy.springboot.practice.service;

import java.util.List;
import mate.academy.springboot.practice.dto.BookDto;
import mate.academy.springboot.practice.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
