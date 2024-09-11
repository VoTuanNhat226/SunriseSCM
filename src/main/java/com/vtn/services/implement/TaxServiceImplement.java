package com.vtn.services.implement;

import com.vtn.dto.tax.TaxResponse;
import com.vtn.pojo.Invoice;
import com.vtn.pojo.Tax;
import com.vtn.repository.TaxRepository;
import com.vtn.services.InvoiceService;
import com.vtn.services.TaxService;
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
public class TaxServiceImplement implements TaxService {

    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public Tax findById(Long id) {
        return this.taxRepository.findById(id);
    }

    @Override
    public void save(Tax tax) {
        this.taxRepository.save(tax);
    }

    @Override
    public void update(Tax tax) {
        this.taxRepository.update(tax);
    }

    @Override
    public void delete(Long id) {
        Tax tax = this.taxRepository.findById(id);
        List<Invoice> invoicesToUpdate = new ArrayList<>(tax.getInvoiceSet());

        invoicesToUpdate.forEach(invoice -> {
            invoice.setTax(null);
            this.invoiceService.update(invoice);
        });

        this.taxRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.taxRepository.count();
    }

    @Override
    public List<Tax> findAllWithFilter(Map<String, String> params) {
        return this.taxRepository.findAllWithFilter(params);
    }

    @Override
    public TaxResponse getTaxResponse(@NotNull Tax tax) {
        return TaxResponse.builder()
                .id(tax.getId())
                .rate(tax.getRate())
                .region(tax.getRegion())
                .build();
    }

    @Override
    public List<TaxResponse> getAllTaxResponse(Map<String, String> params) {
        return this.taxRepository.findAllWithFilter(params).stream()
                .map(this::getTaxResponse)
                .collect(Collectors.toList());
    }
}
