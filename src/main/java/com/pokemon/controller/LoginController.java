package com.pokemon.controller;

import com.pokemon.dto.UserLoginDto;
import com.pokemon.exception.LoginException;
import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class LoginController {

    private static final String LOGIN_SUCCESS_MESSAGE = "Logged successfully!";
    private LoginService loginService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserLoginDto());
        return "login";
    }

    @PostMapping("/logged")
    public String processLogin(@ModelAttribute("loginRequest") UserLoginDto userLoginDto,
                               Model model, RedirectAttributes redirectAttributes) {
        try {
            loginService.login(userLoginDto);
            redirectAttributes.addFlashAttribute("loginMessage", LOGIN_SUCCESS_MESSAGE);
        } catch (LoginException e) {
            model.addAttribute("incorrectLoginMessage", e.getMessage());
            return "login";
        }
        return "redirect:/";
    }

}
