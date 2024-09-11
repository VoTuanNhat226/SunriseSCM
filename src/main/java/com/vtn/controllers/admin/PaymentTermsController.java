package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.PaymentTerms;
import com.vtn.services.PaymentTermsService;
import com.vtn.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/payment-terms", produces = "application/json; charset=UTF-8")
public class PaymentTermsController {

    private final PaymentTermsService paymentTermsService;
    private final SupplierService supplierService;

    @ModelAttribute
    public void addAttributes(@NotNull Model model) {
        model.addAttribute("suppliers", this.supplierService.findAllWithFilter(null));
    }

    @GetMapping
    public String listPaymentTerms(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("paymentTerms", this.paymentTermsService.findAllWithFilter(params));

        return "payment_terms";
    }

    @GetMapping(path = "/add")
    public String addPaymentTerms(Model model) {
        model.addAttribute("paymentTerms", new PaymentTerms());

        return "add_payment_terms";
    }

    @PostMapping(path = "/add")
    public String addPaymentTerms(Model model, @ModelAttribute(value = "paymentTerms") @Valid PaymentTerms paymentTerms, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_payment_terms";
        }

        this.paymentTermsService.save(paymentTerms);

        return "redirect:/admin/payment-terms";
    }

    @GetMapping(path = "/edit/{paymentTermsId}")
    public String editPaymentTerms(Model model, @PathVariable(value = "paymentTermsId") Long id) {
        model.addAttribute("paymentTerms", this.paymentTermsService.findById(id));

        return "edit_payment_terms";
    }

    @PostMapping(path = "/edit/{paymentTermsId}")
    public String editPaymentTerms(Model model, @PathVariable(value = "paymentTermsId") Long id,
                                   @ModelAttribute(value = "paymentTerms") @Valid PaymentTerms paymentTerms, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_payment_terms";
        }

        this.paymentTermsService.update(paymentTerms);

        return "redirect:/admin/payment-terms";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{paymentTermsId}")
    public void deletePaymentTerms(@PathVariable(value = "paymentTermsId") Long id) {
        this.paymentTermsService.delete(id);
    }
}
