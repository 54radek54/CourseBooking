package com.example.coursebooking.controllers;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final AppUserService AppUserService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("app_user", new AppUser());
        return "registration";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("app_user") AppUser appUser) throws AppBasicException {
        AppUserService.addUser(appUser);
        return "register_success";
    }

}
