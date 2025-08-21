package com.evaitcsmatt.shophub.webserver.controllers;

import com.evaitcsmatt.shophub.webserver.dtos.AuthRequest;
import com.evaitcsmatt.shophub.webserver.services.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserCredentialController {
    private final UserCredentialService userCredentialService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new AuthRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("newUser") AuthRequest request) {
        userCredentialService.createUserCredentials(request);
        return "redirect:/store";
    }
}
