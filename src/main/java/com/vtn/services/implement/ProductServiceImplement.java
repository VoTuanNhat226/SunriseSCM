package com.vtn.services.implement;

import com.vtn.components.GlobalService;
import com.vtn.dto.product.ProductResponseForDetails;
import com.vtn.dto.product.ProductResponseForList;
import com.vtn.pojo.Product;
import com.vtn.pojo.Tag;
import com.vtn.repository.ProductRepository;
import com.vtn.services.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImplement implements ProductService {

    @Autowired
    private GlobalService globalService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SupplierService supplierService;

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void save(Product Product) {
        this.productRepository.save(Product);
    }

    @Override
    public void update(Product Product) {
        this.productRepository.update(Product);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.productRepository.count();
    }

    @Override
    public List<Product> findAllWithFilter(Map<String, String> params) {
        return this.productRepository.findAllWithFilter(params);
    }

    @Override
    public ProductResponseForList getProductResponseForList(@NotNull Product product) {
        return ProductResponseForList.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .supplier(this.supplierService.getSupplierResponse(product.getSupplier()))
                .build();
    }

    @Override
    public List<ProductResponseForList> getAllProductResponseForList(@NotNull List<Product> products) {
        return products.stream().map(this::getProductResponseForList).collect(Collectors.toList());
    }

    @Override
    public ProductResponseForDetails getProductResponseForDetails(@NotNull Product product) {
        return ProductResponseForDetails.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .category(this.categoryService.getCategoryResponse(product.getCategory()))
                .unit(this.unitService.getUnitResponse(product.getUnit()))
                .supplier(this.supplierService.getSupplierResponse(product.getSupplier()))
                .tagSet(product.getTagSet().stream()
                        .map(this.tagService::getTagResponse)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void save(Product product, @NotNull List<Long> tagIds) {
        processProduct(product, tagIds);

        this.productRepository.save(product);
    }

    @Override
    public void update(Product product, @NotNull List<Long> tagIds) {
        processProduct(product, tagIds);

        this.productRepository.update(product);
    }

    private void processProduct(Product product, @NotNull List<Long> tagIds) {
        Set<Tag> tags = new HashSet<>();
        for (Long tagId : tagIds) {
            Tag tag = this.tagService.findById(tagId);
            if (tag != null) {
                tags.add(tag);
            }
        }
        product.setTagSet(tags);

        if (product.getFile() != null && !product.getFile().isEmpty()) {
            product.setImage(this.globalService.uploadImage(product.getFile()));
        }
    }
}
