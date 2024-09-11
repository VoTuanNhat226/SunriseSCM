package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.dto.user.UserRequestRegister;
import com.vtn.pojo.Customer;
import com.vtn.services.CustomerService;
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
@RequestMapping(path = "/admin/customers", produces = "application/json; charset=UTF-8")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping
    public String listCustomers(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("customers", this.customerService.findAllWithFilter(params));

        return "customers";
    }

    @GetMapping(path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new UserRequestRegister());

        return "add_customer";
    }

    @PostMapping(path = "/add")
    public String addCustomer(Model model, @ModelAttribute(value = "customer") @Valid UserRequestRegister customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_customer";
        }

        try {
            this.userService.registerUser(customer);
        } catch (Exception e) {
            model.addAttribute("errors", List.of(new MessageResponse(e.getMessage())));

            return "add_customer";
        }

        return "redirect:/admin/customers";
    }

    @GetMapping(path = "/edit/{customerId}")
    public String editCustomer(Model model, @PathVariable(value = "customerId") Long id) {
        model.addAttribute("customer", this.customerService.findById(id));

        return "edit_customer";
    }

    @PostMapping(path = "/edit/{customerId}")
    public String editCustomer(Model model, @PathVariable(value = "customerId") Long id,
                               @ModelAttribute(value = "customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_customer";
        }

        this.customerService.update(customer);

        return "redirect:/admin/customers";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") Long id) {
        this.customerService.delete(id);
    }
}
