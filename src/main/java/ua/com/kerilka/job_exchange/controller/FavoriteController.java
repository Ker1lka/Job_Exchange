package ua.com.kerilka.job_exchange.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.bl.Favorites;
import ua.com.kerilka.job_exchange.entity.Vacancy;

@Controller
public class FavoriteController {

    @GetMapping("/favorites")
    public String getFavorites(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Favorites favorites = (Favorites) session.getAttribute("favorites");

        if (favorites == null) {
            favorites = new Favorites();
        }

        session.setAttribute("favorites", favorites);
        model.addAttribute("favorites", favorites.getFavoriteList());
        model.addAttribute("el", favorites.getSumItem());

        return "favorites";
    }

    @PostMapping("/addFavorite")
    public String addFavorite(@RequestParam(name = "id") Vacancy vacancy,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        Favorites favorites = (Favorites) session.getAttribute("favorites");

        if (favorites == null) {
            favorites = new Favorites();
        }

        favorites.addVacancyToFavorites(vacancy, 1);
        session.setAttribute("favorites", favorites);

        return "redirect:/favorites";
    }

    @PostMapping("/deleteFavorite")
    public String deleteFavorite(@RequestParam(name = "id") Vacancy vacancy,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        Favorites favorites = (Favorites) session.getAttribute("favorites");

        if (favorites == null) {
            favorites = new Favorites();
        }

        favorites.removeVacancyFromFavorites(vacancy);
        session.setAttribute("favorites", favorites);

        return "redirect:/favorites";
    }
}
