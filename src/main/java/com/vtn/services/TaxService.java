package com.vtn.services;

import com.vtn.dto.tax.TaxResponse;
import com.vtn.pojo.Tax;

import java.util.List;
import java.util.Map;

public interface TaxService {

    Tax findById(Long id);

    void save(Tax tax);

    void update(Tax tax);

    void delete(Long id);

    Long count();

    List<Tax> findAllWithFilter(Map<String, String> params);

    TaxResponse getTaxResponse(Tax tax);

    List<TaxResponse> getAllTaxResponse(Map<String, String> params);
}
