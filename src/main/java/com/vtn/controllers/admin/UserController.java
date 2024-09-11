package com.vtn.controllers.admin;

import com.vtn.dto.MessageResponse;
import com.vtn.pojo.User;
import com.vtn.services.UserService;
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
@RequestMapping(path = "/admin/users", produces = "application/json; charset=UTF-8")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String listUser(Model model, @RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        model.addAttribute("users", this.userService.findAllWithFilter(params));

        return "users";
    }

    @GetMapping(path = "/edit/{userId}")
    public String editUser(Model model, @PathVariable(value = "userId") Long id) {
        model.addAttribute("user", this.userService.findById(id));

        return "edit_user";
    }

    @PostMapping(path = "/edit/{userId}")
    public String editUser(Model model, @PathVariable(value = "userId") Long id,
                           @ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", MessageResponse.fromBindingResult(bindingResult));

            return "edit_user";
        }

        this.userService.update(user);

        return "redirect:/admin/users";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable(value = "userId") Long id) {
        this.userService.delete(id);
    }
}
