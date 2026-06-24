package mate.academy.springboot.practice.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.BookDto;
import mate.academy.springboot.practice.dto.CreateBookRequestDto;
import mate.academy.springboot.practice.exception.EntityNotFoundException;
import mate.academy.springboot.practice.mapper.BookMapper;
import mate.academy.springboot.practice.model.Book;
import mate.academy.springboot.practice.repository.BookRepository;
import mate.academy.springboot.practice.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto book) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(book)));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return new PageImpl<>(
                bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList()
        );
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cant find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    @Transactional
    public BookDto update(Long id, CreateBookRequestDto createBookRequestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cant find book by id: " + id)
        );
        bookMapper.updateBook(createBookRequestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
