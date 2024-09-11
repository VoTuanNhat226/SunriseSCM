package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.dto.user.UserRequestRegister;
import com.vtn.pojo.Supplier;
import com.vtn.services.SupplierService;
import com.vtn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/suppliers", produces = "application/json; charset=UTF-8")
public class SupplierController {

    private final SupplierService supplierService;
    private final UserService userService;

    @GetMapping
    public String listSuppliers(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("suppliers", this.supplierService.findAllWithFilter(params));

        return "suppliers";
    }

    @GetMapping(path = "/add")
    public String addSupplier(Model model) {
        model.addAttribute("supplier", new UserRequestRegister());

        return "add_supplier";
    }

    @PostMapping(path = "/add")
    public String addSupplier(Model model, @ModelAttribute(value = "supplier") @Valid UserRequestRegister supplier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_supplier";
        }

        try {
            this.userService.registerUser(supplier);
        } catch (Exception e) {
            model.addAttribute("errors", List.of(new MessageResponse(e.getMessage())));

            return "add_supplier";
        }

        return "redirect:/admin/suppliers";
    }

    @GetMapping(path = "/edit/{supplierId}")
    public String editSupplier(Model model, @PathVariable(value = "supplierId") Long id) {
        model.addAttribute("supplier", supplierService.findById(id));

        return "edit_supplier";
    }

    @PostMapping(path = "/edit/{supplierId}")
    public String editSupplier(Model model, @PathVariable(value = "supplierId") Long id,
                               @ModelAttribute(value = "supplier") @Valid Supplier supplier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_supplier";
        }

        this.supplierService.update(supplier);

        return "redirect:/admin/suppliers";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{supplierId}")
    public void deleteSupplier(@PathVariable(value = "supplierId") Long id) {
        this.supplierService.delete(id);
    }
}
