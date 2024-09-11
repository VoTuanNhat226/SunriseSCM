package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.Inventory;
import com.vtn.services.InventoryService;
import com.vtn.services.WarehouseService;
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
@RequestMapping(path = "/admin/inventories", produces = "application/json; charset=UTF-8")
public class InventoryController {

    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @ModelAttribute
    public void addAttributes(@NotNull Model model) {
        model.addAttribute("warehouses", this.warehouseService.findAllWithFilter(null));
    }

    @GetMapping
    public String listInventory(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("inventories", this.inventoryService.findAllWithFilter(params));

        return "inventories";
    }

    @GetMapping(path = "/add")
    public String addInventory(Model model) {
        model.addAttribute("inventory", new Inventory());

        return "add_inventory";
    }

    @PostMapping(path = "/add")
    public String addInventory(Model model, @ModelAttribute(value = "inventory") @Valid Inventory inventory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_inventory";
        }

        this.inventoryService.save(inventory);

        return "redirect:/admin/inventories";
    }

    @GetMapping(path = "/edit/{inventoryId}")
    public String editInventory(Model model, @PathVariable(value = "inventoryId") Long id) {
        model.addAttribute("inventory", this.inventoryService.findById(id));

        return "edit_inventory";
    }

    @PostMapping(path = "/edit/{inventoryId}")
    public String editInventory(Model model, @PathVariable(value = "inventoryId") Long id,
                                @ModelAttribute(value = "inventory") @Valid Inventory inventory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_inventory";
        }

        this.inventoryService.update(inventory);

        return "redirect:/admin/inventories";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{inventoryId}")
    public void deleteInventory(@PathVariable(value = "inventoryId") Long id) {
        this.inventoryService.delete(id);
    }
}
