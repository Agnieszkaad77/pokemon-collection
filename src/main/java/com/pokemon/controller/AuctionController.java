package com.pokemon.controller;

import com.pokemon.exception.AuctionException;
import com.pokemon.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class AuctionController {

    private AuctionService auctionService;

    @GetMapping("/auctions")
    public String getAuctionsPage(Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());
        return "auctions";
    }

    @PostMapping("/auctions")
    public String processPurchase(long id, Model model) {
        System.out.println("ID:" + id);
        try {
            auctionService.purchaseAuction(id);
        } catch (AuctionException e) {
            model.addAttribute("noAuctionMessage", e.getMessage());
        }
        model.addAttribute("auctions", auctionService.getAllAuctions());
        // redirect should be considered here
        return "auctions";
    }
}
