package com.atypon.literatumproject.webadmintool.controller;

import com.atypon.literatumproject.webadmintool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "home"})
    public String goToHomePage() {
        return "home";
    }

    @GetMapping("/backstage")
    public String goToBackstage() {
        return "backstage";
    }

    @GetMapping("/identities")
    public String goToIdentitesPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "identities";
    }
}
