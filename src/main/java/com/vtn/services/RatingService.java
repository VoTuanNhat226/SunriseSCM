package com.vtn.services;

import com.vtn.dto.rating.RatingRequestUpdate;
import com.vtn.dto.rating.RatingResponse;
import com.vtn.enums.CriteriaType;
import com.vtn.pojo.Rating;
import com.vtn.pojo.Supplier;

import java.util.List;
import java.util.Map;

public interface RatingService {

    Rating findById(Long id);

    void save(Rating rating);

    void update(Rating rating);

    void delete(Long id);

    Long count();

    List<Rating> findAllWithFilter(Map<String, String> params);

    RatingResponse getRatingResponse(Rating rating);

    List<RatingResponse> getAllRatingResponse(Map<String, String> params);

    Rating update(Rating rating, RatingRequestUpdate ratingRequestUpdate);

    List<Supplier> getRankedSuppliers(CriteriaType criteriaType, String sortOrder);
}
