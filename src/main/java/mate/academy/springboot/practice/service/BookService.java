package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.BookDto;
import mate.academy.springboot.practice.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    @PreAuthorize("hasRole('ADMIN,USER')")
    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void delete(Long id);
}
