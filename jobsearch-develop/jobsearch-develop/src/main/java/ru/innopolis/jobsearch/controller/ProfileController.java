package ru.innopolis.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myprofile")
public class ProfileController {

    @GetMapping
    public String showMyProfile(Model model){
        return "myprofile";
    }
}
