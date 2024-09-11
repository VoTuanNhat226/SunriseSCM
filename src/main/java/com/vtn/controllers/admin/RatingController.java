package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.Rating;
import com.vtn.services.RatingService;
import com.vtn.services.SupplierService;
import com.vtn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping(path = "/admin/ratings", produces = "application/json; charset=UTF-8")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;
    private final SupplierService supplierService;

    @ModelAttribute
    public void addAttributes(@NotNull Model model) {
        model.addAttribute("suppliers", this.supplierService.findAllWithFilter(null));
        model.addAttribute("users", this.userService.findAllWithFilter(null));
    }

    @GetMapping
    public String listRating(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("ratings", this.ratingService.findAllWithFilter(params));

        return "ratings";
    }

    @GetMapping(path = "/add")
    public String addRating(Model model) {
        model.addAttribute("rating", new Rating());

        return "add_rating";
    }

    @PostMapping(path = "/add")
    public String addRating(Model model, @ModelAttribute(value = "rating") @Valid Rating rating, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "add_rating";
        }

        this.ratingService.save(rating);

        return "redirect:/admin/ratings";
    }

    @GetMapping(path = "/edit/{ratingId}")
    public String editRating(Model model, @PathVariable(value = "ratingId") Long id) {
        model.addAttribute("rating", this.ratingService.findById(id));

        return "edit_rating";
    }

    @PostMapping(path = "/edit/{ratingId}")
    public String editRating(Model model, @PathVariable(value = "ratingId") Long id,
                             @ModelAttribute(value = "rating") @Valid Rating rating, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_rating";
        }

        this.ratingService.update(rating);

        return "redirect:/admin/ratings";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{ratingId}")
    public void deleteRating(@PathVariable(value = "ratingId") Long id) {
        this.ratingService.delete(id);
    }
}
