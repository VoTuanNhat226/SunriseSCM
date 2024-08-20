package com.vtn.service;

import com.vtn.dto.product.request.ProductRequestDTO;
import com.vtn.dto.product.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO findProductById(Long id);
    List<ProductResponseDTO> findAllProducts();
    ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequest);
    void deleteProduct(Long id);
}
