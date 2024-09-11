package com.vtn.services.implement;

import com.vtn.dto.category.CategoryResponse;
import com.vtn.pojo.Category;
import com.vtn.pojo.Product;
import com.vtn.repository.CategoryRepository;
import com.vtn.repository.ProductRepository;
import com.vtn.services.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        this.categoryRepository.update(category);
    }

    @Override
    public void delete(Long id) {
        Category category = this.categoryRepository.findById(id);
        List<Product> productsToUpdate = new ArrayList<>(category.getProductSet());

        productsToUpdate.forEach(product -> {
            product.setCategory(null);
            this.productRepository.update(product);
        });

        this.categoryRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.categoryRepository.count();
    }

    @Override
    public List<Category> findAllWithFilter(Map<String, String> params) {
        return this.categoryRepository.findAllWithFilter(params);
    }

    @Override
    public CategoryResponse getCategoryResponse(@NotNull Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategoryResponse(Map<String, String> params) {
        return this.categoryRepository.findAllWithFilter(params).stream()
                .map(this::getCategoryResponse)
                .collect(Collectors.toList());
    }
}
