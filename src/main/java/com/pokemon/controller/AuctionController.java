package com.pokemon.controller;

import com.pokemon.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class AuctionController {

    private AuctionService auctionService;

    @GetMapping("/auctions")
    public String getAuctionsPage(Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());
        return "auctions";
    }
}
