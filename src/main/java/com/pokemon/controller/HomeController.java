package com.pokemon.controller;

import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private LoginService loginService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("loggedUser", loginService.getLoggedUserDto());
        return "index";
    }
}
