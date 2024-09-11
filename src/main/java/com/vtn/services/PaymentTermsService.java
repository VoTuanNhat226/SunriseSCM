package com.vtn.services;

import com.vtn.pojo.PaymentTerms;

import java.util.List;
import java.util.Map;

public interface PaymentTermsService {

    PaymentTerms findById(Long id);

    void save(PaymentTerms paymentTerms);

    void update(PaymentTerms paymentTerms);

    void delete(Long id);

    Long count();

    List<PaymentTerms> findAllWithFilter(Map<String, String> params);
}
