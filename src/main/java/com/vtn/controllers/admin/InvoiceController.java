package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.Invoice;
import com.vtn.services.InvoiceService;
import com.vtn.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/invoices", produces = "application/json; charset=UTF-8")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final TaxService taxService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("taxes", this.taxService.findAllWithFilter(null));
    }

    @GetMapping
    public String listInvoice(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("invoices", this.invoiceService.findAllWithFilter(params));

        return "invoices";
    }

    @GetMapping(path = "/edit/{invoiceId}")
    public String editInvoice(Model model, @PathVariable(value = "invoiceId") Long id) {
        model.addAttribute("invoice", this.invoiceService.findById(id));

        return "edit_invoice";
    }

    @PostMapping(path = "/edit/{invoiceId}")
    public String editInvoice(Model model, @PathVariable(value = "invoiceId") Long id,
                              @ModelAttribute(value = "invoice") @Valid Invoice invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_invoice";
        }

        this.invoiceService.update(invoice);

        return "redirect:/admin/invoices";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{invoiceId}")
    public void deleteInvoice(@PathVariable(value = "invoiceId") Long id) {
        this.invoiceService.delete(id);
    }
}
