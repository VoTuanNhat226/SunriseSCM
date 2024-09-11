package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.dto.user.UserRequestRegister;
import com.vtn.pojo.Shipper;
import com.vtn.services.ShipperService;
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
@RequestMapping(path = "/admin/shippers", produces = "application/json; charset=UTF-8")
public class ShipperController {

    private final ShipperService shipperService;
    private final UserService userService;

    @GetMapping
    public String listShippers(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("shippers", this.shipperService.findAllWithFilter(params));

        return "shippers";
    }

    @GetMapping(path = "/add")
    public String addShipper(Model model) {
        model.addAttribute("shipper", new UserRequestRegister());

        return "add_shipper";
    }

    @PostMapping(path = "/add")
    public String addShipper(Model model, @ModelAttribute(value = "shipper") @Valid UserRequestRegister shipper, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_shipper";
        }

        try {
            this.userService.registerUser(shipper);
        } catch (Exception e) {
            model.addAttribute("errors", List.of(new MessageResponse(e.getMessage())));

            return "add_shipper";
        }

        return "redirect:/admin/shippers";
    }

    @GetMapping(path = "/edit/{shipperId}")
    public String editShipper(Model model, @PathVariable(value = "shipperId") Long id) {
        model.addAttribute("shipper", this.shipperService.findById(id));

        return "edit_shipper";
    }

    @PostMapping(path = "/edit/{shipperId}")
    public String editShipper(Model model, @PathVariable(value = "shipperId") Long id,
                              @ModelAttribute(value = "shipper") @Valid Shipper shipper, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_shipper";
        }

        this.shipperService.update(shipper);

        return "redirect:/admin/shippers";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{shipperId}")
    public void deleteShipper(@PathVariable(value = "shipperId") Long id) {
        this.shipperService.delete(id);
    }
}
