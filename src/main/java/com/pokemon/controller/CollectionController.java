package com.pokemon.controller;

import com.pokemon.dto.AuctionDto;
import com.pokemon.dto.UserDto;
import com.pokemon.exception.AuctionException;
import com.pokemon.exception.LoginException;
import com.pokemon.service.AuctionService;
import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class CollectionController {

    private LoginService loginService;
    private AuctionService auctionService;

    @GetMapping("/collection")
    public String getCollectionPage(Model model) {
        try {
            UserDto userDto = loginService.getLoggedUserDtoOrThrow();
            model.addAttribute("pageData", userDto);
            AuctionDto auctionDto = new AuctionDto();
            model.addAttribute("auction", auctionDto);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "collection";
    }

    @PostMapping("/collection")
    public String createAuction(AuctionDto auctionDto, RedirectAttributes redirectAttributes) {
        try {
            auctionService.saveAuction(auctionDto);
        } catch (AuctionException e) {
            redirectAttributes.addFlashAttribute("auctionMessage", e.getMessage());
        }
        return "redirect:/collection";
    }
}
