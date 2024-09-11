package com.vtn.services;

import com.vtn.dto.invoice.InvoiceResponse;
import com.vtn.pojo.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    Invoice findById(Long id);

    Invoice findByInvoiceNumber(String invoiceNumber);

    void save(Invoice invoice);

    void update(Invoice invoice);

    void delete(Long id);

    Long count();

    List<Invoice> findAllWithFilter(Map<String, String> params);

    InvoiceResponse getInvoiceResponse(Invoice invoice);

    List<InvoiceResponse> getAllInvoiceResponse(Map<String, String> params);
}
