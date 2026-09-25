package com.manrique.Generic_web_app.controllers;

import com.manrique.Generic_web_app.DTOs.UserRegisterDTO;
import com.manrique.Generic_web_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("userRegisterDTO") UserRegisterDTO dto,
                                  BindingResult bindingResult,
                                  RedirectAttributes flash) {

        if (!dto.isPasswordMatching()) {
            bindingResult.rejectValue(
                    "confirmPassword",
                    "error.confirmPassword",
                    "Passwords do not match."
            );
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.existsByUsername(dto.getUsername())) {
            bindingResult.rejectValue("username", "error.duplicatedUsername", "This username already exists.");
        }

        if (userService.existsByEmail(dto.getEmail())) {
            bindingResult.rejectValue("email", "error.duplicatedEmail", "This email already exists.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(dto);

        flash.addFlashAttribute("registrationMessage", "Your registration was successful, " + dto.getUsername());
        return "redirect:/login";
    }
}
