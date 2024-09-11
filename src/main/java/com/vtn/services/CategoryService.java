package com.vtn.services;

import com.vtn.dto.category.CategoryResponse;
import com.vtn.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category findById(Long id);

    void save(Category category);

    void update(Category category);

    void delete(Long id);

    Long count();

    List<Category> findAllWithFilter(Map<String, String> params);

    CategoryResponse getCategoryResponse(Category category);

    List<CategoryResponse> getAllCategoryResponse(Map<String, String> params);
}
