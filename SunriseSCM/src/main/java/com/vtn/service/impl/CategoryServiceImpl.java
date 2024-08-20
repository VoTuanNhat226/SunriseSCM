package com.vtn.service.impl;

import com.vtn.dto.category.request.CategoryRequestDTO;
import com.vtn.dto.category.response.CategoryResponseDTO;
import com.vtn.pojo.Category;
import com.vtn.repository.CategoryRepository;
import com.vtn.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id);

        return CategoryResponseDTO.builder()
                .categoryName(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponseDTO> findAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        return categories.stream().map(category -> CategoryResponseDTO.builder().categoryName(category.getName())
                .description(category.getDescription())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO addOrUpdateCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = Category.builder()
                .name(categoryRequestDTO.getName())
                .description(categoryRequestDTO.getDescription())
                .build();

        this.categoryRepository.saveOrUpdate(category);

        return CategoryResponseDTO.builder()
                .categoryName(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id);
        this.categoryRepository.delete(category);
    }
}
