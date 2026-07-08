package mate.academy.springboot.practice.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.CategoryDto;
import mate.academy.springboot.practice.dto.CreateCategoryRequestDto;
import mate.academy.springboot.practice.exception.EntityNotFoundException;
import mate.academy.springboot.practice.mapper.CategoryMapper;
import mate.academy.springboot.practice.model.Category;
import mate.academy.springboot.practice.repository.CategoryRepository;
import mate.academy.springboot.practice.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return new PageImpl<>(
                categoryRepository.findAll(pageable)
                        .stream()
                        .map(categoryMapper::toDto)
                        .toList()
        );
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryRequestDto) {
        return categoryMapper.toDto(
                categoryRepository.save(categoryMapper.toModel(categoryRequestDto)));
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found")
        );
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto) {
        Category categoryFromDb = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found")
        );
        categoryMapper.updateCategory(categoryRequestDto, categoryFromDb);
        return categoryMapper.toDto(categoryRepository.save(categoryFromDb));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
