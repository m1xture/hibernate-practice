package com.java.app.hw43.controller;

import com.java.app.hw43.dto.UserRegistrationDto;
import com.java.app.hw43.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(("/users"))
public class UserController {

    private final UserService userService;

    @GetMapping
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

}
