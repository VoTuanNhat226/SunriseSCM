package com.vtn.service;

import com.vtn.dto.category.request.CategoryRequestDTO;
import com.vtn.dto.category.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO findCategoryById(Long id);
    List<CategoryResponseDTO> findAllCategories();
    CategoryResponseDTO addOrUpdateCategory(CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long id);
}
