package mate.academy.springboot.practice.mapper;

import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.CategoryDto;
import mate.academy.springboot.practice.dto.CreateCategoryRequestDto;
import mate.academy.springboot.practice.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto categoryRequestDto);

    void updateCategory(CreateCategoryRequestDto categoryRequestDto,
                        @MappingTarget Category category);
}
