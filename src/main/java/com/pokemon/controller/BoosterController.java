package com.pokemon.controller;
import com.pokemon.dto.CardDto;
import com.pokemon.dto.UserDto;
import com.pokemon.exception.BoosterException;
import com.pokemon.exception.LoginException;
import com.pokemon.service.BoosterService;
import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoosterController {

    private LoginService loginService;
    private BoosterService boosterService;

    @GetMapping("/buy-booster")
    public String getBuyBoosterPage(Model model) {
        try {
            UserDto userDto = loginService.getLoggedUserDto();
            model.addAttribute("loggedUser", userDto);
            addPokeCoinsAttribute(model);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "booster";
    }

    @PostMapping("/buy-booster")
    public String processBuyBooster(Model model) {
        try {
            List<CardDto> cards = boosterService.buyBooster();
            model.addAttribute("cards", cards);
            addPokeCoinsAttribute(model);
        } catch (BoosterException e) {
            model.addAttribute("noPokeCoinsMessage", e.getMessage());
            return "booster";
        }
        return "booster";
    }

    private void addPokeCoinsAttribute(Model model) {
        model.addAttribute("pokeCoins", loginService.getLoggedUserDto().getPokeCoins());
    }
}
