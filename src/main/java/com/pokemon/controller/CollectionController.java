package com.pokemon.controller;

import com.pokemon.dto.AuctionDto;
import com.pokemon.dto.UserDto;
import com.pokemon.exception.LoginException;
import com.pokemon.service.AuctionService;
import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CollectionController {

    private LoginService loginService;
    private AuctionService auctionService;

    @GetMapping("/collection")
    public String getCollectionPage(Model model) {
        try {
            UserDto userDto = loginService.getLoggedUserDto();
            model.addAttribute("loggedUser", userDto);
            AuctionDto auctionDto = new AuctionDto();
            model.addAttribute("auction", auctionDto);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "collection";
    }

    @PostMapping("/collection/sell")
    public String createAuction(AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:/collection";
    }
}
