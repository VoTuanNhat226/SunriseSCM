package com.vtn.service.impl;

import com.vtn.dto.product.request.ProductRequestDTO;
import com.vtn.dto.product.response.ProductResponseDTO;
import com.vtn.pojo.Category;
import com.vtn.pojo.Product;
import com.vtn.repository.CategoryRepository;
import com.vtn.repository.ProductRepository;
import com.vtn.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = this.productRepository.findById(id);

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .isDeleted(product.getIsDeleted())
                .categoryName(product.getCategory().getName())
                .build();
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products.stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .isDeleted(product.getIsDeleted())
                        .categoryName(product.getCategory().getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequest) {
        Category category = this.categoryRepository.findById(productRequest.getCategoryId());

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .category(category)
                .build();

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .categoryName(category.getName())
                .isDeleted(product.getIsDeleted())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.productRepository.findById(id);
        this.productRepository.delete(product);
    }
}
