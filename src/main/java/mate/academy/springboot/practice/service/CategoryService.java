package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.CategoryDto;
import mate.academy.springboot.practice.dto.CreateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto save(CreateCategoryRequestDto categoryRequestDto);

    CategoryDto findById(Long id);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto);

    void delete(Long id);
}
