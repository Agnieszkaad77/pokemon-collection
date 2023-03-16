package com.pokemon.controller;

import com.pokemon.entity.UserEntity;
import com.pokemon.service.RankingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class RankingController {

    private RankingService rankingService;

    @GetMapping("/ranking")
    public String getRankingPage(Model model) {
        List<UserEntity> ranking = rankingService.getRanking();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }
}
