package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.DeliverySchedule;
import com.vtn.services.DeliveryScheduleService;
import com.vtn.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
@RequestMapping(path = "/admin/schedules", produces = "application/json; charset=UTF-8")
public class DeliveryScheduleController {

    private final DeliveryScheduleService deliveryScheduleService;
    private final OrderService orderService;

    @ModelAttribute
    public void addAttributes(@NotNull Model model) {
        model.addAttribute("orders", this.orderService.findAllWithFilter(null));
    }

    @GetMapping
    public String listDeliverySchedule(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("deliverySchedules", this.deliveryScheduleService.findAllWithFilter(params));

        return "delivery_schedules";
    }

    @GetMapping(path = "/add")
    public String addDeliverySchedule(Model model) {
        model.addAttribute("deliverySchedule", new DeliverySchedule());

        return "add_delivery_schedule";
    }

    @PostMapping(path = "/add")
    public String addDeliverySchedule(Model model,
                                      @ModelAttribute(value = "deliverySchedule") @Valid DeliverySchedule deliverySchedule,
                                      BindingResult bindingResult, @RequestParam(value = "orderIds", required = false) List<String> orderIds) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_delivery_schedule";
        }

        this.deliveryScheduleService.save(deliverySchedule, orderIds);

        return "redirect:/admin/schedules";
    }

    @GetMapping(path = "/edit/{deliveryScheduleId}")
    public String editDeliverySchedule(Model model, @PathVariable(value = "deliveryScheduleId") Long id) {
        model.addAttribute("scheduleOrders", this.orderService.findByDeliveryScheduleId(id));
        model.addAttribute("deliverySchedule", this.deliveryScheduleService.findById(id));

        return "edit_delivery_schedule";
    }

    @PostMapping(path = "/edit/{deliveryScheduleId}")
    public String editDeliverySchedule(Model model, @PathVariable(value = "deliveryScheduleId") Long id,
                                       @ModelAttribute(value = "deliverySchedule") @Valid DeliverySchedule deliverySchedule,
                                       BindingResult bindingResult,
                                       @RequestParam(value = "orderIds", required = false) List<String> orderIds) {
        model.addAttribute("scheduleOrders", this.orderService.findByDeliveryScheduleId(id));
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_delivery_schedule";
        }

        this.deliveryScheduleService.update(deliverySchedule, orderIds);

        return "redirect:/admin/schedules";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{deliveryScheduleId}")
    public void deleteDeliverySchedule(@PathVariable(value = "deliveryScheduleId") Long id) {
        this.deliveryScheduleService.delete(id);
    }
}
