package com.pokemon.controller;

import com.pokemon.dto.UserRegistrationDto;
import com.pokemon.exception.RegistrationException;
import com.pokemon.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private static final String REGISTRATION_SUCCESS_MESSAGE = "Registered successfully!";

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationRequest", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registered")
    public String processRegistration(@ModelAttribute("registrationRequest") UserRegistrationDto userRegistrationDto,
                                      Model model, RedirectAttributes redirectAttributes) {
        try {
            registrationService.register(userRegistrationDto);
            redirectAttributes.addFlashAttribute("registrationMessage", REGISTRATION_SUCCESS_MESSAGE);
        } catch (IllegalArgumentException i) {
            model.addAttribute("fieldsNotFilled", i.getMessage());
            return "registration";
        } catch (RegistrationException r) {
            model.addAttribute("fieldsNotCorrect", r.getMessage());
            return "registration";
        }
        return "redirect:/";
    }
}
