package mate.academy.springboot.practice.mapper;

import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.BookDto;
import mate.academy.springboot.practice.dto.CreateBookRequestDto;
import mate.academy.springboot.practice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBook(CreateBookRequestDto createBookRequestDto, @MappingTarget Book book);
}
