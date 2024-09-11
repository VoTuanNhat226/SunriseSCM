package com.vtn.repository;

import com.vtn.pojo.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceRepository {

    Invoice findById(Long id);

    Invoice findByInvoiceNumber(String invoiceNumber);

    Invoice findByOrderId(Long orderId);

    void save(Invoice invoice);

    void update(Invoice invoice);

    void delete(Long id);

    Long count();

    List<Invoice> findAllWithFilter(Map<String, String> params);
}
