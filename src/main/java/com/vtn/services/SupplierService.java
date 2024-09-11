package com.vtn.services;

import com.vtn.dto.order.OrderResponse;
import com.vtn.dto.product.ProductRequestPublish;
import com.vtn.dto.product.ProductResponseForDetails;
import com.vtn.dto.rating.RatingRequestCreate;
import com.vtn.dto.supplier.SupplierDTO;
import com.vtn.pojo.Rating;
import com.vtn.pojo.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierService {

    Supplier findById(Long id);

    void save(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    Long count();

    List<Supplier> findAllWithFilter(Map<String, String> params);

    SupplierDTO getSupplierResponse(Supplier supplier);

    Supplier getProfileSupplier(String username);

    SupplierDTO updateProfileSupplier(String username, SupplierDTO supplierDTO);

    ProductResponseForDetails publishProduct(String username, ProductRequestPublish productRequestPublish);

    void unpublishProduct(String username, Long productId);

    List<OrderResponse> getOrdersOfSupplier(Long supplierId, Map<String, String> params);

    List<Rating> getRatingsOfSupplier(Long supplierId);

    Rating addRatingForSupplier(String username, Long supplierId, RatingRequestCreate ratingRequestCreate);
}
