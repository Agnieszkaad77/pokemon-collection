package com.pokemon.controller;

import com.pokemon.exception.AuctionException;
import com.pokemon.exception.LoginException;
import com.pokemon.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class AuctionController {

    private AuctionService auctionService;

    @GetMapping("/auctions")
    public String getAuctionsPage(Model model) {
        try {
            model.addAttribute("auctions", auctionService.getAllAuctions());
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "auctions";
    }

    @PostMapping("/auctions")
    public String processPurchase(long id, RedirectAttributes redirectAttributes) {
        try {
            auctionService.purchaseAuction(id);
        } catch (AuctionException e) {
            redirectAttributes.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/auctions";
    }
}
