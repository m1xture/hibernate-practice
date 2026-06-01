package com.java.app.hw43.controller;

import com.java.app.hw43.dto.UserRegistrationDto;
import com.java.app.hw43.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") @Valid UserRegistrationDto dto,
                           BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "register";
        }
        System.out.println("RAW password param: " + request.getParameter("password"));
        System.out.println("DTO password: " + dto.getPassword());
        System.out.println("DTO full: " + dto);
        try {
            userService.register(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
