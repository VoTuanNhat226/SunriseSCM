package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.Warehouse;
import com.vtn.services.WarehouseService;
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
@RequestMapping(path = "/admin/warehouses", produces = "application/json; charset=UTF-8")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public String listWarehouse(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("warehouses", this.warehouseService.findAllWithFilter(params));

        return "warehouses";
    }

    @GetMapping(path = "/add")
    public String addWarehouse(Model model) {
        model.addAttribute("warehouse", new Warehouse());

        return "add_warehouse";
    }

    @PostMapping(path = "/add")
    public String addWarehouse(Model model, @ModelAttribute(value = "warehouse") @Valid Warehouse warehouse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_warehouse";
        }

        this.warehouseService.save(warehouse);

        return "redirect:/admin/warehouses";
    }

    @GetMapping(path = "/edit/{warehouseId}")
    public String editWarehouse(Model model, @PathVariable(value = "warehouseId") Long id) {
        model.addAttribute("warehouse", this.warehouseService.findById(id));

        return "edit_warehouse";
    }

    @PostMapping(path = "/edit/{warehouseId}")
    public String editWarehouse(Model model, @PathVariable(value = "warehouseId") Long id,
                                @ModelAttribute(value = "warehouse") @Valid Warehouse warehouse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_warehouse";
        }

        this.warehouseService.update(warehouse);

        return "redirect:/admin/warehouses";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{warehouseId}")
    public void deleteWarehouse(@PathVariable(value = "warehouseId") Long id) {
        this.warehouseService.delete(id);
    }
}
