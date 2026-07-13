package mate.academy.springboot.practice.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.BookDto;
import mate.academy.springboot.practice.dto.CreateBookRequestDto;
import mate.academy.springboot.practice.model.Book;
import mate.academy.springboot.practice.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "categoriesIds", ignore = true)
    BookDto toDto(Book book);

    @AfterMapping
    default void setCategoriesIds(Book book, @MappingTarget BookDto bookDto) {
        List<Long> categoriesIds = book.getCategories()
                .stream()
                .map(Category::getId)
                .toList();
        bookDto.setCategoriesIds(categoriesIds);
    }

    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

    @AfterMapping
    default void setCategories(CreateBookRequestDto requestDto, @MappingTarget Book book) {
        Set<Category> categories = requestDto.getCategoriesIds()
                .stream()
                .map(Category::new)
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }

    void updateBook(CreateBookRequestDto createBookRequestDto, @MappingTarget Book book);
}
